package com.wf.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wf.core.mapper.SysUserMapper;
import com.wf.core.model.system.entity.SysUserEntity;
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
public class SysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SysUserMapper securityUserMapper;

    public SysUserEntity selectUserByUsername(String username){
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return securityUserMapper.selectOne(queryWrapper);
    }

}
