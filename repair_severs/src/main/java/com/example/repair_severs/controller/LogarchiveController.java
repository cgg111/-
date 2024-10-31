package com.example.repair_severs.controller;

import com.example.repair_severs.entity.Logarchive;
import com.example.repair_severs.service.LogarchiveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import javax.annotation.Resource;

/**
 * (Logarchive)表控制层
 *
 * @author makejava
 * @since 2024-09-15 23:18:59
 */
@RestController
@RequestMapping(value = "logarchive", produces = "application/json;charset=utf-8")
public class LogarchiveController {
    /**
     * 服务对象
     */
    @Resource
    private LogarchiveService logarchiveService;

    /**
     * 分页查询
     *
     * @param logarchive 筛选条件
      * @param pageable         分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Logarchive>> queryByPage(Logarchive logarchive, Pageable pageable) {
        return ResponseEntity.ok(this.logarchiveService.queryByPage(logarchive, pageable));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/id")
    public ResponseEntity<Logarchive> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.logarchiveService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param logarchive 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Logarchive> add(Logarchive logarchive) {
        return ResponseEntity.ok( this.logarchiveService.insert(logarchive));
    }

    /**
     * 编辑数据
     *
     * @param logarchive 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Logarchive> edit(Logarchive logarchive) {
        return ResponseEntity.ok(this.logarchiveService.update(logarchive));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean>  deleteById(Integer id) {
        return ResponseEntity.ok(this.logarchiveService.deleteById(id));
    }


}

