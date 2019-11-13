package com.shui.payment.cmb.parser;

import com.alibaba.fastjson.JSON;
import com.shui.payment.cmb.beans.accounttradequeryprotocol.AccountPayQueryResData;
import com.shui.payment.cmb.beans.accounttradequeryprotocol.AccountTradeResData;
import com.shui.payment.cmb.beans.directpayprotocol.CmbDirectPayResData;
import com.shui.payment.cmb.beans.querylistprotocol.CmbOrderResData;
import com.shui.payment.cmb.beans.querylistprotocol.CmbQueryListResData;
import com.shui.payment.cmb.constant.CmbParamsName;
import com.shui.payment.cmb.enums.BizRequestStatusEnum;
import com.shui.payment.cmb.enums.BizStatusEnum;
import com.shui.payment.cmb.enums.ErrCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * TODO 4.4 招行工具类，尚未优化
 */
@Slf4j
public class CmbRequestUtil {


    /**
     * 生成多笔交易 直接支付的请求报文
     */
    public static String getBatchDirectPayRequestStr(String funcName, String loginName, String businessType, List<Map> dataMap) {
        XmlPacket xmlPkt = new XmlPacket(funcName, loginName);
        Map mpPodInfo = new Properties();
        mpPodInfo.put("BUSCOD", businessType);
        xmlPkt.putProperty("SDKPAYRQX", mpPodInfo);
        for (Map map : dataMap) {
            xmlPkt.putProperty("DCOPDPAYX", map);
        }

        return xmlPkt.toXmlString();
    }

    /**
     * 生成代发或代扣的请求报文
     */
    public static String getAgentWithholdingRequestStr(String funcName, String loginName, Map mpPodInfo, Map dataMap) {
        XmlPacket xmlPkt = new XmlPacket(funcName, loginName);
        xmlPkt.putProperty("SDKATSRQX", mpPodInfo);
        xmlPkt.putProperty("SDKATDRQX", dataMap);

        return xmlPkt.toXmlString();
    }

    /**
     * 生成查询的请求报文
     */
    public static String getQueryRequestStr(String funcName, String loginName, String dataType, Map dataMap) {
        XmlPacket xmlPkt = new XmlPacket(funcName, loginName);
        xmlPkt.putProperty(dataType, dataMap);

        return xmlPkt.toXmlString();
    }

    /**
     * 处理支付等返回的结果
     */


    public static CmbDirectPayResData processPayResult(String result) {
        CmbDirectPayResData resData = new CmbDirectPayResData();
        //默认-9 ，UNKOWN
        resData.setReturnCode(ErrCodeEnum.UNKNOWN.name());

        if (StringUtils.isEmpty(result)) {

            resData.setReturnMsg("请求返回报文为空值");
            return resData;
        }

        XmlPacket xmlPacket = XmlPacket.valueOf(result);
        if (xmlPacket == null) {
            resData.setReturnMsg("响应报文解析失败");
            return resData;
        }

        Map propPayResult = xmlPacket.getProperty(CmbParamsName.NTQPAYRQZ, 0);

        resData.setReturnMsg(xmlPacket.getERRMSG())
                .setReturnCode(getReturnCode(xmlPacket.getRETCOD()));

        if (!("SUCCESS".equals(resData.getReturnCode()) || ErrCodeEnum.UNKNOWN.name().equals(resData.getReturnCode()))) {
            resData.setErrCode(getErrorCode(xmlPacket.getRETCOD()))
                    .setErrMsg(xmlPacket.getERRMSG());
            log.info("【招行银企直连参数处理工具类】--->processPayResult, 支付失败：{}", JSON.toJSONString(resData));
            return resData;
        }

        //返回码为成功时的数据对象转换
        if ("0".equals(xmlPacket.getRETCOD())) {

            resData.setChannelSerialNo(getMapValue(propPayResult, CmbParamsName.SQRNBR))
                    .setQdsOrderNo(getMapValue(propPayResult, CmbParamsName.YURREF))
                    .setChannelOrderNo(getMapValue(propPayResult, CmbParamsName.REQNBR))
                    .setRequestStatus(getMapValue(propPayResult, CmbParamsName.REQSTS))
                    .setBizResult(getMapValue(propPayResult, CmbParamsName.RTNFLG))
                    .setPendingSequence(getMapValue(propPayResult, CmbParamsName.OPRSQN))
                    .setOperateAlias(getMapValue(propPayResult, CmbParamsName.OPRALS))
                    .setErrCode(getMapValue(propPayResult, CmbParamsName.ERRCOD))
                    .setErrMsg(getMapValue(propPayResult, CmbParamsName.ERRTXT))
            ;

        }

        return resData;
    }

    public static String getReturnCode(String code) {

        Integer num = Integer.valueOf(code);
        switch (num) {
            case 0:
            case -1:
            case -9:
                return "SUCCESS";
            default:
                return "FAIL";
        }

    }

    /**
     * 对返回代码的通用定义 - 附录A2
     *
     * @param code
     * @return
     */
    public static String getErrorCode(String code) {

        return getReturnCode(code);

    }

