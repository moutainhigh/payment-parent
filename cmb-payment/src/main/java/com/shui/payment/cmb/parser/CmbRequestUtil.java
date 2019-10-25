package com.shui.payment.cmb.parser;

import com.alibaba.fastjson.JSON;
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
     * 生成直接支付的请求报文
     */
    public static String getDirectPayRequestStr(String funcName, String loginName, String businessType, Map dataMap) {
        XmlPacket xmlPkt = new XmlPacket(funcName, loginName);
        Map mpPodInfo = new Properties();
        mpPodInfo.put("BUSCOD", businessType);
        xmlPkt.putProperty("SDKPAYRQX", mpPodInfo);
        xmlPkt.putProperty("DCOPDPAYX", dataMap);

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

            resData.setChannelSerialNo(propPayResult.get(CmbParamsName.SQRNBR) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.SQRNBR)))
                    .setQdsOrderNo(propPayResult.get(CmbParamsName.YURREF) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.YURREF)))
                    .setChannelOrderNo(propPayResult.get(CmbParamsName.REQNBR) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.REQNBR)))
                    .setRequestStatus(propPayResult.get(CmbParamsName.REQSTS) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.REQSTS)))
                    .setBizResult(propPayResult.get(CmbParamsName.RTNFLG) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.RTNFLG)))
                    .setPendingSequence(propPayResult.get(CmbParamsName.OPRSQN) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.OPRSQN)))
                    .setOperateAlias(propPayResult.get(CmbParamsName.OPRALS) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.OPRALS)))
                    .setErrCode(propPayResult.get(CmbParamsName.ERRCOD) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.ERRCOD)))
                    .setErrMsg(propPayResult.get(CmbParamsName.ERRTXT) == null ? null : String.valueOf(propPayResult.get(CmbParamsName.ERRTXT)))
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
        data.setChannelOrderNo(String.valueOf(map.get(CmbParamsName.REQNBR)))
                .setBizType(String.valueOf(map.get(CmbParamsName.BUSCOD)))
                .setBizMode(String.valueOf(map.get(CmbParamsName.BUSMOD)))
                .setCmbBranchNo(String.valueOf(map.get(CmbParamsName.DBTBBK)))
                .setPayAccountNo(String.valueOf(map.get(CmbParamsName.DBTACC)))
                .setAcceptBranchNo(String.valueOf(map.get(CmbParamsName.CRTBBK)))
                .setAcceptAccountNo(String.valueOf(map.get(CmbParamsName.CRTACC)))
                .setAcceptAccountName(String.valueOf(map.get(CmbParamsName.CRTNAM)))
                .setCny(String.valueOf(map.get(CmbParamsName.CCYNBR)))
                .setOrderAmt(String.valueOf(map.get(CmbParamsName.TRSAMT)))
                .setQueryDate(String.valueOf(map.get(CmbParamsName.EPTDAT)))
                .setQueryTime(String.valueOf(map.get(CmbParamsName.EPTTIM)))
                .setOperateDate(String.valueOf(map.get(CmbParamsName.OPRDAT)))
                .setQdsOrderNo(String.valueOf(map.get(CmbParamsName.YURREF)))
                .setRequestStatus(String.valueOf(map.get(CmbParamsName.REQSTS)))
                .setBizResult(String.valueOf(map.get(CmbParamsName.RTNFLG)))
                .setContainFile(String.valueOf(map.get(CmbParamsName.ATHFLG)))
                .setRemark(String.valueOf(map.get(CmbParamsName.RSV30Z)))
                .setErrMsg(String.valueOf(map.get(CmbParamsName.ERRTXT)));
        return data;
    }

}
