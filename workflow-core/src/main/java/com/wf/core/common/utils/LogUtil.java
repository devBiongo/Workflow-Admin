package com.wf.core.common.utils;

/**
 * 处理并记录日志文件
 * 
 * @author Joffrey
 */
public class LogUtil
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
