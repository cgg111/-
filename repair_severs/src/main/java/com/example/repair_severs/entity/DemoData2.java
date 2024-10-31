package com.example.repair_severs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
public class DemoData2 {
    @ExcelProperty("下单日期")
    private Data xiadanrqi;
    @ExcelProperty("客户")
    private String kehu;
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
    @ExcelProperty("L(Inch)")
    private String chang;
    @ExcelProperty("W(Inch)")
    private String kuang;
    @ExcelProperty("H(Inch)")
    private String gao;
    @ExcelProperty("L+2*(W+H)")
    private double zhouc;
    @ExcelProperty("Actual Weight Amount")
    private String awa;
    @ExcelProperty("VW(LB)")
    private String vm;
    @ExcelProperty("Rated Weight Amount")
    private String rwa;
    @ExcelProperty("CW")
    private String cw;
    @ExcelProperty("0")
    private String fenqu;
    @ExcelProperty("FEDEX Zone")
    private String fenqufedex;
    @ExcelProperty("PB Zone")
    private String pbzone;
    @ExcelProperty("服务类型")
    private String fuwu;
    @ExcelProperty("FCS%")
    private String fcs;
    @ExcelProperty("FCS")
    private String Fcscelling;
    @ExcelProperty("Base Rate")
    private String baserate;
    @ExcelProperty("AHS")
    private String ahs;
    @ExcelProperty("AHS PSS")
    private String ahspass;
    @ExcelProperty("Oversize")
    private String oversizeto;
    @ExcelProperty("Ovsize PSS")
    private String ovsizepss;
    @ExcelProperty("住宅附加費")
    private String zzpss;
    @ExcelProperty("PSS_住宅附加費")
    private String pss;
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
    @ExcelProperty("已支付金额")
    private String yizhifu;
    @ExcelProperty("实际账单金额")
    private String shiji;
    @ExcelProperty("赔付金额USD")
    private String peifu;
    @ExcelProperty("罚款金额USD")
    private String fakuang;
    @ExcelProperty("地址修改")
    private String dizhi;
    @ExcelProperty("调整金额USD")
    private String tiaoz;
    @ExcelProperty("调整原因")
    private String tiaozheng;
}
