package com.atguigu.system.service;

import com.atguigu.model.system.SysPost;
import com.atguigu.model.vo.SysPostQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysPostService extends IService<SysPost> {

    IPage<SysPost> queryPostList(Long page, Long limit, SysPostQueryVo sysPostQueryVo);

    void changeStatus(String id, Integer status);
}
