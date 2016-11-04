package org.roc.wim.blog.article;

import com.roc.core.base.BaseDTO;

/**
 * User: rocwu
 * Date: 2016/10/24
 * Time: 23:16
 * Desc: 博客文章对象类
 */
public class ArticleDTO extends BaseDTO {

    public static final String Fid          = "fid";
    public static final String Author_Id    = "author_id";
    public static final String Main_Title   = "main_title";
    public static final String Sub_Title    = "sub_title";
    public static final String Content      = "content";
    public static final String Create_Time  = "create_time";
    public static final String Modify_Time  = "modify_time";
    public static final String Tag          = "tags";

    private int fid;
    private int author_id;
    private String main_title;
    private String sub_title;
    private String content;
    private long create_time;
    private long modify_time;
    private String tags;

    public ArticleDTO() {
        this.primaryKey = new String[1];
        this.primaryKey[0] = ArticleDTO.Fid;
        this.isAutoIncrease = true;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getModify_time() {
        return modify_time;
    }

    public void setModify_time(long modify_time) {
        this.modify_time = modify_time;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
