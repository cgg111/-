package com.example.repair_severs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
public class ReadUspsmingxi {
    @ExcelProperty("订单号")
    private String orderNumber;

    @ExcelProperty("物流单号")
    private String logisticsNumber;

    @ExcelProperty("参考号")
    private String referenceNumber;

    @ExcelProperty("物流产品")
    private String logisticsProduct;

    @ExcelProperty("订单重量LBS")
    private String orderWeightLbs;

    @ExcelProperty("包裹长INCH")
    private String packageLengthInch;

    @ExcelProperty("包裹宽INCH")
    private String packageWidthInch;

    @ExcelProperty("包裹高INCH")
    private String packageHeightInch;

    @ExcelProperty("订单计费重LBS")
    private String orderBillingWeightLbs;

    @ExcelProperty("发货邮编")
    private String senderZipCode;

    @ExcelProperty("收货邮编")
    private String recipientZipCode;

    @ExcelProperty("分区")
    private String zone;

    @ExcelProperty("基础运费")
    private String baseShippingCost;

    @ExcelProperty("超长/超重附加费(超长)")
    private String overLengthSurcharge;

    @ExcelProperty("超大体积附加费")
    private String oversizedVolumeSurcharge;

    @ExcelProperty("合计")
    private String total;

    @ExcelProperty("币种")
    private String currency;

    @ExcelProperty("复核重量LBS")
    private String reviewedWeightLbs;

    @ExcelProperty("复核长INCH")
    private String reviewedLengthInch;

    @ExcelProperty("复核宽INCH")
    private String reviewedWidthInch;

    @ExcelProperty("复核高INCH")
    private String reviewedHeightInch;

    @ExcelProperty("应收运费")
    private String receivableShippingCost;

    @ExcelProperty("补收运费")
    private String additionalShippingCost;

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
