package com.example.repair_severs.dao;

import com.example.repair_severs.entity.Repairrequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Repairrequest)表数据库访问层
 *
 * @author makejava
 * @since 2024-08-04 17:45:34
 */
 @Mapper
public interface RepairrequestDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Repairrequest queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param repairrequest 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Repairrequest> queryAllByLimit(@Param("listpm")Repairrequest repairrequest, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param repairrequest 查询条件
     * @return 总行数
     */
    long count(Repairrequest repairrequest);

    /**
     * 新增数据
     *
     * @param repairrequest 实例对象
     * @return 影响行数
     */
    int insert(Repairrequest repairrequest);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Repairrequest> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Repairrequest> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Repairrequest> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Repairrequest> entities);

    /**
     * 修改数据
     *
     * @param repairrequest 实例对象
     * @return 影响行数
     */
    int update(Repairrequest repairrequest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
    Repairrequest finduserid(Integer userid);
}

