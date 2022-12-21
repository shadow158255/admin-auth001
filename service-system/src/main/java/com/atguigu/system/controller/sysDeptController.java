package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysDept;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.system.service.SysDeptService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-09-29
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/admin/system/sysDept")
public class sysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    //更改岗位状态
    @ApiOperation("更改部门状态")
    @GetMapping("/changeStatus/{id}/{status}")
    public Result changeStatus(@PathVariable String id,
                               @PathVariable Integer status){
        sysDeptService.changeStatus(id,status);
        return Result.ok();

    }

    //增加部门
    @ApiOperation("添加部门")
    @PostMapping("/addDept")
    public Result addDept(@RequestBody SysDept sysDept){
        boolean isSuccess = sysDeptService.save(sysDept);
        if (isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //删除部门
    @ApiOperation("删除部门")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
    sysDeptService.removeDeptById(id);
    return Result.ok();
    }

    //修改部门
    @ApiOperation("修改部门")
    @PostMapping("/updateDept")
    public Result updateDept(@RequestBody SysDept sysDept){
        boolean isSuccess = sysDeptService.updateById(sysDept);
        if (isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //查询部门
    @ApiOperation("根据id查询部门")
    @GetMapping("/selectDeptById/{id}")
    public Result selectById(@PathVariable String id){
        SysDept sysDept = sysDeptService.getById(id);
        return Result.ok(sysDept);
    }

    //条件查询部门雷彪
    @ApiOperation("查询部门列表树形结构")
    @GetMapping("/queryDeptTree")
    public Result queryPage(){
        List<SysDept> sysDepts = sysDeptService.getDeptList();
        return Result.ok(sysDepts);
    }
}
