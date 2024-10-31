//package com.example.repair_severs.util;
//
//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.WorkbookSettings;
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.lang.reflect.Field;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.logging.Logger;
//
/////**
//// * Excel导出工具
//// * */
//
//@Slf4j
//public class OfficeUtils {
//
//    public static void main(String[] args) {
//        List<Map<String,String>> datas = new ArrayList<Map<String,String>>();
//
//        for (int i = 0; i < 10; i++) {
//            Map<String,String> data = new HashMap<String, String>();
//            data.put("用户编号", "用户编号" + i);
//            data.put("姓名", "张三" + i);
//            data.put("电话", "1532645451" + i);
//            data.put("性别", "sex" + i);
//            datas.add(data);
//
//        }
////        Logger.getGlobal().info("编辑数据:" + "55555");
//////        String path = request.getSession().getServletContext().getRealPath("/resources");
////        System.out.println(OfficeUtils.createExcel("", "D:\\111\\user.xls", "用户编号,姓名,电话,性别".split(","),
////                datas,"总共:,1700万,这是测试的".split(",")));
//    }
//    /**
//     * 发送响应流方法
//     */
//    public static void setResponseHeader(HttpServletResponse response, String fileName) {
//        try {
//            try {
//                fileName = new String(fileName.getBytes(), "ISO8859-1");
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
////            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
////            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
////            response.setHeader("X-Accel-Buffering", "no");
//
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//            response.addHeader("Pargam", "no-cache");
//          response.addHeader("Cache-Control", "no-cache");
//            response.setHeader("Content-Type","application/octet-stream");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
//    /**
//     * 创建Excel包含数据
//     * 数组都按顺序对应列名和字段值
//     * folder	  存放文件夹路径 ：download
//     * filename	 文件名   ：统计信息.xls
//     *  columnNames 列名
//     * dataSource  数据源
//     * valueNames  字段名
//     *
//     */
//    public static synchronized   String createExcel(String folder,String filename,String[] columnNames,List<Map<String,String>> datas,String[] bottom_row_str, HttpServletResponse resp){
//        try {
//
//            String saveFileName=folder+filename;
//            saveFileName=saveFileName.replaceAll("\\\\", "/").replaceAll("//", "/");
//            saveFileName=saveFileName.substring(0,saveFileName.lastIndexOf("."))+"_"+new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date())+saveFileName.substring(saveFileName.lastIndexOf("."),saveFileName.length());
//            setResponseHeader(resp, saveFileName);
//            OutputStream os = resp.getOutputStream();
/////          File file=new File("/"+saveFileName);
//           System.out.println("-----------"+saveFileName);
//            // 创建可写入的excel工作簿
//            WritableWorkbook writableWorkbook = Workbook.createWorkbook(os);
//            // 创建可写的工作表
//            WritableSheet wtSheet = writableWorkbook.createSheet("create", 0);
//            for (int i=0;i<columnNames.length;i++) {
//                wtSheet.addCell(new Label(i, 0, columnNames[i].trim()));
//            }
//            for (int j = 1; j <= datas.size(); j++) {
//                for (int i = 0; i < columnNames.length; i++) {
//                    Map<String,String> data = datas.get(j-1);
//                    wtSheet.addCell(new Label(i, j, String.valueOf(data.get(columnNames[i])==null?"":data.get(columnNames[i]))));
//                }
//            }
//            if (bottom_row_str!=null) {
//                for (int i = 0; i < bottom_row_str.length; i++) {
//                    wtSheet.addCell(new Label(i, datas.size()+1,bottom_row_str[i] ));
//                }
//            }
//            writableWorkbook.write();
//            os.flush();
//            writableWorkbook.close();
//            os.close();
//            return saveFileName;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//
////
////    public static synchronized   String createExcel(String folder,String filename,String[] columnNames,List<Map<String,String>> datas,String[] bottom_row_str){
////        try {
////            folder=folder+ ((folder.endsWith("/")||folder.endsWith("\\")) ? "" : File.separator);
////            File fold=new File("/"+folder);
////
////            //			File fold=new File(serverPath+folder);
////            if (!fold.exists()) {
////                fold.mkdirs();
////            }
////            String saveFileName=folder+filename;
////            saveFileName=saveFileName.replaceAll("\\\\", "/").replaceAll("//", "/");
////            saveFileName=saveFileName.substring(0,saveFileName.lastIndexOf("."))+"_"+new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date())+saveFileName.substring(saveFileName.lastIndexOf("."),saveFileName.length());
////
////            File file=new File("/"+saveFileName);
//////            System.out.println("-----------"+file);
////            // 创建可写入的excel工作簿
////            WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
////            // 创建可写的工作表
////            WritableSheet wtSheet = writableWorkbook.createSheet("create", 0);
////            for (int i=0;i<columnNames.length;i++) {
////                wtSheet.addCell(new Label(i, 0, columnNames[i].trim()));
////            }
////            for (int j = 1; j <= datas.size(); j++) {
////                for (int i = 0; i < columnNames.length; i++) {
////                    Map<String,String> data = datas.get(j-1);
////                    wtSheet.addCell(new Label(i, j, String.valueOf(data.get(columnNames[i])==null?"":data.get(columnNames[i]))));
////
////                }
////
////            }
////            if (bottom_row_str!=null) {
////                for (int i = 0; i < bottom_row_str.length; i++) {
////                    wtSheet.addCell(new Label(i, datas.size()+1,bottom_row_str[i] ));
////                }
////            }
////
////
////            writableWorkbook.write();
////            writableWorkbook.close();
////            return file.getPath();
////        } catch (Exception e) {
////            e.printStackTrace();
////            return null;
////        }
////    }
//
//        public static synchronized boolean createExcelTemplete(String serverPath,String folder,String filename,String[] columnNames){
//        try {
//            String savePath = serverPath+((serverPath.endsWith("/")||serverPath.endsWith("\\")) ? "" : File.separator) + folder
//                    + ((folder.endsWith("/")||folder.endsWith("\\")) ? "" : File.separator);
//
//            File fold=new File(savePath);
//            if (!fold.exists()) {
//                fold.mkdirs();
//            }
//            File file=new File(savePath+filename);
//            // 创建可写入的excel工作簿
//            WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
//            // 创建可写的工作表
//            WritableSheet wtSheet = writableWorkbook.createSheet("create", 0);
//            for (int i=0;i<columnNames.length;i++) {
//                wtSheet.addCell(new Label(i, 0, columnNames[i].trim()));
//            }
//
//            writableWorkbook.write();
//            writableWorkbook.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    /**
//     * 解析Excel 返回List对象
//     * @param <T>
//     * @param path
//     * @param clazz 需要返回的excel文件物理路径对象
//     * @param fields 需要赋值的字段，必须和excel列的中顺序相同
//     * @return
//     */
//    public static synchronized <T>List<T> parseExcel(String path,Class<T> clazz,String fields) {
//        InputStream ios =null;
//        Workbook writablebook=null;
//        File f=null;
//        try {
//            List<T> list=new ArrayList<T>();
//            T obj=null;
//
//            try {
//                ios = new BufferedInputStream(new FileInputStream(path));
//                f=new File(path);
//            } catch (Exception e) {
//                System.out.println(path+"文件不存在");
//                return null;
//            }
//            WorkbookSettings setting=new WorkbookSettings();
//            Locale local=new Locale("zh","CN");
//            setting.setLocale(local);
//            setting.setEncoding("ISO-8859-1");
//            writablebook = Workbook.getWorkbook(ios,setting);
//            Sheet sheetArray = writablebook.getSheet(0);
//            Cell[] cells = null;
//            Field field=null;
//            int rows=	sheetArray.getRows();
//            for (int i = 1; i < rows; i++) {
//                cells= sheetArray.getRow(i);
//                obj=clazz.newInstance();
//                String[] fieldstr=fields.split(",");
//                for (int j = 0; j < fieldstr.length; j++) {
//                    if(j == 3 && cells.length <= 3) {
//                        break;
//                    }
//                    if (fieldstr[j]!=null&&!fieldstr[j].trim().equals("")){
//                        field=clazz.getDeclaredField(fieldstr[j]);
//                        set(field, obj, cells[j].getContents().toString());
//                    }
//                }
//                list.add(obj);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally{
//            try {
//                if(writablebook!=null)
//                    writablebook.close();
//                if(ios!=null)
//                    ios.close();
//                if(f!=null&&f.exists())
//                    f.delete();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    /**
//     * 解析Excel为对象赋值
//     * @param field
//     * @param obj
//     * @param context
//     * @throws Exception
//     */
//    private static  void  set(Field field,Object obj,String context) throws Exception{
//        field.setAccessible(true);
//        field.set(obj, context);
//        field.setAccessible(false);
//    }
//}
