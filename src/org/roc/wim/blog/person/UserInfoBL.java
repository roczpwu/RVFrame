package org.roc.wim.blog.person;

import com.roc.core.base.BaseBL;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * User: rocwu
 * Date: 2016/11/06
 * Time: 18:53
 * Desc: 用户信息业务逻辑层
 */
public class UserInfoBL extends BaseBL {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @PostConstruct
    public void init() {
        this.dao = userInfoDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    /**
     * 按照用户id获取用户信息
     * @param user_id 用户id
     * @return user_info
     */
    public UserInfoDTO getUserInfoById(int user_id) {
        return (UserInfoDTO) this.dao.
                where(UserInfoDTO.User_Id+" = "+user_id).one();
    }

}
