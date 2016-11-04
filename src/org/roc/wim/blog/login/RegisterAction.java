package org.roc.wim.blog.login;

import com.roc.core.Utils.NumberUtil;
import com.roc.core.Utils.StringUtil;
import com.roc.core.base.BaseAction;
import com.roc.core.user.UserBL;
import com.roc.core.user.UserDTO;
import com.roc.core.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: rocwu
 * Date: 2016/08/30
 * Time: 1:20
 * Desc: 注册Action类
 */
public class RegisterAction extends BaseAction {

    @Autowired
    private UserBL userBL;

    private String username;
    private String email;
    private String password;
    private String message;
    private String captcha;

    // 注册页
    public String registerPage() {
        if (!UserManager.isGuest())
            return "index";
        return SUCCESS;
    }

    // 注册
    public String register() throws Exception {
        if (!UserManager.isGuest())
            return "index";
        UserDTO user = (UserDTO) userBL.
                getByCondition(
                        UserDTO.User_Name+"='"+ StringUtil.mysqlEscapeStr(username)+"'"
                );
        if (user != null) {
            message = "该用户名已被注册";
            return INPUT;
        }
        user = new UserDTO();
        user.setUser_name(username);
        user.setPassword_hash(StringUtil.getMd5(password));
        user.setEmail(email);
        user.setAuth_key(NumberUtil.generateUUID());
        userBL.save(user);
        UserManager.login(user.getAuth_key());
        return "index";
    }

    public UserBL getUserBL() {
        return userBL;
    }

    public void setUserBL(UserBL userBL) {
        this.userBL = userBL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
