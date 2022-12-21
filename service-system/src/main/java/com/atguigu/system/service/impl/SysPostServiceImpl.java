package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysMenu;
import com.atguigu.model.system.SysPost;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.mapper.SysMenuMapper;
import com.atguigu.system.mapper.SysPostMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.service.SysPostService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

    @Autowired
    private SysPostMapper sysPostMapper;

    @Override
    public IPage<SysPost> queryPostList(Long page, Long limit, SysPostQueryVo sysPostQueryVo) {
        Page<SysPost> postPage = new Page<>(page,limit);
        QueryWrapper<SysPost> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysPostQueryVo.getName())){
            wrapper.eq("name",sysPostQueryVo.getName());
        }
        if (!StringUtils.isEmpty(sysPostQueryVo.getPostCode())){
            wrapper.eq("post_code",sysPostQueryVo.getPostCode());
        }
        if (!StringUtils.isEmpty(sysPostQueryVo.getStatus())){
            wrapper.eq("status",sysPostQueryVo.getStatus());
        }
        Page<SysPost> sysPostPage = baseMapper.selectPage(postPage, wrapper);
        return sysPostPage;
    }

    @Override
    public void changeStatus(String id, Integer status) {
        //根据id查询对应岗位
        SysPost sysPost = sysPostMapper.selectById(id);
        //更改对应的状态
        sysPost.setStatus(status);
        //将更改后的对象再存入数据库
        int updateById = sysPostMapper.updateById(sysPost);
        if (updateById<=0){
            throw new GuiguException(555,"修改失败,请稍后再试！");
        }
    }
}
