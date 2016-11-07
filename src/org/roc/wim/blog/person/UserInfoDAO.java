package org.roc.wim.blog.person;

import com.roc.core.base.BaseDAO;

/**
 * User: rocwu
 * Date: 2016/11/06
 * Time: 18:51
 * Desc: 用户信息数据库操作类类
 */
public class UserInfoDAO extends BaseDAO {

    public UserInfoDAO() {
        this.dtoType = UserInfoDTO.class;
        this.dbName = "rocwu";
        this.tableName = "user_info";
        this.dbConnectConfig = null;
    }

}
