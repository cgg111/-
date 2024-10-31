package com.example.repair_severs.dao;

import com.example.repair_severs.entity.Logarchive;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Logarchive)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-15 23:19:00
 */
 @Mapper
public interface LogarchiveDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Logarchive queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param logarchive 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Logarchive> queryAllByLimit(@Param("listpm")Logarchive logarchive, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param logarchive 查询条件
     * @return 总行数
     */
    long count(Logarchive logarchive);

    /**
     * 新增数据
     *
     * @param logarchive 实例对象
     * @return 影响行数
     */
    int insert(Logarchive logarchive);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Logarchive> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Logarchive> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Logarchive> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Logarchive> entities);

    /**
     * 修改数据
     *
     * @param logarchive 实例对象
     * @return 影响行数
     */
    int update(Logarchive logarchive);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

