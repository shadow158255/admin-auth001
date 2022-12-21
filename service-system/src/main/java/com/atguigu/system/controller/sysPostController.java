package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysPost;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.system.service.SysPostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-09-29
 */
@Api(tags = "岗位管理")
@RestController
@RequestMapping("/admin/system/sysPost")
public class sysPostController {
    @Autowired
    private SysPostService sysPostService;

    //更改岗位状态
    @ApiOperation("更改岗位状态")
    @GetMapping("/changeStatus/{id}/{status}")
    public Result changeStatus(@PathVariable String id,
                               @PathVariable Integer status){
        sysPostService.changeStatus(id,status);
        return Result.ok();

    }

    //增加岗位
    @ApiOperation("增加岗位")
    @PostMapping("/addPost")
    public Result addPost(@RequestBody SysPost sysPost){
        boolean isSuccess = sysPostService.save(sysPost);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //删除岗位
    @ApiOperation("根据id删除岗位")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        boolean isSuccess = sysPostService.removeById(id);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //修改岗位
    @ApiOperation("修改岗位")
    @PostMapping("/updatePost")
    public Result updatePost(@RequestBody SysPost sysPost){
        boolean isSuccess = sysPostService.updateById(sysPost);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //根据id查询
    @ApiOperation("根据id查询岗位")
    @GetMapping("/queryPostById/{id}")
    public Result queryPostById(@PathVariable String id){
        SysPost sysPost = sysPostService.getById(id);
        return Result.ok(sysPost);
    }

    //查询岗位列表
    @ApiOperation("查询岗位列表")
    @GetMapping("/queryPostList/{page}/{limit}")
    public Result queryPostList(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysPostQueryVo sysPostQueryVo){
        IPage<SysPost> sysPostIPage = sysPostService.queryPostList(page,limit,sysPostQueryVo);
        return Result.ok(sysPostIPage);
    }


}
