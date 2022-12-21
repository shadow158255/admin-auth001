package com.atguigu.system.service;

import com.atguigu.model.system.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface SysDeptService extends IService<SysDept> {
    List<SysDept> getDeptList();

    //根据id删除部门
    void removeDeptById(String id);
    //改变部门状态
    void changeStatus(String id, Integer status);
}
