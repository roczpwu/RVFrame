package org.roc.wim.blog.article;

import com.roc.core.base.BaseBL;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * User: rocwu
 * Date: 2016/10/24
 * Time: 23:25
 * Desc: 博客文章对象业务逻辑层
 */
public class ArticleBL extends BaseBL {

    @Autowired
    private ArticleDAO articleDAO;

    @PostConstruct
    public void init() {
        this.dao = articleDAO;
    }

    public ArticleDAO getArticleDAO() {
        return articleDAO;
    }

    public void setArticleDAO(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }
}
