package com.wf.general.controller;

import com.wf.core.common.annotation.Log;
import com.wf.core.common.constants.Constants;
import com.wf.core.common.enums.BusinessType;
import com.wf.core.model.response.AjaxResult;
import com.wf.core.service.SysLoginService;
import com.wf.core.service.SysMenuService;
import com.wf.general.model.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录验证
 *
 * @author Joffrey
 */
@RestController
@RequestMapping("user")
public class SysLoginController {
    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 登录方法
     *
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginVO loginVO) {
        AjaxResult ajax = AjaxResult.success();
        String token = sysLoginService.login(loginVO.getUsername(), loginVO.getPassword(), loginVO.getCode(),
                loginVO.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 注册方法
     *
     * @return 结果
     */
    @GetMapping("/register")
    public AjaxResult register() {
        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.TOKEN, sysMenuService.selectMenuTreeByUserId(1L));
        return ajax;
    }
}
