package com.roc.core.user;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * User: rocwu
 * Date: 2016/10/25
 * Time: 15:07 am
 * Desc: 检查登录态的拦截器
 */
public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invoke) throws Exception {
        if (UserManager.isGuest())
            return "login";
        return invoke.invoke();
    }
}
