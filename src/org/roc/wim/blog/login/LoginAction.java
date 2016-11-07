package org.roc.wim.blog.login;

import com.roc.core.BLMessage;
import com.roc.core.Utils.WebUtil;
import com.roc.core.base.BaseAction;
import com.roc.core.user.UserManager;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;

/**
 * User: rocwu
 * Date: 2016/08/30
 * Time: 1:20
 * Desc: 登录页Action类
 */
public class LoginAction extends BaseAction {

    private String username;
    private String password;
    private boolean autoLogin;
    private BLMessage message;

    // 登录页
    public String loginPage() throws Exception {
        if (!UserManager.isGuest())
            return "index";
        return SUCCESS;
    }

    // 登录
    public String login() throws Exception {
        if (!UserManager.isGuest())
            return "index";
        message = UserManager.login(username, password, autoLogin);
        if (message.isSuccess()) {
            // 保持登录态，种cookie
            if (autoLogin)
                WebUtil.setCookie(UserManager.AUTH_CODE_KEY, (String) message.getData(), 0);
            return "index";
        }
        else {
            //getRequestMap().put("message", message);
            //getRequestMap().put("username", username);
            //getRequestMap().put("password", password);
            return INPUT;
        }
    }

    // 登出
    public String logout() throws Exception {
        if (UserManager.isGuest())
            return "index";
        BLMessage message = UserManager.loginout();
        if (message.isSuccess()) {
            return SUCCESS;
        }
        else {
            throw new RuntimeException("退出登录失败。");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public boolean getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public BLMessage getMessage() {
        return message;
    }

    public void setMessage(BLMessage message) {
        this.message = message;
    }
}
