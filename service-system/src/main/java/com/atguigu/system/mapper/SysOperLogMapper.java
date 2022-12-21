package com.atguigu.system.mapper;

import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysOperLogMapper  extends BaseMapper<SysOperLog> {
   // IPage<SysOperLog> selectOperPage(@Param("vo") SysOperLogQueryVo sysOperLogQueryVo,Page<SysOperLog> page);
}
