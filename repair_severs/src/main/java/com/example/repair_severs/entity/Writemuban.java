package com.example.repair_severs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(25)
@HeadRowHeight(30)
public class Writemuban {
    @ExcelProperty("下单日期")
    private String orderDate;

    @ExcelProperty("客户")
    private String customer;

    @ExcelProperty("注入点")
    private String injectionPoint;

    @ExcelProperty("渠道")
    private String channel;

    @ExcelProperty("服务类型")
    private String serviceType;

    @ExcelProperty("订单号")
    private String dinfdanhao;

    @ExcelProperty("跟踪单号")
    private String trackingNumber;

    @ExcelProperty("客户单号")
    private String customerOrderNumber;

    @ExcelProperty("L(Inch)")
    private String length;

    @ExcelProperty("W(Inch)")
    private String width;

    @ExcelProperty("H(Inch)")
    private String height;

    @ExcelProperty("L+2*(W+H)")
    private double dimensionSum;

    @ExcelProperty("材积")
    private String caiji;

    @ExcelProperty("收件人邮编")
    private String recipientZipCode;
    @ExcelProperty("发货人邮编")
    private String shipperZipCode;
    @ExcelProperty("Shipment Date")
    private String shipmentDate;

    @ExcelProperty("POD Delivery Date")
    private String podDeliveryDate;
    @ExcelProperty("Actual Weight Amount")
    private String actualWeight;

    @ExcelProperty("VW(LB)")
    private String volumetricWeight;

    @ExcelProperty("Rated Weight Amount")
    private String ratedWeight;

    @ExcelProperty("CW")
    private String cw;

    @ExcelProperty("FEDEX Zone")
    private String fedexZone;

    @ExcelProperty("USPS Zone")
    private String uspsZone;

    @ExcelProperty("PB Zone")
    private String pbZone;

    @ExcelProperty("FCS%")
    private String fcsPercentage;

    @ExcelProperty("FCS")
    private String fcs;

    @ExcelProperty("Base Rate")
    private String baseRate;

    @ExcelProperty("AHS")
    private String ahs;

    @ExcelProperty("AHS PSS")
    private String ahsPss;

    @ExcelProperty("Oversize")
    private String oversize;

    @ExcelProperty("Ovsize PSS")
    private String ovsizePss;

    @ExcelProperty("住宅附加費")
    private String residentialSurcharge;

    @ExcelProperty("PSS_住宅附加費")
    private String pssResidentialSurcharge;

    @ExcelProperty("DAS")
    private String das;

    @ExcelProperty("DAS-Extend")
    private String dasExtend;

    @ExcelProperty("DAS-Remote")
    private String dasRemote;

    @ExcelProperty("DAS-Alaska")
    private String dasAlaska;

    @ExcelProperty("DAS-Hawaii")
    private String dasHawaii;

    @ExcelProperty("Unauthorized-PSS")
    private String unauthorizedPss;

    @ExcelProperty("Unauthorized")
    private String unauthorized;

    @ExcelProperty("FEDEX Total AMT")
    private String fedexTotalAmount;

    @ExcelProperty("超长附加费")
    private String oversizedSurcharge;

    @ExcelProperty("不合规")
    private String nonCompliance;

    @ExcelProperty("超限附加费")
    private String overLimitSurcharge;

    @ExcelProperty("超体积附加费")
    private String oversizedVolumeSurcharge;

    @ExcelProperty("超大附加费")
    private String extraLargeSurcharge;

    @ExcelProperty("USPS Total AMT")
    private String uspsTotalAmount;

    @ExcelProperty("USPS-L Total AMT")
    private String uspsLTotalAmount;

    @ExcelProperty("Total")
    private String totalAmount;

    @ExcelProperty("已支付金额")
    private String paidAmount;

    @ExcelProperty("补收金额")
    private String addInvoiceAmount;
    @ExcelProperty("实际账单金额")
    private String actualInvoiceAmount;

    @ExcelProperty("赔付金额USD")
    private String compensationAmount;

    @ExcelProperty("罚款金额USD")
    private String fineAmount;

    @ExcelProperty("地址修改")
    private String addressModification;

    @ExcelProperty("调整金额USD")
    private String adjustmentAmount;

    @ExcelProperty("调整原因")
    private String adjustmentReason;

    @ExcelProperty("补收超长/超重附加费")
    private String additionalOverLengthSurcharge;

    @ExcelProperty("补收超大体积附加费")
    private String additionalOversizedVolumeSurcharge;

    @ExcelProperty("补收包裹复核费")
    private String additionalPackageReviewFee;

    @ExcelProperty("罚金")
    private String penalty;

    @ExcelProperty("补收合计")
    private String additionalTotal;

    @ExcelProperty("补收日期")
    private String additionalCollectionDate;

    @ExcelProperty("补收备注")
    private String additionalRemark;
}
