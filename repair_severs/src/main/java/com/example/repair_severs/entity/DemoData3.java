package com.example.repair_severs.entity;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
public class DemoData3  {
    @ColumnWidth(15)
    @ExcelProperty("下单日期")
    private Data xiadanrqi;
    @ColumnWidth(10)
    @ExcelProperty("客户")
    private String kehu;
    @ColumnWidth(15)
    @ExcelProperty("注入点")
    private String zhurudian;
    @ExcelProperty("渠道")
    private String qudao;
    @ExcelProperty("服务类型")
    private String fuwuleix;
    @ExcelProperty("跟踪单号")
    private String genzondanhao;
    @ExcelProperty("客户单号")
    private String kehudanhao;
    @ExcelProperty("收货人邮编")
    private String youbian;
    @ExcelProperty("发货人邮编")
    private String consingee;
    @ExcelProperty("材积系数")
    private String caijixishu;
    @ExcelProperty("毛重（KG）")
    private String maozhon;
    @ExcelProperty("长（CM）")
    private double c;
    @ExcelProperty("宽（CM）")
    private double k;
    @ExcelProperty("高（CM）")
    private double g;
    @ExcelProperty("L(Inch)")
    private String chang;
    @ExcelProperty("W(Inch)")
    private String kuang;
    @ExcelProperty("H(Inch)")
    private String gao;
    @ExcelProperty("L+2*(W+H)")
    private double zhouc;
    @ExcelProperty("GW(LB)")
    private String awa;
    @ExcelProperty("VW(LB)")
    private String vm;
    @ExcelProperty("CW(LB/OZ)")
    private String lboz;
    @ExcelProperty("FEDEX Zone")
    private String fenqufedex;
    @ExcelProperty("USPS Zone")
    private String uspszone;
    @ColumnWidth(8)
    @ExcelProperty("FCS%")
    private String fcs;
    @ColumnWidth(8)
    @ExcelProperty("FCS")
    private String Fcscelling;
    @ExcelProperty("Base Rate")
    private String baserate;
    @ColumnWidth(8)
    @ExcelProperty("AHS")
    private String ahs;
    @ExcelProperty("AHS PSS")
    private String ahspass;

    @ExcelProperty("AHS-E\n" + "Package (Input)")
    private String input;

    @ExcelProperty("Oversize")
    private String cw;
    @ExcelProperty("Ovsize PSS")
    private String fenqu;
    @ExcelProperty("住宅附加費")
    private String zzpss;
    @ExcelProperty("PSS_住宅附加費")
    private String pss;
    @ColumnWidth(8)
    @ExcelProperty("DAS")
    private String das;
    @ExcelProperty("DAS-Extend")
    private String dasextend;
    @ExcelProperty("DAS_Remote")
    private String dasremote;
    @ExcelProperty("DAS-Alaska")
    private String das_alaska;
    @ExcelProperty("DAS-Hawaii")
    private String dashawaii;
    @ExcelProperty("Unauthorized-PSS")
    private String unauthorizedpss;
    @ExcelProperty("Unauthorized")
    private String unauthorized;
    @ExcelProperty("FEDEX Total AMT")
    private String fedextotal;
    @ExcelProperty("Base Rate")
    private String baseratebase;
    @ExcelProperty("超长附加费")
    private String chaochang;
    @ExcelProperty("不合规")
    private String buhegui;
    @ExcelProperty("超限附加费")
    private String changxian;
    @ExcelProperty("超体积附加费")
    private String chaotiji;
    @ExcelProperty("超大附加费")
    private String chaoda;
    @ExcelProperty("USPS Total AMT")
    private String uspsstotal;
    @ExcelProperty("USPS-L Total AMT")
    private String uspsl;
    @ExcelProperty("Total")
    private String total;
}
