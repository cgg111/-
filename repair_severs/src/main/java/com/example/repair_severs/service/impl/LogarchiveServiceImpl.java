package com.example.repair_severs.service.impl;

import com.example.repair_severs.entity.Logarchive;
import com.example.repair_severs.dao.LogarchiveDao;
import com.example.repair_severs.entity.msgCode;
import com.example.repair_severs.service.LogarchiveService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * (Logarchive)表服务实现类
 *
 * @author makejava
 * @since 2024-09-15 23:19:03
 */
@Service("logarchiveService")
public class LogarchiveServiceImpl implements LogarchiveService {
    @Resource
    private LogarchiveDao logarchiveDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Logarchive queryById(Integer id) {
        return this.logarchiveDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param logarchive 筛选条件
     * @param pageable         分页对象
     * @return 查询结果
     */
    @Override
    public Page<Logarchive> queryByPage(Logarchive logarchive, Pageable pageable) {
        long total = this.logarchiveDao.count(logarchive);
        return new PageImpl<>(this.logarchiveDao.queryAllByLimit(logarchive, pageable), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param logarchive 实例对象
     * @return 实例对象
     */
    @Override
    public Logarchive insert(Logarchive logarchive) {
        this.logarchiveDao.insert(logarchive);
        return logarchive;
    }

    /**
     * 修改数据
     *
     * @param logarchive 实例对象
     * @return 实例对象
     */
    @Override
    public Logarchive update(Logarchive logarchive) {
        this.logarchiveDao.update(logarchive);
        return this.queryById(logarchive.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.logarchiveDao.deleteById(id) > 0;
    }




    /**
     * 下载文件
     * @param name
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public msgCode logDownload(String name, HttpServletResponse response) throws Exception {
        File file = new File("C:\\Users\\13042\\IdeaProjects\\repair_severs\\src\\main\\java\\com\\example\\repair_severs\\file" + File.separator + name);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, name + "文件不存在");
            return null; // 文件不存在时，不返回msgCode对象
        }

        // 声明传输内容类型
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            os.flush(); // 确保输出流被完全刷新
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件传输错误");
            return null; // 传输错误时，不返回msgCode对象
        }
        return null;
    }


}

