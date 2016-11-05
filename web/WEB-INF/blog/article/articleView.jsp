<%@ page import="org.roc.wim.blog.article.ArticleDTO" %>
<%@ page import="com.roc.core.Utils.StringUtil" %>
<%@ page import="com.roc.core.user.UserManager" %>
<%@ page import="com.roc.core.user.UserDTO" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.roc.core.Utils.TimeUtil" %><%--
  博客浏览页.
  User: rocwu
  Date: 2016/10/25
  Time: 下午8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="w" uri="/roc-tags.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    ArticleDTO article = (ArticleDTO) request.getAttribute("article");
    if (article.getTags() == null)
        article.setTags("");
    UserDTO author = UserManager.getUserById(article.getAuthor_id());
%>

<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://wonderoc.com/favicon.ico" type="image/x-icon">
    <title>${article.main_title}</title>
    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <jsp:include page="/WEB-INF/require/requireValidatorCss.jsp"/>
    <link href="/statics/blog/css/site.css" rel="stylesheet" type="text/css">
    <link href="/statics/blog/article/article.css" rel="stylesheet" type="text/css">
    <jsp:include page="/WEB-INF/require/requireSimditorCss.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/blog/common/header.jsp"/>

<!-- Set your background image for this header on the line below. -->
<div class="container-fluid intro-header">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="post-heading">
                <div class="tags">
                    <%
                        String[] tags = article.getTags().split(" ");
                        for (String tag : tags) {
                            if (StringUtil.isEmpty(tag)) continue;
                    %>
                    <a class="tag" href="#" title="<%=tag%>>"><%=tag%></a>
                    <% } %>
                </div>
                <h1>${article.main_title}</h1>
                <h2 class="subheading">${article.sub_title}</h2>
                <%
                    Calendar c =Calendar.getInstance();
                    c.setTimeInMillis(article.getCreate_time()*1000);
                    String date = TimeUtil.getYyyy_mm_dd(c);
                %>
                <span class="meta"><a href="#"><%=author.getUser_name()%></a> 发布于 <%=date%></span>
            </div>
        </div>
    </div>
</div>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div style="text-align: right" class="col-lg-10 col-lg-offset-1 col-md-8 col-md-offset-2">
            <span class="opera—span"> <i class="icon-star"></i>收藏</span>
            <%if (!UserManager.isGuest() && UserManager.getUser().getFid() == article.getAuthor_id()) {%>
            <span class="opera—span" onclick="editArticle(<%=article.getFid()%>)">编辑</span>
            <span class="opera—span" onclick="deleteArticle(<%=article.getFid()%>)">删除</span>
            <%}%>
            <hr/>
        </div>
        <div class="simditor-body col-lg-10 col-lg-offset-1 col-md-8 col-md-offset-2" id="preview">
            <%=article.getContent()%>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/blog/common/footer.jsp"/>

<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
<jsp:include page="/WEB-INF/require/requireValidatorJs.jsp"/>
<jsp:include page="/WEB-INF/require/requireSimditorJs.jsp"/>
<script src="/statics/blog/article/article.js"></script>

</body>
</html>

