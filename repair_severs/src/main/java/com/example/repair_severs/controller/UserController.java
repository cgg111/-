package com.example.repair_severs.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.repair_severs.entity.*;
import com.example.repair_severs.service.LogarchiveService;
import com.example.repair_severs.service.UserService;
import com.example.repair_severs.util.DemoDataListener;
import com.example.repair_severs.util.MD5Util;
import com.example.repair_severs.util.redis_static;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2024-08-03 16:11:19
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=utf-8")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private LogarchiveService logarchiveService;

    /**
     * 分页查询
     *
     * @param user     筛选条件
     * @param pageable 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<User>> queryByPage(User user, Pageable pageable) {
        return ResponseEntity.ok(this.userService.queryByPage(user, pageable));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/id")
    public ResponseEntity<User> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<User> add(User user) {
        return ResponseEntity.ok(this.userService.insert(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<User> edit(User user) {
        return ResponseEntity.ok(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }


    @PostMapping("/adduser")
    public ResponseEntity<User> adduser(User user, Pageable pageable) {
        msgCode msgCode = new msgCode();
        User us = null;
        String encryptedPassword = MD5Util.encrypt(user.getUspassword());
        user.setUspassword(encryptedPassword);

        if (
                user.getUsname() != null && !user.getUsname().equals("") &&
                        user.getUspassword() != null && !user.getUspassword().equals("") &&
                        user.getUsemail() != null && !user.getUsemail().equals("") &&
                        user.getUsphone() != null && !user.getUsphone().equals("")
        ) {


            us = this.userService.FindByName(user);
            if (us != null) {
                msgCode.setMsg("用户名已存在");
                return null;
            } else if (us != null) {
                msgCode.setMsg("您注册的邮件已存在");
                return null;
            } else {
                user.setUsdata(new Date());
                user.setUspravite("1");
                us = this.userService.insert(user);
                if (us != null) {
                    msgCode.setMsg("注册成功");
                    System.out.println("注册成功");
                    return ResponseEntity.ok(us);
                } else {
                    msgCode.setMsg("注册失败");
                    System.out.println("注册失败");
                    return null;
                }

            }

        } else {
            msgCode.setMsg("账号或密码或邮件或手机号，不能为kon");
            System.out.println(("账号或密码或邮件或手机号，不能为kon"));
            return null;
        }


    }

    @GetMapping("/mylogin")
    public ResponseEntity<msgCode> myquerylogin(User user, Pageable pageable) {
        msgCode code = new msgCode();
        List<User> us = null;
        Logarchive logarchive = new Logarchive();
        if (user.getUsname() != null && !user.getUsname().equals("") &&
                user.getUspassword() != null && !user.getUspassword().equals("")) {

            String encryptedPassword = MD5Util.encrypt(user.getUspassword());
            user.setUspassword(encryptedPassword);
            us = this.userService.queryByPage(user, pageable).getContent();
            logarchive.setUsername(user.getUsname());
            logarchive.setTime(new Date());
            logarchive.setMessage("111");
            logarchive.setId(1);
            logarchiveService.insert(logarchive);
            if (us.size() > 0) {
                User foundUser = us.get(0);
                if (foundUser.getUsname().equals(user.getUsname()) &&
                        foundUser.getUspassword().equals(encryptedPassword)) {

                    if (foundUser.getUspravite().equals(user.getUspravite())) {
                        System.out.println("用户登录成功");
                        String tokens = UUID.randomUUID().toString();//生成token，唯一编号
                        redis_static.getIntence().addTimeout(tokens, user.getUsname(), 10000);//token和name保存到redis中
                        code.setMsg("登录成功");
                        code.setObj(us.get(0));
                        code.setCode(200);
                        code.setTokens(tokens);
                        Logger.getGlobal().info("登录成功:" + foundUser.getUsname());

                    } else {
                        System.out.println("管理员登录成功");
                        String tokens = UUID.randomUUID().toString();//生成token，唯一编号
                        redis_static.getIntence().addTimeout(tokens, user.getUsname(), 10000);//token和name保存到redis中
                        code.setMsg("登录成功");
                        code.setObj(us.get(0));
                        code.setCode(200);
                        code.setTokens(tokens);
                        Logger.getGlobal().info("管理员登录成功:" + foundUser.getUsname());
                    }
                } else {
                    code.setMsg("登录失败");
                    code.setCode(403);
                    System.out.println("登录失败");
                    Logger.getGlobal().info("登录失败:" + user.getUsname());
                }
            } else {
                code.setMsg("账号不存在");
                code.setCode(403);
                System.out.println("账号不存在");
                Logger.getGlobal().info("账号不存在:" + user.getUsname());
            }
        } else {
            code.setMsg("登录失败,账号密码为空");
            code.setCode(403);
            System.out.println("登录失败,账号密码为空");
        }
        return ResponseEntity.ok(code);
    }

    @GetMapping("/login")
    public ResponseEntity<msgCode> querylogin(@RequestBody User user, Pageable pageable) {
        msgCode code = new msgCode();
        List<User> us = null;

        // 打印输入的用户信息和分页信息
        System.out.println("User details: " + user);
        System.out.println("Pageable details: " + pageable);

        if (user.getUsname() != null && !user.getUsname().isEmpty() &&
                user.getUspassword() != null && !user.getUspassword().isEmpty()) {

            // 加密输入的密码以与存储的密码进行比较
            String encryptedPassword = MD5Util.encrypt(user.getUspassword());
            user.setUspassword(encryptedPassword);

            Page<User> userPage = this.userService.queryByPage(user, pageable);
            us = userPage.getContent();

            // 打印查询结果
            System.out.println("Query result: " + us);

            if (!us.isEmpty()) {
                User foundUser = us.get(0);
                if (foundUser.getUsname().equals(user.getUsname()) &&
                        foundUser.getUspassword().equals(encryptedPassword)) {

                    if (foundUser.getUspravite().equals(user.getUspravite())) {
                        String tokens = UUID.randomUUID().toString(); // 生成token，唯一编号
                        redis_static.getIntence().addTimeout(tokens, user.getUsname(), 10000); // token和name保存到redis中

                        code.setMsg("登录成功");
                        code.setObj(foundUser);
                        code.setCode(200);
                        code.setTokens(tokens);

                        System.out.println("用户登录成功");
                    } else {
                        System.out.println("管理员登录成功");
                        String tokens = UUID.randomUUID().toString(); // 生成token，唯一编号
                        redis_static.getIntence().addTimeout(tokens, user.getUsname(), 10000); // token和name保存到redis中

                        code.setMsg("登录成功");
                        code.setObj(foundUser);
                        code.setCode(200);
                        code.setTokens(tokens);
                    }
                } else {
                    code.setMsg("登录失败");
                    code.setCode(403);
                    System.out.println("登录失败");
                }
            } else {
                code.setMsg("账号不存在");
                code.setCode(403);
                System.out.println("账号不存在");
            }
        } else {
            code.setMsg("登录失败,账号密码为空");
            code.setCode(403);
            System.out.println("登录失败,账号密码为空");
        }

        return ResponseEntity.ok(code);
    }


    @PostMapping(value = "/uploadexcel")
    public String uploadexcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx"))) {
            throw new RuntimeException("只支持 .xls 和 .xlsx 格式的文件");
        }
        try {
            // Step 1: 读取 Excel 中的 sheet1 和 sheet2 的数据
            List<DemoData> sheet1Data = new ArrayList<>();
            List<DemoData> sheet2Data = new ArrayList<>();
            List<ReadUpsmingxi> readUpsmingxis = new ArrayList<>();
            List<Readfedexmingxi> readfedexmingxis = new ArrayList<>();
            List<ReadUspsmingxi> readUspsmingxis = new ArrayList<>();
            try (ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build()) {
                // （包裹派送）
                ReadSheet readSheet2 = buildReadSheet(1, DemoData.class, sheet1Data);
                // （fedex调整账单）
                ReadSheet fedxtiaoz = buildReadSheet(2, DemoData.class, sheet2Data);
                // （ups明细）
                ReadSheet upsmingxi = buildReadSheet(3, ReadUpsmingxi.class, readUpsmingxis);
                // （fedex明细）
                ReadSheet fedxmingxi = buildReadSheet(4, Readfedexmingxi.class, readfedexmingxis);
                // （usps明细）
                ReadSheet uspsmingxi = buildReadSheet(5, ReadUspsmingxi.class, readUspsmingxis);
                excelReader.read(readSheet2, fedxtiaoz, fedxmingxi, uspsmingxi, upsmingxi);
            }
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String excelName = URLEncoder.encode("多表导出示例", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + excelName + ".xlsx");

            //处理 Detail 数据并转换为要导出的 list
            List<DemoData3> DetailData = ListUtils.newArrayList();
            for (DemoData demoData : sheet1Data) {
                DemoData3 demoData3 = new DemoData3();
                demoData3.setQudao(demoData.getQudao());
                demoData3.setLboz(demoData.getJifei());
                demoData3.setAwa(demoData.getShizhon());
                demoData3.setFcscelling(demoData.getRyfujiafei());
                String youbian = demoData.getYoubian();
                String firstFive = youbian.substring(0, 5);
                demoData3.setYoubian(firstFive);
                demoData3.setConsingee(demoData.getFahuoyoubian());
                demoData3.setCaijixishu(demoData.getCaiji());
                demoData3.setGenzondanhao(demoData.getWuliu());
                demoData3.setKehudanhao(demoData.getCankap());
                String zone = demoData.getYouqu();
                String endr = zone.substring(zone.length() - 1);
                demoData3.setFenqufedex(endr);
                demoData3.setBaserate(demoData.getYunfei());
                demoData3.setZzpss(demoData.getZhuzai());
                demoData3.setDasremote(demoData.getPianyuan());
                demoData3.setCw(demoData.getChaodachucun());
                demoData3.setFedextotal(demoData.getBaoguo());
                demoData3.setTotal(demoData.getBaoguo());
                demoData3.setFcscelling(demoData.getChulifei());
                //处理长宽高
                String chicun = demoData.getChicun();
                if (chicun != null && chicun.contains("x")) {
                    String[] dimensions = chicun.split("x");
                    if (dimensions.length == 3) {
                        double conversionFactor = 2.54;
                        String lengthStr = dimensions[0].trim();
                        String widthStr = dimensions[1].trim();
                        String heightStr = dimensions[2].trim();
                        demoData3.setChang(dimensions[0].trim()); // 长
                        demoData3.setKuang(dimensions[1].trim());  // 宽
                        demoData3.setGao(dimensions[2].trim()); // 高
                        double length = Double.parseDouble(lengthStr);
                        double width = Double.parseDouble(widthStr);
                        double height = Double.parseDouble(heightStr);
                        // 计算公式：长 + 2 * (宽 + 高)
                        double totalSize = length + 2 * (width + height);
                        double lengthInCm = Math.ceil(length * conversionFactor);
                        double widthInCm = Math.ceil(width * conversionFactor);
                        double heightInCm = Math.ceil(height * conversionFactor);
                        demoData3.setC(lengthInCm);
                        demoData3.setK(widthInCm);
                        demoData3.setG(heightInCm);
                        demoData3.setZhouc(totalSize); // 需要在 DemoData2 中定义 totalSize 字段
                    }
                }
                DetailData.add(demoData3);
            }

            //处理账单汇总
            List<Writemuban> exportData = ListUtils.newArrayList();
            for (DemoData demoData : sheet2Data) {
                Writemuban writemuban = new Writemuban();
                writemuban.setChannel(demoData.getChannel());
                writemuban.setPaidAmount(demoData.getPaidAmount());
                writemuban.setActualInvoiceAmount(demoData.getActualAmount());
                writemuban.setCompensationAmount(demoData.getCompensationAmount());
                writemuban.setFineAmount(demoData.getFineAmount());
                writemuban.setAdjustmentReason(demoData.getAdjustmentReason());
                writemuban.setTrackingNumber(demoData.getWuliu());
                writemuban.setCustomerOrderNumber(demoData.getCankap());
                writemuban.setDinfdanhao(demoData.getOrderNumber());
                writemuban.setAdjustmentAmount(demoData.getAdjustmentAmount());
                String reason = demoData.getAdjustmentReason();
                if (!"取消件退款".equals(reason) && !"订单取消手续费".equals(reason)) {
                    String danhao = demoData.getWuliu();
                    String carrier = demoData.getCarrier();
                    switch (carrier) {
                        case "FEDEX":
                            for (Readfedexmingxi readfedexmingxi : readfedexmingxis) {
                                if (readfedexmingxi.getExpressOrGroundTrackingID().equals(danhao)) {
                                    // 设置 FedEx 明细进入模板
                                    writemuban.setActualWeight(readfedexmingxi.getActualWeightAmount());
                                    writemuban.setTotalAmount(readfedexmingxi.getTotalAmount());
                                    writemuban.setServiceType(readfedexmingxi.getServiceType());
                                    writemuban.setRatedWeight(readfedexmingxi.getRatedWeightAmount());
                                    writemuban.setLength(readfedexmingxi.getDimLength());
                                    writemuban.setHeight(readfedexmingxi.getDimHeight());
                                    writemuban.setWidth(readfedexmingxi.getDimWidth());
                                    writemuban.setCaiji(readfedexmingxi.getDimDivisor());
                                    writemuban.setShipmentDate(readfedexmingxi.getShipmentDate());
                                    writemuban.setPodDeliveryDate(readfedexmingxi.getPodDeliveryDate());
                                    double length = Double.parseDouble(readfedexmingxi.getDimLength());
                                    double width = Double.parseDouble(readfedexmingxi.getDimHeight());
                                    double height = Double.parseDouble(readfedexmingxi.getDimWidth());
                                    double totalSize = length + 2 * (width + height);
                                    writemuban.setDimensionSum(totalSize);
                                    writemuban.setFedexZone(readfedexmingxi.getZonecode());
                                    String shippercode = readfedexmingxi.getShipperZipCode();
                                    if (shippercode != null && shippercode.length() >= 5) {
                                        String firstFive = shippercode.substring(0, 5);
                                        writemuban.setShipperZipCode(firstFive);
                                    } else {
                                        writemuban.setShipperZipCode(shippercode);
                                    }
                                    String recipt = readfedexmingxi.getRecipientZipCode();
                                    if (recipt != null && recipt.length() >= 5) {
                                        String firstFive1 = recipt.substring(0, 5);
                                        writemuban.setRecipientZipCode(firstFive1);
                                    } else {
                                        writemuban.setRecipientZipCode(recipt);
                                    }
                                }
                            }
                            break;

                        case "USPS":
                            for (ReadUspsmingxi readUspsmingxi : readUspsmingxis) {
                                if (readUspsmingxi.getLogisticsNumber().equals(danhao)) {
                                    writemuban.setLength(readUspsmingxi.getPackageLengthInch());
                                    writemuban.setWidth(readUspsmingxi.getPackageWidthInch());
                                    writemuban.setHeight(readUspsmingxi.getPackageHeightInch());
                                    double length = Double.parseDouble(readUspsmingxi.getPackageLengthInch());
                                    double width = Double.parseDouble(readUspsmingxi.getPackageWidthInch());
                                    double height = Double.parseDouble(readUspsmingxi.getPackageHeightInch());
                                    double totalSize = length + 2 * (width + height);
                                    writemuban.setDimensionSum(totalSize);
                                    String shippercode = readUspsmingxi.getSenderZipCode();
                                    if (shippercode != null && shippercode.length() >= 5) {
                                        String firstFive = shippercode.substring(0, 5);
                                        writemuban.setShipperZipCode(firstFive);
                                    } else {
                                        writemuban.setShipperZipCode(shippercode);
                                    }
                                    String recipt = readUspsmingxi.getRecipientZipCode();
                                    if (recipt != null && recipt.length() >= 5) {
                                        String firstFive1 = recipt.substring(0, 5);
                                        writemuban.setRecipientZipCode(firstFive1);
                                    } else {
                                        writemuban.setRecipientZipCode(recipt);
                                    }
                                    writemuban.setUspsZone(readUspsmingxi.getZone());
                                    writemuban.setBaseRate(readUspsmingxi.getBaseShippingCost());
                                    writemuban.setOversizedVolumeSurcharge(readUspsmingxi.getOversizedVolumeSurcharge());
                                    writemuban.setAddInvoiceAmount(readUspsmingxi.getAdditionalShippingCost());
                                    writemuban.setCw(readUspsmingxi.getOrderBillingWeightLbs());
                                    writemuban.setActualWeight(readUspsmingxi.getOrderWeightLbs());
                                    writemuban.setOversize(readUspsmingxi.getOverLengthSurcharge());
                                    writemuban.setAdditionalOverLengthSurcharge(readUspsmingxi.getAdditionalOverLengthSurcharge());
                                    writemuban.setAdditionalOversizedVolumeSurcharge(readUspsmingxi.getAdditionalOversizedVolumeSurcharge());
                                    writemuban.setAdditionalPackageReviewFee(readUspsmingxi.getAdditionalPackageReviewFee());
                                    writemuban.setPenalty(readUspsmingxi.getPenalty());
                                    writemuban.setAdditionalTotal(readUspsmingxi.getAdditionalTotal());
                                    writemuban.setAdditionalCollectionDate(readUspsmingxi.getAdditionalCollectionDate());
                                    writemuban.setAdditionalRemark(readUspsmingxi.getAdditionalRemark());
                                }
                            }
                            break;

                        // 其他承运人可以在这里添加
                        default:
                            // 如果没有匹配的承运人，执行默认操作（可选）
                            break;
                    }
                } else {
                    writemuban.setAdjustmentAmount(demoData.getAdjustmentAmount());
                }

                //回表查询填充数据
                //处理fedex导入writemuban

//                for (Readfedexmingxi fedexDetail : readfedexmingxis){
//                    String danhao = demoData.getWuliu();
//                    if (fedexDetail.getExpressOrGroundTrackingID().equals(danhao)){
//                        writemuban.setChannel(fedexDetail.getChannelCode());
//                        writemuban.setPaidAmount(fedexDetail.getTotalAmount());
//                        writemuban.setActualInvoiceAmount(fedexDetail.getActualWeightAmount());
////                       writemuban.setCompensationAmount(fedexDetail.getCompensationAmount());
////                       writemuban.setFineAmount(fedexDetail.getFineAmount());
////                       writemuban.setAdjustmentReason(fedexDetail.getAdjustmentReason());
////                       writemuban.setTrackingNumber(fedexDetail.getWuliu());
////                       writemuban.setCustomerOrderNumber(fedexDetail.getCankap());
////                       writemuban.setDinfdanhao(fedexDetail.getOrderNumber());
////                       writemuban.setAdjustmentAmount(fedexDetail.getAdjustmentAmount());
//                        writemuban.setActualWeight(fedexDetail.getActualWeightAmount());
//                        writemuban.setTotalAmount(fedexDetail.getTotalAmount());
//                        writemuban.setServiceType(fedexDetail.getServiceType());
//                        writemuban.setRatedWeight(fedexDetail.getRatedWeightAmount());
//                        writemuban.setLength(fedexDetail.getDimLength());
//                        writemuban.setHeight(fedexDetail.getDimHeight());
//                        writemuban.setWidth(fedexDetail.getDimWidth());
//                        writemuban.setCaiji(fedexDetail.getDimDivisor());
//                        writemuban.setShipmentDate(fedexDetail.getShipmentDate());
//                        writemuban.setPodDeliveryDate(fedexDetail.getPodDeliveryDate());
//                        double length = Double.parseDouble(fedexDetail.getDimLength());
//                        double width = Double.parseDouble(fedexDetail.getDimHeight());
//                        double height = Double.parseDouble(fedexDetail.getDimWidth());
//                        double totalSize = length + 2 * (width + height);
//                        writemuban.setDimensionSum(totalSize);
//                        writemuban.setFedexZone(fedexDetail.getZonecode());
//
//                        String shippercode = fedexDetail.getShipperZipCode();
//                        if (shippercode != null && shippercode.length() >= 5) {
//                            String firstFive = shippercode.substring(0, 5);
//                            writemuban.setShipperZipCode(firstFive);
//                        } else {
//                            writemuban.setShipperZipCode(shippercode);
//                        }
//                        String recipt = fedexDetail.getRecipientZipCode();
//                        if (recipt != null && recipt.length() >= 5) {
//                            String firstFive1 = recipt.substring(0, 5);
//                            writemuban.setRecipientZipCode(firstFive1);
//                        } else {
//                            writemuban.setRecipientZipCode(recipt);
//                        }
//                    }
//                }
//
//                //处理usps导入writemuban
//                for (ReadUspsmingxi uspsDetail : readUspsmingxis){
//                    String danhao = demoData.getWuliu();
//                    if (uspsDetail.getLogisticsNumber().equals(danhao)){
//
////                        writemuban.setChannel(uspsDetail.getChannel());
////                        writemuban.setPaidAmount(uspsDetail.getPaidAmount());
////                        writemuban.setActualInvoiceAmount(uspsDetail.getActualAmount());
////                        writemuban.setCompensationAmount(uspsDetail.getCompensationAmount());
////                        writemuban.setFineAmount(uspsDetail.getFineAmount());
////                        writemuban.setAdjustmentReason(uspsDetail.getAdjustmentReason());
////                        writemuban.setTrackingNumber(uspsDetail.getWuliu());
////                        writemuban.setCustomerOrderNumber(uspsDetail.getCankap());
////                        writemuban.setDinfdanhao(uspsDetail.getOrderNumber());
////                        writemuban.setAdjustmentAmount(uspsDetail.getAdjustmentAmount());
//
//                        writemuban.setLength(uspsDetail.getPackageLengthInch());
//                        writemuban.setWidth(uspsDetail.getPackageWidthInch());
//                        writemuban.setHeight(uspsDetail.getPackageHeightInch());
//                        double length = Double.parseDouble(uspsDetail.getPackageLengthInch());
//                        double width = Double.parseDouble(uspsDetail.getPackageWidthInch());
//                        double height = Double.parseDouble(uspsDetail.getPackageHeightInch());
//                        double totalSize = length + 2 * (width + height);
//                        writemuban.setDimensionSum(totalSize);
//                        String shippercode = uspsDetail.getSenderZipCode();
//                        if (shippercode != null && shippercode.length() >= 5) {
//                            String firstFive = shippercode.substring(0, 5);
//                            writemuban.setShipperZipCode(firstFive);
//                        } else {
//                            writemuban.setShipperZipCode(shippercode);
//                        }
//                        String recipt = uspsDetail.getRecipientZipCode();
//                        if (recipt != null && recipt.length() >= 5) {
//                            String firstFive1 = recipt.substring(0, 5);
//                            writemuban.setRecipientZipCode(firstFive1);
//                        } else {
//                            writemuban.setRecipientZipCode(recipt);
//                        }
//                        writemuban.setUspsZone(uspsDetail.getZone());
//                        writemuban.setBaseRate(uspsDetail.getBaseShippingCost());
//                        writemuban.setOversizedVolumeSurcharge(uspsDetail.getOversizedVolumeSurcharge());
//                        writemuban.setAddInvoiceAmount(uspsDetail.getAdditionalShippingCost());
//                        writemuban.setCw(uspsDetail.getOrderBillingWeightLbs());
//                        writemuban.setActualWeight(uspsDetail.getOrderWeightLbs());
//                        writemuban.setOversize(uspsDetail.getOverLengthSurcharge());
//                        writemuban.setAdditionalOverLengthSurcharge(uspsDetail.getAdditionalOverLengthSurcharge());
//                        writemuban.setAdditionalOversizedVolumeSurcharge(uspsDetail.getAdditionalOversizedVolumeSurcharge());
//                        writemuban.setAdditionalPackageReviewFee(uspsDetail.getAdditionalPackageReviewFee());
//                        writemuban.setPenalty(uspsDetail.getPenalty());
//                        writemuban.setAdditionalTotal(uspsDetail.getAdditionalTotal());
//                        writemuban.setAdditionalCollectionDate(uspsDetail.getAdditionalCollectionDate());
//                        writemuban.setAdditionalRemark(uspsDetail.getAdditionalRemark());
//                    }
//                }
                exportData.add(writemuban);
            }
            // Step 3: 写入数据到 Excel 输出流
            try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build()) {
                // 写入第一个表（用户数据）
                WriteSheet writeSheet1 = EasyExcel.writerSheet(1, "Detail").head(DemoData3.class).build();
                excelWriter.write(DetailData, writeSheet1);
                // 写入第二个表（客户数据）
                WriteSheet writeSheet2 = EasyExcel.writerSheet(2, "账单汇总").head(Writemuban.class).build();
                excelWriter.write(exportData, writeSheet2);
            }


        } catch (IOException e) {
            throw new RuntimeException("文件处理失败", e);
        }

        return "成功";
    }


    @PostMapping("/upzhloadexcel")
    public String upzhloadexcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx"))) {
            throw new RuntimeException("只支持 .xls 和 .xlsx 格式的文件");
        }
        try {
            // Step 1: 读取正行导入的数据
            List<ReadZhmanifest> sheet1Data = new ArrayList<>();
            try (ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build()) {
                ReadSheet readSheet = buildReadSheet(0, ReadZhmanifest.class, sheet1Data);
                excelReader.read(readSheet);
            }
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 使用上传文件的原始文件名，添加后缀
            String excelName = URLEncoder.encode(fileName.replace(".xls", "").replace(".xlsx", "") + ".xlsx", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + excelName);
            List<WriteZhmanifest> DetailData = ListUtils.newArrayList();
            List<WriteMYDmanifest> DetailDataMYD = ListUtils.newArrayList();
            Map<String, Boolean> hawbNumberCount = new HashMap<>();
            //正行
            if (fileName.contains("Manifest")) {
                String[] digits = extractDigitsFromFileName(fileName);
                String firstThreeDigits = digits[0];
                String lastEightDigits = digits[1];
                for (ReadZhmanifest readZhmanifest : sheet1Data) {
                    WriteZhmanifest writeZhmanifest = new WriteZhmanifest();
                    writeZhmanifest.setAwbPrefix(firstThreeDigits);
                    writeZhmanifest.setAwbNumber(lastEightDigits);
                    String hawbNumber = readZhmanifest.getHawbNumber();
                    // 判断 hawbNumber 是否已经存在
                    if (hawbNumberCount.containsKey(hawbNumber)) {
                        // 如果存在，将该 HAWB Number 标记为重复单号
                        writeZhmanifest.setHawbNumber("重复单号"+ hawbNumber);
                    } else {
                        // 如果不存在，保留原始 HAWB Number
                        hawbNumberCount.put(hawbNumber, true); // 第一次出现时记录
                        writeZhmanifest.setHawbNumber(hawbNumber);
                    }
                    String shipperCountry = readZhmanifest.getShipperCountry();
                    if (shipperCountry != null && shipperCountry.length() > 2) {
                        writeZhmanifest.setShipperCountry("地址不符合只能是二字代码"+ shipperCountry);
                    }else {
                        writeZhmanifest.setShipperCountry(readZhmanifest.getShipperCountry());
                    }

                    String name = readZhmanifest.getConsigneeName();
                    // 使用正则表达式判断名字是否包含名和姓
                    if (name.matches("^(?=.*[a-zA-Z])([a-zA-Z][a-zA-Z.'-]*)(\\s+([a-zA-Z][a-zA-Z.'-]*))+$")) {
                        writeZhmanifest.setNameexcel("有名有姓");
                        if (name.length() > 20) {
                            name = name.substring(0, 20);  // 截取前 20 个字符
                        }
                        writeZhmanifest.setConsigneeName(name);
                    } else {
                        writeZhmanifest.setNameexcel("无名无姓");
                        if (name.length() > 20) {
                            name = name.substring(0, 20);  // 截取前 20 个字符
                            writeZhmanifest.setConsigneeName(name);
                        }
                    }
                    String Address = readZhmanifest.getConsigneeStreetAddress();
                    if (Address != null) {
                        if (Address.length() > 35) {
                            Address = Address.substring(0, 35);
                        }
                    }
                    writeZhmanifest.setConsigneeStreetAddress(Address);
                    if(isValidUSState(readZhmanifest.getConsigneeStateOrProvince())){
                        writeZhmanifest.setConsigneeStateOrProvince(readZhmanifest.getConsigneeStateOrProvince());
                    }else {
                        writeZhmanifest.setConsigneeStateOrProvince("州名不正确"+readZhmanifest.getConsigneeStateOrProvince());
                    }
                    String PostCode = readZhmanifest.getConsigneePostalCode();
                    if (PostCode != null && PostCode.length() > 5) {
                        writeZhmanifest.setConsigneePostalCode("邮编不符合只能是5位数"+ PostCode);
                    }else {
                        writeZhmanifest.setConsigneePostalCode(readZhmanifest.getConsigneePostalCode());
                    }
                    String ConsigneeCountry = readZhmanifest.getConsigneeCountry();
                    if (ConsigneeCountry != null && ConsigneeCountry.length() > 2) {
                        writeZhmanifest.setConsigneeCountry("地址不符合只能是二字代码"+ ConsigneeCountry);
                    }else {
                        writeZhmanifest.setConsigneeCountry(readZhmanifest.getConsigneeCountry());
                    }
                    populateAdditionalFields(readZhmanifest, writeZhmanifest);
                    DetailData.add(writeZhmanifest);
                }
            } else {
                String[] digits = extractDigitsFromFileName(fileName);
                String firstThreeDigits = digits[0];
                String lastEightDigits = digits[1];
                for (ReadZhmanifest readZhmanifest : sheet1Data) {
                    WriteMYDmanifest writeZhmanifest = new WriteMYDmanifest();
                    writeZhmanifest.setAwbPrefix(firstThreeDigits);
                    writeZhmanifest.setAwbNumber(lastEightDigits);
                    String hawbNumber = readZhmanifest.getHawbNumber();
                    // 判断 hawbNumber 是否已经存在
                    if (hawbNumberCount.containsKey(hawbNumber)) {
                        // 如果存在，将该 HAWB Number 标记为重复单号
                        writeZhmanifest.setHawbNumber("重复单号"+ hawbNumber);
                    } else {
                        // 如果不存在，保留原始 HAWB Number
                        hawbNumberCount.put(hawbNumber, true); // 第一次出现时记录
                        writeZhmanifest.setHawbNumber(hawbNumber);
                    }
                    String shipperCountry = readZhmanifest.getShipperCountry();
                    if (shipperCountry != null && shipperCountry.length() > 2) {
                        writeZhmanifest.setShipperCountry("地址不符合只能是二字代码"+ shipperCountry);
                    }else {
                        writeZhmanifest.setShipperCountry(readZhmanifest.getShipperCountry());
                    }
                    String name = readZhmanifest.getConsigneeName();
                    // 使用正则表达式判断名字是否包含名和姓
                    if (name.matches("^(?=.*[a-zA-Z])([a-zA-Z][a-zA-Z.'-]*)(\\s+([a-zA-Z][a-zA-Z.'-]*))+$")) {
                        writeZhmanifest.setNameexcel("有名有姓");
                        if (name.length() > 20) {
                            name = name.substring(0, 20);  // 截取前 20 个字符
                        }
                        writeZhmanifest.setConsigneeName(name);
                    } else {
                        writeZhmanifest.setNameexcel("无名无姓");
                        if (name.length() > 20) {
                            name = name.substring(0, 20);  // 截取前 20 个字符
                            writeZhmanifest.setConsigneeName(name);
                        }
                    }
                    String Address = readZhmanifest.getConsigneeStreetAddress();
                    if (Address != null) {
                        if (Address.length() > 35) {
                            Address = Address.substring(0, 35);
                        }
                    }
                    writeZhmanifest.setConsigneeStreetAddress(Address);
                    if(isValidUSState(readZhmanifest.getConsigneeStateOrProvince())){
                        writeZhmanifest.setConsigneeStateOrProvince(readZhmanifest.getConsigneeStateOrProvince());
                    }else {
                        writeZhmanifest.setConsigneeStateOrProvince("州名不正确"+readZhmanifest.getConsigneeStateOrProvince());
                    }
                    String PostCode = readZhmanifest.getConsigneePostalCode();
                    if (PostCode != null && PostCode.length() > 5) {
                        writeZhmanifest.setConsigneePostalCode("邮编不符合只能是5位数"+ PostCode);
                    }else {
                        writeZhmanifest.setConsigneePostalCode(readZhmanifest.getConsigneePostalCode());
                    }
                    String ConsigneeCountry = readZhmanifest.getConsigneeCountry();
                    if (ConsigneeCountry != null && ConsigneeCountry.length() > 2) {
                        writeZhmanifest.setConsigneeCountry("地址不符合只能是二字代码"+ ConsigneeCountry);
                    }else {
                        writeZhmanifest.setConsigneeCountry(readZhmanifest.getConsigneeCountry());
                    }
                    populateAdditionalFields(readZhmanifest, writeZhmanifest);
                    DetailDataMYD.add(writeZhmanifest);
                }
            }
            // Step 3: 写入数据到 Excel 输出流
            try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build()) {
                if (fileName.contains("Manifest")) {
                    WriteSheet writeSheet1 = EasyExcel.writerSheet(1, "001").head(WriteZhmanifest.class).build();
                    excelWriter.write(DetailData, writeSheet1);
                } else {
                    WriteSheet writeSheet1 = EasyExcel.writerSheet(1, "001").head(WriteMYDmanifest.class).build();
                    excelWriter.write(DetailDataMYD, writeSheet1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("文件处理失败", e);
        }
        return "成功";
    }


    private void populateAdditionalFields(ReadZhmanifest readZhmanifest, WriteMYDmanifest writeZhmanifest) {
        writeZhmanifest.setWaybillType(readZhmanifest.getWaybillType());
        writeZhmanifest.setShipperCity(readZhmanifest.getShipperCity());
        writeZhmanifest.setConsigneeName(readZhmanifest.getConsigneeName());
        writeZhmanifest.setConsigneeTelephoneNumber(readZhmanifest.getConsigneeTelephoneNumber());
        writeZhmanifest.setCargoPieceCount(readZhmanifest.getCargoPieceCount());
        writeZhmanifest.setCargoWeight(readZhmanifest.getCargoWeight());
        writeZhmanifest.setCargoWeightUOM(readZhmanifest.getCargoWeightUOM());
        writeZhmanifest.setCargoDescription(readZhmanifest.getCargoDescription());
        writeZhmanifest.setFdaIndicator(readZhmanifest.getFdaIndicator());
        writeZhmanifest.setIncludeType86(readZhmanifest.getIncludeType86());
        writeZhmanifest.setEntryType(readZhmanifest.getEntryType());
        writeZhmanifest.setT86DateOfArrival(readZhmanifest.getT86DateOfArrival());
        writeZhmanifest.setModeOfTransport(readZhmanifest.getModeOfTransport());
        writeZhmanifest.setBondType(readZhmanifest.getBondType());
        writeZhmanifest.setFirms(readZhmanifest.getFirms());
        writeZhmanifest.setPortOfEntry(readZhmanifest.getPortOfEntry());
        writeZhmanifest.setHtsNumber1(readZhmanifest.getHtsNumber1());
        writeZhmanifest.setDescription1(readZhmanifest.getDescription1());
        writeZhmanifest.setLineItemValue1(readZhmanifest.getLineItemValue1());
        writeZhmanifest.setCountryOfOrigin1(readZhmanifest.getCountryOfOrigin1());
        writeZhmanifest.setConsigneeStreetAddresstwo(readZhmanifest.getConsigneeStreetAddresstwo());
        writeZhmanifest.setConsigneeCity(readZhmanifest.getConsigneeCity());
        writeZhmanifest.setShipperTelephoneNumber(readZhmanifest.getShipperTelephoneNumber());
        writeZhmanifest.setAirportOfOrigin(readZhmanifest.getAirportOfOrigin());
        writeZhmanifest.setShipperName(readZhmanifest.getShipperName());
        writeZhmanifest.setShipperStreetAddress(readZhmanifest.getShipperStreetAddress());
    }

    private void populateAdditionalFields(ReadZhmanifest readZhmanifest, WriteZhmanifest writeZhmanifest) {
        writeZhmanifest.setConsigneeCity(readZhmanifest.getConsigneeCity());
        writeZhmanifest.setConsigneeTelephoneNumber(readZhmanifest.getConsigneeTelephoneNumber());
        writeZhmanifest.setShipperCity(readZhmanifest.getShipperCity());
        writeZhmanifest.setConsigneeName(readZhmanifest.getConsigneeName());
        writeZhmanifest.setCargoPieceCount(readZhmanifest.getCargoPieceCount());
        writeZhmanifest.setCargoWeight(readZhmanifest.getCargoWeight());
        writeZhmanifest.setCargoWeightUOM(readZhmanifest.getCargoWeightUOM());
        writeZhmanifest.setCargoDescription(readZhmanifest.getCargoDescription());
        writeZhmanifest.setFdaIndicator(readZhmanifest.getFdaIndicator());
        writeZhmanifest.setIncludeType86(readZhmanifest.getIncludeType86());
        writeZhmanifest.setEntryType(readZhmanifest.getEntryType());
        writeZhmanifest.setT86DateOfArrival(readZhmanifest.getT86DateOfArrival());
        writeZhmanifest.setModeOfTransport(readZhmanifest.getModeOfTransport());
        writeZhmanifest.setBondType(readZhmanifest.getBondType());
        writeZhmanifest.setFirms(readZhmanifest.getFirms());
        writeZhmanifest.setPortOfEntry(readZhmanifest.getPortOfEntry());
        writeZhmanifest.setHtsNumber1(readZhmanifest.getHtsNumber1());
        writeZhmanifest.setDescription1(readZhmanifest.getDescription1());
        writeZhmanifest.setLineItemValue1(readZhmanifest.getLineItemValue1());
        writeZhmanifest.setCountryOfOrigin1(readZhmanifest.getCountryOfOrigin1());
        writeZhmanifest.setWaybillType(readZhmanifest.getWaybillType());
        writeZhmanifest.setAirportOfOrigin(readZhmanifest.getAirportOfOrigin());
        writeZhmanifest.setShipperName(readZhmanifest.getShipperName());
        writeZhmanifest.setShipperStreetAddress(readZhmanifest.getShipperStreetAddress());
        writeZhmanifest.setShipperTelephoneNumber(readZhmanifest.getShipperTelephoneNumber());

    }

    private String[] extractDigitsFromFileName(String fileName) {
        String firstThreeDigits = "";
        String lastEightDigits = "";

        // 分割文件名
        String[] parts = fileName.split("-");
        if (parts.length > 0) {
            firstThreeDigits = parts[0].replaceAll("[^\\d]", "").substring(0, Math.min(3, parts[0].length()));
        }
        if (parts.length > 1) {
            String digitsInSecondPart = parts[1].replaceAll("[^\\d]", "");
            if (digitsInSecondPart.length() >= 8) {
                lastEightDigits = digitsInSecondPart.substring(digitsInSecondPart.length() - 8);  // 提取后八位
            }
        }

        return new String[]{firstThreeDigits, lastEightDigits};
    }

    private <T> ReadSheet buildReadSheet(int sheetNo, Class<T> clazz, List<T> dataList) {
        return EasyExcel.readSheet(sheetNo)
                .head(clazz)
                .registerReadListener(new DemoDataListener<>(dataList))
                .build();
    }

    String[] stateCodes = {
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
            "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
            "VA", "WA", "WV", "WI", "WY"
    };
    // 验证州代码是否合法的函数
    public boolean isValidUSState(String state) {
        for (String code : stateCodes) {
            if (code.equalsIgnoreCase(state)) {
                return true; // 如果匹配，则返回true
            }
        }
        return false; // 如果没有匹配项，返回false
    }








    @PostMapping("/baojiaduibi")
    public String uploadExcel(@RequestParam("file1") MultipartFile file1,
                              @RequestParam("file2") MultipartFile file2,
                              HttpServletResponse response) {
        return null;
    }
}
