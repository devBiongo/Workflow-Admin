package com.wf.core.service;

import com.wf.core.mapper.SysOperateLogMapper;
import com.wf.core.model.system.entity.SysOperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务层处理
 * 
 * @author Joffrey
 */
@Service
public class SysOperateLogService
{
    @Autowired
    private SysOperateLogMapper sysOperateLogMapper;

    /**
     * 新增操作日志
     * 
     * @param sysOperateLog 操作日志对象
     */
    public void insertOperateLog(SysOperateLog sysOperateLog)
    {
        sysOperateLogMapper.insertOperateLog(sysOperateLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperateLog> selectOperateLogList(SysOperateLog operateLog)
    {
        return sysOperateLogMapper.selectOperateLogList(operateLog);
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param operateIds 需要删除的操作日志ID
     * @return 结果
     */
    public int deleteOperateLogByIds(Long[] operateIds)
    {
        return sysOperateLogMapper.deleteOperateLogByIds(operateIds);
    }

    /**
     * 查询操作日志详细
     * 
     * @param operateId 操作ID
     * @return 操作日志对象
     */
    public SysOperateLog selectOperateLogById(Long operateId)
    {
        return sysOperateLogMapper.selectOperateLogById(operateId);
    }

    /**
     * 清空操作日志
     */
    public void cleanOperateLog()
    {
        sysOperateLogMapper.cleanOperateLog();
    }
}
