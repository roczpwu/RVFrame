package org.roc.wim.blog.article;

import com.roc.core.base.BaseDAO;
import org.springframework.stereotype.Repository;

/**
 * User: rocwu
 * Date: 2016/10/24
 * Time: 23:23
 * Desc: 博客文章表数据库操作层
 */
@Repository
public class ArticleDAO extends BaseDAO {

    public ArticleDAO() {
        this.dtoType = ArticleDTO.class;
        this.dbName = "rocwu";
        this.tableName = "article";
        this.dbConnectConfig = null;
    }

}
