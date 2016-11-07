/**
 * Created by rocwu on 2016/11/5.
 */

/**
 * 编辑文章
 * @param article_id
 */
function editArticle(article_id) {
    location.assign("/blogEditPage.action?article.fid="+article_id);
}

/**
 * 删除文章
 * @param article_id
 */
function deleteArticle(article_id) {
    $.ajax({
        type : "POST",  //提交方式  
        url : "/ajax/deleteArticle.action",
        data : {
            "article.fid" : article_id
        },//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success ) {
                location.assign("/index.action");
            } else {
                alert(result.message);
            }
        }
    });
}