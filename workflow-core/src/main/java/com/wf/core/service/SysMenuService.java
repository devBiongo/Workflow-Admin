package com.wf.core.service;

import com.wf.core.mapper.SysMenuMapper;
import com.wf.core.model.system.entity.SysMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单 业务层处理
 *
 * @author Joffrey
 */
@Service
public class SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    public List<SysMenuEntity> selectMenuTreeByUserId(Long userId) {
        List<SysMenuEntity> menus = menuMapper.selectMenuTreeByUserId(userId);
        return doFormatMenus(menus, 0);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param menus    分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuEntity> doFormatMenus(List<SysMenuEntity> menus, int parentId) {
        List<SysMenuEntity> returnList = new ArrayList<>();
        for (SysMenuEntity menu : menus) {
            if (parentId == menu.getParentId()) {
                recursionFunction(menus, menu);
                returnList.add(menu);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param menus 原材料
     * @param target 注入对象
     */
    private void recursionFunction(List<SysMenuEntity> menus, SysMenuEntity target) {
        // 得到子节点列表
        List<SysMenuEntity> childrenList = getChildrenList(menus, target);
        target.setChildren(childrenList);
        for (SysMenuEntity child : childrenList) {
            if (hasChild(menus, child)) {
                recursionFunction(menus, child);
            }
        }
    }

    /**
     * 遍历原材料获得该节点的所有子节点
     */
    private List<SysMenuEntity> getChildrenList(List<SysMenuEntity> menus, SysMenuEntity target) {
        List<SysMenuEntity> returnList = new ArrayList<>();
        for (SysMenuEntity menu : menus) {
            if (menu.getParentId().longValue() == target.getMenuId().longValue()) {
                returnList.add(menu);
            }
        }
        return returnList;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuEntity> menus, SysMenuEntity target) {
        return getChildrenList(menus, target).size() > 0;
    }
}