package com.example.repair_severs.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
@Data
/**
 * (Logarchive)实体类
 *
 * @author makejava
 * @since 2024-09-15 23:19:01
 */
public class Logarchive implements Serializable {

    
    private Integer id;// 主键 
    
    private String username;// 用户名 
    
    private String message;// 登录日志信息 
    
    private Date time;// 登录时间 


}

