package com.example.repair_severs.service;

import com.example.repair_severs.entity.Logarchive;
import com.example.repair_severs.entity.msgCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * (Logarchive)表服务接口
 *
 * @author makejava
 * @since 2024-09-15 23:19:02
 */
public interface LogarchiveService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Logarchive queryById(Integer id);

    /**
     * 分页查询
     *
     * @param logarchive 筛选条件
      * @param pageable         分页对象
     * @return 查询结果
     */
    Page<Logarchive> queryByPage(Logarchive logarchive, Pageable pageable);

    /**
     * 新增数据
     *
     * @param logarchive 实例对象
     * @return 实例对象
     */
    Logarchive insert(Logarchive logarchive);

    /**
     * 修改数据
     *
     * @param logarchive 实例对象
     * @return 实例对象
     */
    Logarchive update(Logarchive logarchive);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    /**
     * 下载文件
     * @param name
     * @param response
     * @return
     * @throws Exception
     */
    msgCode logDownload(String name, HttpServletResponse response)throws Exception;
}
