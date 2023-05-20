package com.wf.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.core.model.system.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    SysUserEntity findByUsername(@Param("username") String username);
}
