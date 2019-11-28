package com.shui.payment.cmb.constant;

/**
 * 参数名称汇总
 *
 * @author code
 * @Title: CmbParamsName
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1711:53 AM
 */
public class CmbParamsName {
    /**
     * 流程实例号
     */
    public static final String REQNBR = "REQNBR";
    /**
     * 业务编码
     */
    public static final String BUSCOD = "BUSCOD";
    /**
     * 业务模式
     */
    public static final String BUSMOD = "BUSMOD";
    /**
     * 转出分行号
     */
    public static final String DBTBBK = "DBTBBK";
    /**
     * 付方帐号
     */
    public static final String DBTACC = "DBTACC";
    /**
     * 收方分行号
     */
    public static final String CRTBBK = "CRTBBK";
    /**
     * 收方帐号
     */
    public static final String CRTACC = "CRTACC";
    /**
     * 收方名称
     */
    public static final String CRTNAM = "CRTNAM";
    /**
     * 币种
     */
    public static final String CCYNBR = "CCYNBR";
    /**
     * 交易金额
     */
    public static final String TRSAMT = "TRSAMT";
    /**
     * 期望日
     */
    public static final String EPTDAT = "EPTDAT";
    /**
     * 期望时间
     */
    public static final String EPTTIM = "EPTTIM";
    /**
     * 经办日
     */
    public static final String OPRDAT = "OPRDAT";
    /**
     * 对方参考号
     */
    public static final String YURREF = "YURREF";
    /**
     * 请求状态
     */
    public static final String REQSTS = "REQSTS";
    /**
     * 业务处理结果
     */
    public static final String RTNFLG = "RTNFLG";
    /**
     * 是否有附件信息
     */
    public static final String ATHFLG = "ATHFLG";


    /**
     * 保留字 30
     */
    public static final String RSV30Z = "RSV30Z";
    /**
     * 转账失败原因
     */
    public static final String ERRTXT = "ERRTXT";


    /**
     * 流水号
     */
    public static final String SQRNBR = "SQRNBR";
    /**
     * 待处理操作序列
     */
    public static final String OPRSQN = "OPRSQN";
    /**
     * 操作别名
     */
    public static final String OPRALS = "OPRALS";
    /**
     * 错误码
     */
    public static final String ERRCOD = "ERRCOD";


    /**
     * 交易日 交易发生的日期
     * ETYDAT
     */
    public static final String ETYDAT = "ETYDAT";
    /**
     * 交易时间 交易发生的时间，只有小时有效
     * ETYTIM
     */
    public static final String ETYTIM = "ETYTIM";
    /**
     * 借贷标记 C:贷； D:借
     * AMTCDR
     */
    public static final String AMTCDR = "AMTCDR";

    /**
     * 流水号 银行会计系统交易流水号
     * REFNBR
     */
    public static final String REFNBR = "REFNBR";


    /**
     * 交易类型 附录A9
     * TRSCOD
     */
    public static final String TRSCOD = "TRSCOD";
    /**
     * 摘要
     * 若为企业银行客户端经办的交易，则该字段为用途信息（ 4.0 版代发代扣业务除外）若为其它渠道经办的交易，则该字段为交易的简单说明和注解。
     * NARYUR
     */
    public static final String NARYUR = "NARYUR";
    /**
     * 借方金额企业为借方时的交易金额（正数）
     * TRSAMTD
     */
    public static final String TRSAMTD = "TRSAMTD";

    /**
     * 贷方金额 企业为贷方时的交易金额（正数）
     * TRSAMTC
     */
    public static final String TRSAMTC = "TRSAMTC";

    /**
     * 余额 帐户的联机余额
     * TRSBLV
     */
    public static final String TRSBLV = "TRSBLV";


    /**
     * 业务名称
     * BUSNAM
     */
    public static final String BUSNAM = "BUSNAM";
    /**
     * 用途
     * NUSAGE
     */
    public static final String NUSAGE = "NUSAGE";

    /**
     * 业务摘要 对业务的简单说明或注解。企业银行客户端录入的摘要信息
     * BUSNAR
     */
    public static final String BUSNAR = "BUSNAR";

    /**
     * 收/付方开户地区 收/付方帐号开户行所在地区，如北京、上海、深圳等
     * C_RPYBBK
     */
    public static final String C_RPYBBK = "C_RPYBBK";
    /**
     * 收/付方名称
     * RPYNAM
     */
    public static final String RPYNAM = "RPYNAM";
    /**
     * 收/付方账号 收/付方的转入或转出帐号
     * RPYACC
     */
    public static final String RPYACC = "RPYACC";
    /**
     * 收、付方开户行行号
     * RPYBBN
     */
    public static final String RPYBBN = "RPYBBN";
    /**
     * 收、付方开户行行名
     * RPYBNK
     */
    public static final String RPYBNK = "RPYBNK";
    /**
     * 收/付方开户行地址
     * RPYADR
     */
    public static final String RPYADR = "RPYADR";
    /**
     * 母/子公司帐号的开户行所在地区，如北京、上海、深圳等
     * C_GSBBBK
     */
    public static final String C_GSBBBK = "C_GSBBBK";
    /**
     * 母/子公司帐号
     * GSBACC
     */
    public static final String GSBACC = "GSBACC";
    /**
     * 母/子公司名称
     * GSBNAM
     */
    public static final String GSBNAM = "GSBNAM";
    /**
     * 信息标志
     * INFFLG
     */
    public static final String INFFLG = "INFFLG";
    /**
     * 票据号
     * CHKNBR
     */
    public static final String CHKNBR = "CHKNBR";
    /**
     * 冲账标志 *为冲帐， X 为补帐（冲账交易与原交易借贷相反）
     * RSVFLG
     */
    public static final String RSVFLG = "RSVFLG";
    /**
     * 扩展摘要 有效位数为 16
     * NAREXT
     */
    public static final String NAREXT = "NAREXT";

    /**
     * 币种名称
     */
    public static final String C_CCYNBR = "C_CCYNBR";
    /**
     * 科目
     */
    public static final String ACCITM = "ACCITM";
    /**
     * 分行号
     */
    public static final String BBKNBR = "BBKNBR";
    /**
     * 账号
     */
    public static final String ACCNBR = "ACCNBR";
    /**
     * 注解
     */
    public static final String ACCNAM = "ACCNAM";
    /**
     * 上日余额
     * ACCBLV
     */
    public static final String ACCBLV = "ACCBLV";
    /**
     * 联机余额
     */
    public static final String ONLBLV = "ONLBLV";
    /**
     * 冻结余额
     */
    public static final String HLDBLV = "HLDBLV";
    /**
     * 可用余额
     */
    public static final String AVLBLV = "AVLBLV";
    /**
     * 透支额度
     */
    public static final String LMTOVR = "LMTOVR";
    /**
     * 账户状态
     */
    public static final String STSCOD = "STSCOD";
    /**
     * 利息码
     */
    public static final String INTCOD = "INTCOD";
    /**
     * 利息码
     */
    public static final String C_INTRAT = "C_INTRAT";
    /**
     * 开户日
     */
    public static final String OPNDAT = "OPNDAT";
    /**
     * 到期日
     */
    public static final String MUTDAT = "MUTDAT";
    /**
     * 利率类型 A.35 利率类型码
     */
    public static final String INTTYP = "INTTYP";
    /**
     * 存期
     */
    public static final String DPSTXT = "DPSTXT";


    public static final String NTQPAYRQZ = "NTQPAYRQZ";
}
