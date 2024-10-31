package com.example.repair_severs.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
@Data
/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-08-03 16:11:19
 */
public class User implements Serializable {
    
    
    private Integer id;// id     
    
    private String usname;// 用户名     
    
    private String uspassword;// 用户密码     
    
    private Date usdata;// 注册时间     
    
    private String usphone;// 手机号码     
    
    private String usemail;// 电子邮件     
    
    private String uspravite;// 1代表用户2代表管理员 


}

