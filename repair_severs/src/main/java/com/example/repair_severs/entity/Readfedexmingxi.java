package com.example.repair_severs.entity;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
public class Readfedexmingxi {

    @ExcelProperty("渠道代码")
    private String channelCode;

    @ExcelProperty("订单号")
    private String orderNumber;

    @ExcelProperty("账单总金额")
    private String totalAmount;

    @ExcelProperty("佣金/折扣")
    private String discount;

    @ExcelProperty("Invoice Date")
    private String invoiceDate;

    @ExcelProperty("Invoice Number")
    private String invoiceNumber;

    @ExcelProperty("Store ID")
    private String storeID;

    @ExcelProperty("Original Amount Due")
    private String originalAmountDue;

    @ExcelProperty("Current Balance")
    private String currentBalance;

    @ExcelProperty("Payor")
    private String payor;

    @ExcelProperty("Ground Tracking ID Prefix")
    private String groundTrackingIDPrefix;

    @ExcelProperty("Express or Ground Tracking ID")
    private String expressOrGroundTrackingID;

    @ExcelProperty("Transportation Charge Amount")
    private String transportationChargeAmount;

    @ExcelProperty("Net Charge Amount")
    private String netChargeAmount;

    @ExcelProperty("Service Type")
    private String serviceType;

    @ExcelProperty("Ground Service")
    private String groundService;

    @ExcelProperty("Shipment Date")
    private String shipmentDate;

    @ExcelProperty("POD Delivery Date")
    private String podDeliveryDate;

    @ExcelProperty("POD Delivery Time")
    private String podDeliveryTime;

    @ExcelProperty("POD Service Area Code")
    private String podServiceAreaCode;

    @ExcelProperty("POD Signature Description")
    private String podSignatureDescription;

    @ExcelProperty("Actual Weight Amount")
    private String actualWeightAmount;

    @ExcelProperty("Actual Weight Units")
    private String actualWeightUnits;

    @ExcelProperty("Rated Weight Amount")
    private String ratedWeightAmount;

    @ExcelProperty("Rated Weight Units")
    private String ratedWeightUnits;

    @ExcelProperty("Number of Pieces")
    private String numberOfPieces;

    @ExcelProperty("Bundle Number")
    private String bundleNumber;

    @ExcelProperty("Meter Number")
    private String meterNumber;

    @ExcelProperty("TDMasterTrackingID")
    private String tdMasterTrackingID;

    @ExcelProperty("Service Packaging")
    private String servicePackaging;

    @ExcelProperty("Dim Length")
    private String dimLength;

    @ExcelProperty("Dim Width")
    private String dimWidth;

    @ExcelProperty("Dim Height")
    private String dimHeight;

    @ExcelProperty("Dim Divisor")
    private String dimDivisor;

    @ExcelProperty("Dim Unit")
    private String dimUnit;

    @ExcelProperty("Recipient Name")
    private String recipientName;

    @ExcelProperty("Recipient Company")
    private String recipientCompany;

    @ExcelProperty("Recipient Address Line 1")
    private String recipientAddressLine1;

    @ExcelProperty("Recipient Address Line 2")
    private String recipientAddressLine2;

    @ExcelProperty("Recipient City")
    private String recipientCity;

    @ExcelProperty("Recipient State")
    private String recipientState;

    @ExcelProperty("Recipient Zip Code")
    private String recipientZipCode;

    @ExcelProperty("Recipient Country/Territory")
    private String recipientCountryTerritory;

    @ExcelProperty("Shipper Company")
    private String shipperCompany;

    @ExcelProperty("Shipper Name")
    private String shipperName;

    @ExcelProperty("Shipper Address Line 1")
    private String shipperAddressLine1;

    @ExcelProperty("Shipper Address Line 2")
    private String shipperAddressLine2;

    @ExcelProperty("Shipper City")
    private String shipperCity;

    @ExcelProperty("Shipper State")
    private String shipperState;
    @ExcelProperty("Zone Code")
    private String zonecode;
    @ExcelProperty("Shipper Zip Code")
    private String shipperZipCode;

    @ExcelProperty("Shipper Country/Territory")
    private String shipperCountryTerritory;


    @ExcelProperty("Shipment Notes")
    private String shipmentNotes;



}
