package com.example.repair_severs.controller;

import com.example.repair_severs.entity.Repairrequest;
import com.example.repair_severs.entity.User;
import com.example.repair_severs.entity.msgCode;
import com.example.repair_severs.service.RepairrequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (Repairrequest)表控制层
 *
 * @author makejava
 * @since 2024-08-03 15:37:28
 */
@RestController
@RequestMapping(value = "/repairrequest", produces = "application/json;charset=utf-8")
public class RepairrequestController {
    /**
     * 服务对象
     */
    @Resource
    private RepairrequestService repairrequestService;

    /**
     * 分页查询
     *
     * @param repairrequest 筛选条件
     * @param pageable      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Repairrequest>> queryByPage(Repairrequest repairrequest, Pageable pageable) {
        return ResponseEntity.ok(this.repairrequestService.queryByPage(repairrequest, pageable));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/id")
    public ResponseEntity<Repairrequest> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.repairrequestService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param repairrequest 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Repairrequest> add(Repairrequest repairrequest) {
        return ResponseEntity.ok(this.repairrequestService.insert(repairrequest));
    }

    /**
     * 编辑数据
     *
     * @param repairrequest 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Repairrequest> edit(Repairrequest repairrequest) {
        return ResponseEntity.ok(this.repairrequestService.update(repairrequest));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.repairrequestService.deleteById(id));
    }


    @PostMapping("/myadd")
    public ResponseEntity<Repairrequest> myadd(Repairrequest repairrequest) {
        // Create a message code object
        msgCode msgCode = new msgCode();
        Repairrequest us = null;

        if (
                repairrequest.getEmployeeName() != null && !repairrequest.getEmployeeName().isEmpty() &&
                        repairrequest.getDepartment() != null && !repairrequest.getDepartment().isEmpty() &&
                        repairrequest.getContactPhone() != null && !repairrequest.getContactPhone().isEmpty() &&
                        repairrequest.getDescription() != null && !repairrequest.getDescription().isEmpty() &&
                        repairrequest.getRepairType() != null && !repairrequest.getRepairType().isEmpty() &&
                        repairrequest.getRepairLocation() != null && !repairrequest.getRepairLocation().isEmpty() &&
                        repairrequest.getRepairTitle() != null && !repairrequest.getRepairTitle().isEmpty() &&
                        repairrequest.getUserid() != null && !repairrequest.getRepairTitle().isEmpty()

        ) {


            repairrequest.setCreatedAt(new Date());
            repairrequest.setStatus("1");
            String requestId = UUID.randomUUID().toString();
            repairrequest.setRequestCode(requestId);
            us = this.repairrequestService.insert(repairrequest);
            if (us != null) {

                msgCode.setMsg("注册成功");
                System.out.println("注册成功");
                return ResponseEntity.ok(repairrequest);

            } else {

                msgCode.setMsg("注册失败");
                System.out.println("注册失败");
                return null;

            }

        }
        System.out.println("输入数据不能为空");
        return null;
    }


    @GetMapping("/usrqueryByPage")
    public ResponseEntity<Page<Repairrequest>> usrqueryByPage(Repairrequest repairrequest, Pageable pageable) {
        msgCode msgCode = new msgCode();


        List<Repairrequest> listUs = repairrequestService.queryByPage(repairrequest, pageable).getContent();
//多条查询
        if (!listUs.isEmpty()) {
            Repairrequest foundUser = listUs.get(0);
            if (foundUser.getUserid().equals(repairrequest.getUserid())) {
                System.out.println("查寻到了");
                Page<Repairrequest> page = this.repairrequestService.queryByPage(repairrequest, pageable);
                if (foundUser.getStatus().equals("1")) {
                    System.out.println("正在处理中");
                    msgCode.setMsg("正在处理中");
                    return ResponseEntity.ok(page);
                } else if (foundUser.getStatus().equals("2")) {
                    System.out.println("处理完成");
                    msgCode.setMsg("处理完成待评价");
                    return ResponseEntity.ok(page);
                } else {

                    msgCode.setMsg("评价成功");
                    System.out.println("评价成功");
                    System.out.println(" " + foundUser.getStatus());
                    return ResponseEntity.ok(page);
                }

            } else {
                System.out.println("没有历史数据");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        // 根据 userid 查询历史数据
//            Repairrequest us = this.repairrequestService.queryById(5);


//        }

        // 如果参数无效，返回 400 Bad Request 状态
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/myedit")
    public ResponseEntity<Repairrequest> myedit(Repairrequest repairrequest) {

        if (repairrequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (repairrequest.getUserid() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (repairrequest.getRequestCode() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            Pageable pageable = PageRequest.of(0, 10); // 示例分页请求，可以根据需求调整
            List<Repairrequest> listUs = repairrequestService.queryByPage(repairrequest, pageable).getContent();

            if (!listUs.isEmpty()) {
                Repairrequest foundUser = listUs.get(0);
                if (foundUser.getUserid().equals(repairrequest.getUserid()) && foundUser.getRequestCode().equals(repairrequest.getRequestCode())) {
                    System.out.println("查寻到了");
                    foundUser.setStatus("2");
                    System.out.println("修改状态成功");
                    return ResponseEntity.ok(this.repairrequestService.update(foundUser));
                } else {
                    System.out.println("没有历史数据");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            } else {
                System.out.println("list不能为空");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/pingjia")
    public ResponseEntity<Repairrequest> pingjia(Repairrequest repairrequest) {
        msgCode msgCode = new msgCode();
        Repairrequest listUs = repairrequestService.finduserid(repairrequest.getUserid());
        Repairrequest foundUser = listUs;
        if (foundUser.getStatus().equals("2")) {
            foundUser.setManyidu(repairrequest.getManyidu());
            foundUser.setFuwushudu(repairrequest.getFuwushudu());
            foundUser.setFuwuzhil(repairrequest.getFuwuzhil());
            foundUser.setStatus("3");
            repairrequestService.update(foundUser);
            msgCode.setMsg("评价成功");
            msgCode.setCode(200);
            msgCode.setObj(foundUser);
            return ResponseEntity.ok(foundUser);
        } else if (foundUser.getStatus().equals("3")) {
            System.out.println("已经处理完成");
            return ResponseEntity.ok(listUs);
        } else if (foundUser.getStatus().equals("1")) {
            System.out.println("请求正在处理中");
            return ResponseEntity.ok(listUs);
        }
        return null;

    }


    @GetMapping("/getpingjia")
    public ResponseEntity<Page<Repairrequest>> getpingjia(Repairrequest repairrequest, Pageable pageable) {

        List<Repairrequest> listUs = repairrequestService.queryByPage(repairrequest, pageable).getContent();
//多条查询
        for (Repairrequest foundUser : listUs) {
            // 检查 userid 和 status
            if (foundUser.getUserid() == repairrequest.getUserid() && "3".equals(foundUser.getStatus())) {
                Page<Repairrequest> page = this.repairrequestService.queryByPage(repairrequest, pageable);
                System.out.println("查询成功");
                return ResponseEntity.ok(page);
            }
        }
        System.out.println("没有评价数据");
        return ResponseEntity.noContent().build();
    }


}