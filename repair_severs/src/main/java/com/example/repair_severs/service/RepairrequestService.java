package com.example.repair_severs.service;

import com.example.repair_severs.entity.Repairrequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
/**
 * (Repairrequest)表服务接口
 *
 * @author makejava
 * @since 2024-08-03 15:37:34
 */
public interface RepairrequestService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Repairrequest queryById(Integer id);

    /**
     * 分页查询
     *
     * @param repairrequest 筛选条件
      * @param pageable         分页对象
     * @return 查询结果
     */
    Page<Repairrequest> queryByPage(Repairrequest repairrequest, Pageable pageable);

    /**
     * 新增数据
     *
     * @param repairrequest 实例对象
     * @return 实例对象
     */
    Repairrequest insert(Repairrequest repairrequest);

    /**
     * 修改数据
     *
     * @param repairrequest 实例对象
     * @return 实例对象
     */
    Repairrequest update(Repairrequest repairrequest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
     Repairrequest finduserid(Integer userid);
}