    /**
     * 处理查询或代发代扣返回的结果
     */
    public static Map processAgentAndQueryResult(String result, String retFlag) {
        Map retMap = new HashMap();
        retMap.put("result", "F");

        if (result != null && result.length() > 0) {
            XmlPacket pktRsp = XmlPacket.valueOf(result);
            if (pktRsp != null) {
                String sRetCod = pktRsp.getRETCOD();
                if ("0".equals(sRetCod)) {
                    Map propResult = pktRsp.getProperty(retFlag, 0);
                    if (propResult != null) {
                        retMap.put("result", "S");
                        retMap.putAll(propResult);
                        log.info("【招行银企直连参数处理工具类】--->processAgentAndQueryResult, 查询或代发代扣成功, propResult= " + propResult);
                    } else {
                        retMap.put("msg", "请求结果成功，但业务代码段为空值");
                        log.info("【招行银企直连参数处理工具类】--->processAgentAndQueryResult, 请求结果成功，但业务代码段为空值");
                    }
                } else {
                    retMap.put("msg", pktRsp.getERRMSG());
                    log.info("【招行银企直连参数处理工具类】--->processAgentAndQueryResult, retFlag= " + retFlag + ", 查询或代发代扣失败：" + pktRsp.getERRMSG());
                }
            } else {
                retMap.put("msg", "响应报文解析失败");
                log.info("【招行银企直连参数处理工具类】--->processAgentAndQueryResult, 响应报文解析失败, retFlag= " + retFlag);
            }
        } else {
            retMap.put("msg", "请求返回报文为空值");
            log.info("【招行银企直连参数处理工具类】--->processAgentAndQueryResult, 请求返回报文为空值");
        }

        return retMap;
    }

    /**
     * 处理批量查询返回的结果
     */
    public static CmbQueryListResData processPayBatchQueryResult(String result, String retFlag) {
        CmbQueryListResData resData = new CmbQueryListResData();
        List<CmbOrderResData> successDataList = new ArrayList<>(); //保存支付成功的交易
        List<CmbOrderResData> failDataList = new ArrayList<>(); //保存支付失败的交易
        List<CmbOrderResData> otherDataList = new ArrayList<>(); //保存其他情况的交易


        if (StringUtils.isEmpty(result)) {
            return resData;
        }

        XmlPacket xmlPacket = XmlPacket.valueOf(result);

        if (xmlPacket == null || (!"0".equals(xmlPacket.getRETCOD()))) {
            return resData;
        }
        resData.setReturnCode(getReturnCode(xmlPacket.getRETCOD()))
                .setReturnMsg(xmlPacket.getERRMSG());

        int dataSize = xmlPacket.getSectionSize(retFlag);
        log.info("【招行银企直连参数处理工具类】--->processPayBatchQueryResult, dataSize= " + dataSize);
        for (int i = 0; i < dataSize; i++) {

            Map propResult = xmlPacket.getProperty(retFlag, i);
            if (propResult == null) {
                continue;
            }

            String reqSts = (String) propResult.get(CmbParamsName.REQSTS); //请求状态
            String rtnFlg = (String) propResult.get("RTNFLG"); //业务处理结果

            if (BizRequestStatusEnum.FIN == BizRequestStatusEnum.getEnum(reqSts)) {
                if (BizStatusEnum.S == BizStatusEnum.getEnum(rtnFlg)) {
                    successDataList.add(convertCmbOrderResData(propResult));
                } else if (BizStatusEnum.F == BizStatusEnum.getEnum(rtnFlg)
                        || BizStatusEnum.B == BizStatusEnum.getEnum(rtnFlg)) {
                    failDataList.add(convertCmbOrderResData(propResult));
                } else {
                    otherDataList.add(convertCmbOrderResData(propResult));
                }
            } else {
                otherDataList.add(convertCmbOrderResData(propResult));
            }

        }

        resData.setSuccessDataList(successDataList)
                .setFailDataList(failDataList)
                .setOtherDataList(otherDataList);

        log.info("批量查询返回结果json={}", JSON.toJSONString(resData));
        return resData;
    }

    private static CmbOrderResData convertCmbOrderResData(Map map) {
        CmbOrderResData data = new CmbOrderResData();
        data.setChannelOrderNo(getMapValue(map, CmbParamsName.REQNBR))
                .setBizType(getMapValue(map, CmbParamsName.BUSCOD))
                .setBizMode(getMapValue(map, CmbParamsName.BUSMOD))
                .setCmbBranchNo(getMapValue(map, CmbParamsName.DBTBBK))
                .setPayAccountNo(getMapValue(map, CmbParamsName.DBTACC))
                .setAcceptBranchNo(getMapValue(map, CmbParamsName.CRTBBK))
                .setAcceptAccountNo(getMapValue(map, CmbParamsName.CRTACC))
                .setAcceptAccountName(getMapValue(map, CmbParamsName.CRTNAM))
                .setCny(getMapValue(map, CmbParamsName.CCYNBR))
                .setOrderAmt(getMapValue(map, CmbParamsName.TRSAMT))
                .setQueryDate(getMapValue(map, CmbParamsName.EPTDAT))
                .setQueryTime(getMapValue(map, CmbParamsName.EPTTIM))
                .setOperateDate(getMapValue(map, CmbParamsName.OPRDAT))
                .setQdsOrderNo(getMapValue(map, CmbParamsName.YURREF))
                .setRequestStatus(getMapValue(map, CmbParamsName.REQSTS))
                .setBizResult(getMapValue(map, CmbParamsName.RTNFLG))
                .setContainFile(getMapValue(map, CmbParamsName.ATHFLG))
                .setRemark(getMapValue(map, CmbParamsName.RSV30Z))
                .setErrMsg(getMapValue(map, CmbParamsName.ERRTXT));
        return data;
    }

