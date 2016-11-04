package org.roc.wim.blog.index;

import com.roc.core.Utils.StringUtil;
import com.roc.core.base.BaseAction;
import com.roc.core.base.Page;
import org.roc.wim.blog.article.ArticleBL;
import org.roc.wim.blog.article.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: rocwu
 * Date: 2016/08/30
 * Time: 1:20
 * Desc: 首页Action类
 */
public class IndexAction extends BaseAction {

    @Autowired
    private ArticleBL articleBL;

    private Page page;

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        if (page == null)
            page = new Page();
        if (StringUtil.isEmpty(page.getOrderBy()))
            page.setOrderBy(ArticleDTO.Modify_Time + " desc");
        articleBL.getList(page);
        return SUCCESS;
    }

    public ArticleBL getArticleBL() {
        return articleBL;
    }

    public void setArticleBL(ArticleBL articleBL) {
        this.articleBL = articleBL;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
