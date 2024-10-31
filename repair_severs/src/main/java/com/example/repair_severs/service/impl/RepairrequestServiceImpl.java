package com.example.repair_severs.service.impl;

import com.example.repair_severs.dao.RepairrequestDao;
import com.example.repair_severs.entity.Repairrequest;
import com.example.repair_severs.service.RepairrequestService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import javax.annotation.Resource;

/**
 * (Repairrequest)表服务实现类
 *
 * @author makejava
 * @since 2024-08-03 15:37:35
 */
@Service("repairrequestService")
public class RepairrequestServiceImpl implements RepairrequestService {
    @Resource
    private RepairrequestDao repairrequestDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Repairrequest queryById(Integer id) {
        return this.repairrequestDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param repairrequest 筛选条件
     * @param pageable         分页对象
     * @return 查询结果
     */
    @Override
    public Page<Repairrequest> queryByPage(Repairrequest repairrequest, Pageable pageable) {
        long total = this.repairrequestDao.count(repairrequest);
        return new PageImpl<>(this.repairrequestDao.queryAllByLimit(repairrequest, pageable), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param repairrequest 实例对象
     * @return 实例对象
     */
    @Override
    public Repairrequest insert(Repairrequest repairrequest) {
        this.repairrequestDao.insert(repairrequest);
        return repairrequest;
    }

    /**
     * 修改数据
     *
     * @param repairrequest 实例对象
     * @return 实例对象
     */
    @Override
    public Repairrequest update(Repairrequest repairrequest) {
        this.repairrequestDao.update(repairrequest);
        return this.queryById(repairrequest.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.repairrequestDao.deleteById(id) > 0;
    }
    @Override
    public Repairrequest finduserid(Integer userid) {
        return this.repairrequestDao.finduserid(userid);
    }
}
