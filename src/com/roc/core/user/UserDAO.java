package com.roc.core.user;

import com.roc.core.base.BaseDAO;
import org.springframework.stereotype.Repository;

/**
 * User: rocwu
 * Date: 2016/10/24
 * Time: 11:15 am
 * Desc: 用户表数据库操作层
 */
@Repository
public class UserDAO extends BaseDAO {

    public UserDAO() {
        this.dtoType = UserDTO.class;
        this.dbName = "rocwu";
        this.tableName = "user";
        this.dbConnectConfig = null;
    }

}
