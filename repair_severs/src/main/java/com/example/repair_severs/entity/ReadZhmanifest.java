package com.example.repair_severs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data

@ColumnWidth(25)
@HeadRowHeight(35)
public class ReadZhmanifest {
    @ExcelProperty("Waybill Type")
    private String waybillType;

    @ExcelProperty("AWB Prefix")
    private String awbPrefix;

    @ExcelProperty("AWB Number")
    private String awbNumber;

    @ExcelProperty("HAWB Number")
    private String hawbNumber;

    @ExcelProperty("Airport Of Origin")
    private String airportOfOrigin;

    @ExcelProperty("Shipper Name")
    private String shipperName;

    @ExcelProperty("Shipper Street Address")
    private String shipperStreetAddress;

    @ExcelProperty("Shipper City")
    private String shipperCity;

    @ExcelProperty("Shipper Country")
    private String shipperCountry;

    @ExcelProperty("Shipper Telephone Number")
    private String shipperTelephoneNumber;

    @ExcelProperty("Consignee Name")
    private String consigneeName;

    @ExcelProperty("Consignee Street Address")
    private String consigneeStreetAddress;

    @ExcelProperty("Consignee Street Address2")
    private String consigneeStreetAddresstwo;


    @ExcelProperty("Consignee City")
    private String consigneeCity;

    @ExcelProperty("Consignee State or Province")
    private String consigneeStateOrProvince;

    @ExcelProperty("Consignee Postal Code")
    private String consigneePostalCode;

    @ExcelProperty("Consignee Country")
    private String consigneeCountry;

    @ExcelProperty("Consignee Telephone Number")
    private String consigneeTelephoneNumber;

    @ExcelProperty("Cargo Piece Count")
    private Integer cargoPieceCount;

    @ExcelProperty("Cargo Weight")
    private Double cargoWeight;

    @ExcelProperty("Cargo Weight UOM")
    private String cargoWeightUOM;

    @ExcelProperty("Cargo Description")
    private String cargoDescription;

    @ExcelProperty("FDA Indicator")
    private Boolean fdaIndicator;

    @ExcelProperty("Include Type 86")
    private Boolean includeType86;

    @ExcelProperty("Entry Type")
    private String entryType;

    @ExcelProperty("T86 Date of Arrival")
    private String t86DateOfArrival;

    @ExcelProperty("Mode of Transport")
    private String modeOfTransport;

    @ExcelProperty("Bond Type")
    private String bondType;

    @ExcelProperty("FIRMS")
    private String firms;

    @ExcelProperty("Port of Entry")
    private String portOfEntry;

    @ExcelProperty("HTS Number 1")
    private String htsNumber1;

    @ExcelProperty("Description 1")
    private String description1;

    @ExcelProperty("Line Item Value 1")
    private Double lineItemValue1;

    @ExcelProperty("Country of Origin 1")
    private String countryOfOrigin1;
}
