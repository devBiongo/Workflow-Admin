package com.wf.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.core.model.system.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单表 数据层
 *
 * @author Joffrey
 */
public interface SysMenuMapper {
    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuEntity> selectMenuTreeByUserId(Long userId);
}