package com.example.repair_severs.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
@Data
/**
 * (Repairrequest)实体类
 *
 * @author makejava
 * @since 2024-08-05 11:01:43
 */
public class Repairrequest implements Serializable {
    
    
    private Integer id;// id     
    
    private String requestCode;// 申请编号     
    
    private String employeeName;// 员工姓名     
    
    private String department;// 员工部门     
    
    private String contactPhone;// 联系电话     
    
    private String repairType;// 报修类型     
    
    private String assetCode;// 资产编号（可选)     
    
    private String assetName;// 资产名称（可选）     
    
    private String repairTitle;// 报修标题     
    
    private String repairLocation;// 报修位置     
    
    private String description;// 详细描述     
    
    private String attachment;// 附件（可以存储上传的图片或视频文件）     
    
    private Date createdAt;// 申请时间，默认为当前时间     
    
    private String status;// 处理转态     
    
    private Integer userid;// 用户id号     
    
    private String manyidu;// 用户满意度 12345 代表几颗星     
    
    private String fuwuzhil;// 服务质量 12345 代表几颗星     
    
    private String fuwushudu;// 服务速度 12345 代表几颗星 


}

