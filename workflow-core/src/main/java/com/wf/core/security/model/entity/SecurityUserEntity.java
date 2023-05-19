package com.wf.core.security.model.entity;

import com.wf.core.model.BaseEntity;
import lombok.Data;

import java.sql.Date;

/**
 * 用户对象 sys_user
 *
 * @author Joffrey
 */
@Data
public class SecurityUserEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private int userId;

    private int deptId;

    private String username;

    private String nickname;

    private String usertype;

    private String email;

    private String phoneNumber;

    private String sex;

    private String avatar;

    private String password;

    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;
}
