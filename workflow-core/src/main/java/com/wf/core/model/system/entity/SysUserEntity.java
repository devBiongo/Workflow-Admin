package com.wf.core.model.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * 用户对象 sys_user
 *
 * @author Joffrey
 */
@Data
@TableName("sys_user")
public class SysUserEntity{

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Long userId;

    @TableField("dept_id")
    private Long deptId;

    @TableField("user_name")
    private String username;

    @TableField("nick_name")
    private String nickname;

    @TableField("user_type")
    private String usertype;

    private String email;

    @TableField("phonenumber")
    private String phoneNumber;

    private String sex;

    private String avatar;

    private String password;

    private String status;

    @TableField("del_Flag")
    private String delFlag;

    @TableField("login_ip")
    private String loginIp;

    @TableField("login_date")
    private Date loginDate;

    @TableField("create_by")
    private String createBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_time")
    private Date updateTime;

    private String remark;
}
