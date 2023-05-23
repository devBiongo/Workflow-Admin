package com.wf.core.mapper;

import com.wf.core.model.system.entity.SysOperateLog;

import java.util.List;

/**
 * 操作日志 数据层
 * 
 * @author Joffrey
 */
public interface SysOperateLogMapper
{
    /**
     * 新增操作日志
     * 
     * @param operateLog 操作日志对象
     */
    public void insertOperateLog(SysOperateLog operateLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperateLog> selectOperateLogList(SysOperateLog operateLog);

    /**
     * 批量删除系统操作日志
     * 
     * @param operateIds 需要删除的操作日志ID
     * @return 结果
     */
    public int deleteOperateLogByIds(Long[] operateIds);

    /**
     * 查询操作日志详细
     * 
     * @param operateId 操作ID
     * @return 操作日志对象
     */
    public SysOperateLog selectOperateLogById(Long operateId);

    /**
     * 清空操作日志
     */
    public void cleanOperateLog();
}
