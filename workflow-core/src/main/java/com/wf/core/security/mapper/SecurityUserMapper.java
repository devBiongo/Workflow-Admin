package com.wf.core.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.core.security.model.entity.SecurityUserEntity;
import org.apache.ibatis.annotations.Param;

public interface SecurityUserMapper extends BaseMapper<SecurityUserEntity> {
    SecurityUserEntity findByUsername(@Param("username") String username);
}
