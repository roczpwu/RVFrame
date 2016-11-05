package org.roc.wim.blog.article;

import com.roc.core.BLMessage;
import com.roc.core.base.BaseAction;
import com.roc.core.user.UserDTO;
import com.roc.core.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: rocwu
 * Date: 2016/10/25
 * Time: 13:21
 * Desc: 博客文章Action类
 */
public class ArticleAction extends BaseAction {

    @Autowired
    private ArticleBL articleBL;

    private ArticleDTO article;

    private BLMessage blMessage;

    // 查看博客文章
    public String view() throws Exception {
        if (article == null || article.getFid() == 0)
            throw new RuntimeException("文章已被删除");
        article = (ArticleDTO) articleBL.get(article.getFid());
        if (article == null)
            throw new RuntimeException("文章已被删除");
        return SUCCESS;
    }

    // 进入博客编辑页
    public String editPage() {
        if (article!=null && article.getFid()>0)
            article = (ArticleDTO) articleBL.get(article.getFid());
        return SUCCESS;
    }

    // 创建/保存博客文章
    public String save() {
        UserDTO user = UserManager.getUser();
        if (article.getFid() == 0) {
            article.setAuthor_id(user.getFid());
            article.setCreate_time(System.currentTimeMillis()/1000);
            article.setModify_time(System.currentTimeMillis()/1000);
        } else {
            ArticleDTO originArticle = (ArticleDTO) articleBL.get(article.getFid());
            if (article == null || originArticle.getAuthor_id() != user.getFid())
                throw new RuntimeException("无法编辑其他人的文章。");
            originArticle.setModify_time(System.currentTimeMillis()/1000);
            originArticle.setMain_title(article.getMain_title());
            originArticle.setSub_title(article.getSub_title());
            originArticle.setTags(article.getTags());
            originArticle.setContent(article.getContent());
            article = originArticle;
        }
        articleBL.save(article);
        return SUCCESS;
    }

    // 删除文章
    public String delete() throws Exception {
        blMessage = new BLMessage();
        if (article==null || (article = (ArticleDTO) articleBL.get(article.getFid())) == null) {
            blMessage.setSuccess(false);
            blMessage.setMessage("博客文章不存在。");
        } else if (article.getAuthor_id() != UserManager.getUser().getFid()) {
            blMessage.setSuccess(false);
            blMessage.setMessage("无法删除其他人的博客。");
        } else {
            if (articleBL.delete(article) > 0) {
                blMessage.setSuccess(true);
                blMessage.setMessage("删除成功。");
            } else {
                blMessage.setSuccess(false);
                blMessage.setMessage("删除失败。");
            }
        }
        return SUCCESS;
    }

    public ArticleBL getArticleBL() {
        return articleBL;
    }

    public void setArticleBL(ArticleBL articleBL) {
        this.articleBL = articleBL;
    }

    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }

    public BLMessage getBlMessage() {
        return blMessage;
    }

    public void setBlMessage(BLMessage blMessage) {
        this.blMessage = blMessage;
    }
}