    /**
     * 2.3 查询账户交易明细接口返回结果解析
     *
     * @param result
     * @param dataFlag
     * @return
     */
    public static AccountPayQueryResData processAccountPayQuery(String result, String dataFlag) {

        AccountPayQueryResData resData = new AccountPayQueryResData();

        XmlPacket xmlPacket = XmlPacket.valueOf(result);

        if (xmlPacket == null || (!"0".equals(xmlPacket.getRETCOD()))) {
            return new AccountPayQueryResData().setReturnCode("FAIL");
        }
        resData.setReturnMsg(xmlPacket.getERRMSG())
                .setReturnCode(getReturnCode(xmlPacket.getRETCOD()));
        if (!("SUCCESS".equals(resData.getReturnCode()))) {
            resData.setErrCode(getErrorCode(xmlPacket.getRETCOD()))
                    .setErrMsg(xmlPacket.getERRMSG());
            log.info("2.3 查询账户交易返回结果，processAccountPayQuery, 结果解析失败：{}", JSON.toJSONString(resData));
            return resData;
        }

        //正常解析返回结果信息
        List<AccountTradeResData> tradeList = new ArrayList<>();
        for (int i = 0; i < xmlPacket.getSectionSize(dataFlag); i++) {
            Map propMap = xmlPacket.getProperty(dataFlag, i);
            if ("0".equals(xmlPacket.getRETCOD())) {
                AccountTradeResData trade = getAccountTradeResData(propMap);
                tradeList.add(trade);
            }
        }

        resData.setResData(tradeList);
        return resData;

    }

    /**
     * 组装2.3 接口返回数据
     *
     * @param propMap
     * @return
     */
    private static AccountTradeResData getAccountTradeResData(Map propMap) {
        AccountTradeResData trade = new AccountTradeResData();
        trade.setTradeDate(getMapValue(propMap, CmbParamsName.ETYDAT))
                .setTradeTime(getMapValue(propMap, CmbParamsName.ETYTIM))
                .setTypeCode(getMapValue(propMap, CmbParamsName.AMTCDR))
                .setBankOrderNo(getMapValue(propMap, CmbParamsName.REFNBR))
                .setQdsOrderNo(getMapValue(propMap, CmbParamsName.YURREF))
                .setChannelOrderNo(getMapValue(propMap, CmbParamsName.REQNBR))
                .setTradeType(getMapValue(propMap, CmbParamsName.TRSCOD))
                .setDesc(getMapValue(propMap, CmbParamsName.NARYUR))
                .setBorrowAmt(getMapValue(propMap, CmbParamsName.TRSAMTD))
                .setLoanAmt(getMapValue(propMap, CmbParamsName.TRSAMTC))
                .setBalanceAmt(getMapValue(propMap, CmbParamsName.TRSBLV))
                .setBizName(getMapValue(propMap, CmbParamsName.BUSNAM))
                .setPurpose(getMapValue(propMap, CmbParamsName.NUSAGE))
                .setBizDesc(getMapValue(propMap, CmbParamsName.BUSNAR))
                .setClientAreaName(getMapValue(propMap, CmbParamsName.C_RPYBBK))
                .setClientAccountName(getMapValue(propMap, CmbParamsName.RPYNAM))
                .setClientAccountNo(getMapValue(propMap, CmbParamsName.RPYACC))
                .setClientBranchNo(getMapValue(propMap, CmbParamsName.RPYBBN))
                .setClientBranchName(getMapValue(propMap, CmbParamsName.RPYBNK))
                .setClientBranchAddress(getMapValue(propMap, CmbParamsName.RPYADR))
                .setAlphaCompArea(getMapValue(propMap, CmbParamsName.C_GSBBBK))
                .setAlphaCompAccountNo(getMapValue(propMap, CmbParamsName.GSBACC))
                .setAlphaCompName(getMapValue(propMap, CmbParamsName.GSBNAM))
                .setInfoFlag(getMapValue(propMap, CmbParamsName.INFFLG))
                .setIncludeAttachment(getMapValue(propMap, CmbParamsName.ATHFLG))
                .setBillNo(getMapValue(propMap, CmbParamsName.CHKNBR))
                .setReverseFlag(getMapValue(propMap, CmbParamsName.RSVFLG))
                .setAppendDesc(getMapValue(propMap, CmbParamsName.NAREXT));
        return trade;
    }
    private static String getMapValue(Map map, String key) {
        return map.get(key) == null ? null : map.get(key).toString();
    }
}
