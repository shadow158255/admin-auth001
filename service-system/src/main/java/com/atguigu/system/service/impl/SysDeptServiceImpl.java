package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysDept;
import com.atguigu.model.system.SysPost;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.mapper.SysDeptMapper;
import com.atguigu.system.service.SysDeptService;
import com.atguigu.system.utils.DeptHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public void changeStatus(String id, Integer status) {
        //根据id查询对应岗位
        SysDept sysDept = sysDeptMapper.selectById(id);
        //更改对应的状态
        sysDept.setStatus(status);
        //将更改后的对象再存入数据库
        int updateById = sysDeptMapper.updateById(sysDept);
        if (updateById<=0){
            throw new GuiguException(555,"修改失败,请稍后再试！");
        }
    }

    @Override
    public List<SysDept> getDeptList() {
        //查询到所有部门数据
        List<SysDept> sysDepts = baseMapper.selectList(null);
        //递归算法将数据分级成前端需要数据
        List<SysDept> trees = DeptHelper.bulidTree(sysDepts);

        return trees;
    }

    @Override
    public void removeDeptById(String id) {
        //先判断当前id对应是否还有子级部门
        List<SysDept> sysDepts = sysDeptMapper.selectList(new QueryWrapper<SysDept>().eq("parent_id", id));
        if (sysDepts.size() == 0){
            sysDeptMapper.deleteById(id);
        }else {
            throw new GuiguException(204,"请先删除子部门");
        }
    }
}
