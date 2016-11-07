package org.roc.wim.blog.person;

import com.roc.core.base.BaseAction;
import com.roc.core.user.UserBL;
import com.roc.core.user.UserDTO;
import com.roc.core.user.UserManager;
import com.sun.xml.internal.ws.model.RuntimeModelerException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: rocwu
 * Date: 2016/08/30
 * Time: 1:20
 * Desc: 个人主页Action类
 */
public class PersonAction extends BaseAction {

    private int user_id;
    private UserDTO userDTO;
    private UserInfoDTO userInfoDTO;

    @Autowired
    private UserBL userBL;
    @Autowired
    private UserInfoBL userInfoBL;

    // 查看个人主页
    public String view() {
        if (user_id==0 && !UserManager.isGuest())
            user_id = UserManager.getUser().getFid();
        userDTO = (UserDTO) userBL.get(user_id);
        userInfoDTO = userInfoBL.getUserInfoById(user_id);
        if (userDTO == null || userInfoDTO == null)
            throw new RuntimeModelerException("用户不存在");
        return SUCCESS;
    }

    // 编辑个人主页
    public String editPage() {
        if (user_id != UserManager.getUser().getFid())
            throw new RuntimeException("无法修改其他人的资料");
        userDTO = (UserDTO) userBL.get(user_id);
        userInfoDTO = userInfoBL.getUserInfoById(user_id);
        if (userDTO == null || userInfoDTO == null)
            throw new RuntimeModelerException("用户不存在");
        return SUCCESS;
    }

    // 提交个人主页编辑
    public String edit() {
        userInfoDTO.setUser_id(UserManager.getUser().getFid());
        UserInfoDTO oldDTO = userInfoBL.getUserInfoById(userInfoDTO.getUser_id());
        if (oldDTO != null) userInfoBL.delete(oldDTO);
        userInfoBL.save(userInfoDTO);
        return SUCCESS;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }

    public UserBL getUserBL() {
        return userBL;
    }

    public void setUserBL(UserBL userBL) {
        this.userBL = userBL;
    }

    public UserInfoBL getUserInfoBL() {
        return userInfoBL;
    }

    public void setUserInfoBL(UserInfoBL userInfoBL) {
        this.userInfoBL = userInfoBL;
    }
}
