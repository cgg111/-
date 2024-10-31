package com.example.repair_severs.entity;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import java.util.Date;
@Data
public class DemoData {
    @ExcelProperty("物流单号")
    private String wuliu;
    @ExcelProperty("参考号")
    private String cankap;
    @ExcelProperty("包裹派送费用USD")
    private String baoguo;
    @ExcelProperty("渠道代码")
    private String qudao;
    @ExcelProperty("收件人")
    private String consinee;
    @ExcelProperty("收件公司")
    private String gonsi;
    @ExcelProperty("收件地址1")
    private String dizhi1;
    @ExcelProperty("收件地址2")
    private String dizhi2;
    @ExcelProperty("收件城市")
    private String chengshi;
    @ExcelProperty("收件州")
    private String zhou;
    @ExcelProperty("收件地址邮编")
    private String youbian;
    @ExcelProperty("件数")
    private String jianshu;
    @ExcelProperty("材积系数DIM")
    private String caiji;
    @ExcelProperty("实重LBS")
    private String shizhon;
    @ExcelProperty("尺寸INCHES")
    private String chicun;
    @ExcelProperty("计费重LBS")
    private String jifei;
    @ExcelProperty("发货地址邮编")
    private String fahuoyoubian;
    @ExcelProperty("邮区")
    private String youqu;
    @ExcelProperty("运费")
    private String yunfei;
    @ExcelProperty("燃油附加费")
    private String ryfujiafei;
    @ExcelProperty("住宅地址附加费")
    private String zhuzai;
    @ExcelProperty("偏远地址附加费")
    private String pianyuan;
    @ExcelProperty("额外处理费")
    private String chulifei;
    @ExcelProperty("超大超尺寸费")
    private String chaodachucun;
    @ExcelProperty("旺季附加费")
    private String wangji;
    @ExcelProperty("危险品附加费")
    private String weixianp;
    @ExcelProperty("签名费")
    private String qianm;
    @ExcelProperty("保险费")
    private String baoxianfei;
    @ExcelProperty("其他费用")
    private String qitafeiyon;
    @ExcelProperty("备注1")
    private String beizhu;
    @ExcelProperty("备注2")
    private String beizhu2;

    @ExcelProperty("承运商")
    private String carrier;

    @ExcelProperty("服务类型")
    private String serviceType;

    @ExcelProperty("渠道")
    private String channel;

    @ExcelProperty("订单号")
    private String orderNumber;

    @ExcelProperty("已支付金额USD")
    private String paidAmount;

    @ExcelProperty("实际金额USD")
    private String actualAmount;

    @ExcelProperty("赔付金额USD")
    private String compensationAmount;

    @ExcelProperty("罚款金额USD")
    private String fineAmount;

    @ExcelProperty("调整原因")
    private String adjustmentReason;

    @ExcelProperty("调整金额USD")
    private String adjustmentAmount;
}