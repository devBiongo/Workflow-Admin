package com.wf.core.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wf.core.security.mapper.SecurityUserMapper;
import com.wf.core.security.model.entity.SecurityUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户 业务层处理
 * 
 * @author Joffrey
 */
@Service
public class SecurityUserService{
    private static final Logger log = LoggerFactory.getLogger(SecurityUserService.class);

    @Autowired
    private SecurityUserMapper securityUserMapper;

    public SecurityUserEntity selectUserByUsername(String username){
        QueryWrapper<SecurityUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return securityUserMapper.selectOne(queryWrapper);
    }

}
