package com.example.repair_severs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
public class ReadUpsmingxi {

    @ExcelProperty("渠道代码")
    private String channelCode;

    @ExcelProperty("订单号")
    private String orderNumber;

    @ExcelProperty("账单总金额")
    private Double totalInvoiceAmount;

    @ExcelProperty("佣金/折扣")
    private Double commissionOrDiscount;

    @ExcelProperty("Version")
    private String version;

    @ExcelProperty("Recipient Number")
    private String recipientNumber;

    @ExcelProperty("Account Number")
    private String accountNumber;

    @ExcelProperty("Account Country")
    private String accountCountry;

    @ExcelProperty("Invoice Date")
    private String invoiceDate;

    @ExcelProperty("Invoice Number")
    private String invoiceNumber;

    @ExcelProperty("Invoice Type Code")
    private String invoiceTypeCode;

    @ExcelProperty("Invoice Type Detail Code")
    private String invoiceTypeDetailCode;

    @ExcelProperty("Account Tax ID")
    private String accountTaxId;

    @ExcelProperty("Invoice Currency Code")
    private String invoiceCurrencyCode;

    @ExcelProperty("Invoice Amount")
    private Double invoiceAmount;

    @ExcelProperty("Transaction Date")
    private String transactionDate;

    @ExcelProperty("Pickup Record Number")
    private String pickupRecordNumber;

    @ExcelProperty("Lead Shipment Number")
    private String leadShipmentNumber;

    @ExcelProperty("World Ease Number")
    private String worldEaseNumber;

    @ExcelProperty("Shipment Reference Number 1")
    private String shipmentReferenceNumber1;

    @ExcelProperty("Shipment Reference Number 2")
    private String shipmentReferenceNumber2;

    @ExcelProperty("Bill Option Code")
    private String billOptionCode;

    @ExcelProperty("Package Quantity")
    private Integer packageQuantity;

    @ExcelProperty("Oversize Quantity")
    private Integer oversizeQuantity;

    @ExcelProperty("Tracking Number")
    private String trackingNumber;

    @ExcelProperty("Package Reference Number 1")
    private String packageReferenceNumber1;

    @ExcelProperty("Package Reference Number 2")
    private String packageReferenceNumber2;

    @ExcelProperty("Package Reference Number 3")
    private String packageReferenceNumber3;

    @ExcelProperty("Package Reference Number 4")
    private String packageReferenceNumber4;

    @ExcelProperty("Package Reference Number 5")
    private String packageReferenceNumber5;

    @ExcelProperty("Entered Weight")
    private Double enteredWeight;

    @ExcelProperty("Entered Weight Unit of Measure")
    private String enteredWeightUnitOfMeasure;

    @ExcelProperty("Billed Weight")
    private Double billedWeight;

    @ExcelProperty("Billed Weight Unit of Measure")
    private String billedWeightUnitOfMeasure;

    @ExcelProperty("Container Type")
    private String containerType;

    @ExcelProperty("Billed Weight Type")
    private String billedWeightType;

    @ExcelProperty("Package Dimensions")
    private String packageDimensions;

    @ExcelProperty("Zone")
    private String zone;

    @ExcelProperty("Charge Category Code")
    private String chargeCategoryCode;

    @ExcelProperty("Charge Category Detail Code")
    private String chargeCategoryDetailCode;

    @ExcelProperty("Charge Source")
    private String chargeSource;

    @ExcelProperty("Type Code 1")
    private String typeCode1;

    @ExcelProperty("Type Detail Code 1")
    private String typeDetailCode1;

    @ExcelProperty("Type Detail Value 1")
    private String typeDetailValue1;

    @ExcelProperty("Type Code 2")
    private String typeCode2;

    @ExcelProperty("Type Detail Code 2")
    private String typeDetailCode2;

    @ExcelProperty("Type Detail Value 2")
    private String typeDetailValue2;

    @ExcelProperty("Charge Classification Code")
    private String chargeClassificationCode;

    @ExcelProperty("Charge Description Code")
    private String chargeDescriptionCode;

    @ExcelProperty("Charge Description")
    private String chargeDescription;

    @ExcelProperty("Charged Unit Quantity")
    private Integer chargedUnitQuantity;

    @ExcelProperty("Basis Currency Code")
    private String basisCurrencyCode;

    @ExcelProperty("Basis Value")
    private Double basisValue;

    @ExcelProperty("Tax Indicator")
    private String taxIndicator;

    @ExcelProperty("Transaction Currency Code")
    private String transactionCurrencyCode;

    @ExcelProperty("Incentive Amount")
    private Double incentiveAmount;

    @ExcelProperty("Net Amount")
    private Double netAmount;

    @ExcelProperty("Miscellaneous Currency Code")
    private String miscellaneousCurrencyCode;

    @ExcelProperty("Miscellaneous Incentive Amount")
    private Double miscellaneousIncentiveAmount;

    @ExcelProperty("Miscellaneous Net Amount")
    private Double miscellaneousNetAmount;

    @ExcelProperty("Alternate Invoicing Currency Code")
    private String alternateInvoicingCurrencyCode;

    @ExcelProperty("Alternate Invoice Amount")
    private Double alternateInvoiceAmount;

    @ExcelProperty("Invoice Exchange Rate")
    private Double invoiceExchangeRate;

    @ExcelProperty("Tax Variance Amount")
    private Double taxVarianceAmount;

    @ExcelProperty("Currency Variance Amount")
    private Double currencyVarianceAmount;

    @ExcelProperty("Invoice Level Charge")
    private Double invoiceLevelCharge;

    @ExcelProperty("Invoice Due Date")
    private String invoiceDueDate;

    @ExcelProperty("Alternate Invoice Number")
    private String alternateInvoiceNumber;

    @ExcelProperty("Store Number")
    private String storeNumber;

    @ExcelProperty("Customer Reference Number")
    private String customerReferenceNumber;

    @ExcelProperty("Sender Name")
    private String senderName;

    @ExcelProperty("Sender Company Name")
    private String senderCompanyName;

    @ExcelProperty("Sender Address Line 1")
    private String senderAddressLine1;

    @ExcelProperty("Sender Address Line 2")
    private String senderAddressLine2;

    @ExcelProperty("Sender City")
    private String senderCity;

    @ExcelProperty("Sender State")
    private String senderState;

    @ExcelProperty("Sender Postal")
    private String senderPostal;

    @ExcelProperty("Sender Country")
    private String senderCountry;

    @ExcelProperty("Receiver Name")
    private String receiverName;

    @ExcelProperty("Receiver Company Name")
    private String receiverCompanyName;

    @ExcelProperty("Receiver Address Line 1")
    private String receiverAddressLine1;

    @ExcelProperty("Receiver Address Line 2")
    private String receiverAddressLine2;

    @ExcelProperty("Receiver City")
    private String receiverCity;

    @ExcelProperty("Receiver State")
    private String receiverState;

    @ExcelProperty("Receiver Postal")
    private String receiverPostal;

    @ExcelProperty("Receiver Country")
    private String receiverCountry;

    // 根据需要继续添加其他字段

    // Getters and Setters
    // 可以使用 Lombok 的 @Data 来简化代码
}
