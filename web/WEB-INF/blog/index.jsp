<%@ page import="com.roc.core.user.UserManager" %>
<%@ page import="java.util.List" %>
<%@ page import="org.roc.wim.blog.article.ArticleDTO" %>
<%@ page import="com.roc.core.base.Page" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.roc.core.Utils.TimeUtil" %>
<%@ taglib prefix="w" uri="/roc-tags.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  首页.
  User: rocwu
  Date: 2016/10/24
  Time: 下午4:02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Page mpage = (Page)request.getAttribute("page");
    List<ArticleDTO> list = mpage.getList();
%>

<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://wonderoc.com/favicon.ico" type="image/x-icon">
    <title>R&V工场</title>
    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <jsp:include page="/WEB-INF/require/requireValidatorCss.jsp"/>
    <link href="/statics/blog/css/site.css" rel="stylesheet" type="text/css">
    <link href="/statics/blog/index/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/blog/common/header.jsp"/>

<div class="container-fluid intro-header">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="site-heading">
                <h1>R&V工场</h1>
                <hr class="small">
                <span class="subheading">代码艺术碰撞的火花</span>
            </div>
        </div>
    </div>
</div>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <% for (ArticleDTO article : list) {
                String url = "/blogView.action?article.fid="+article.getFid();
                String userName = UserManager.getUserById(article.getAuthor_id()).getUser_name();
            %>
            <div class="post-preview">
                <a href="<%=url%>">
                    <h2 class="post-title">
                        <%=article.getMain_title()%>
                    </h2>
                    <h3 class="post-subtitle">
                        <%=article.getSub_title()%>
                    </h3>
                </a>
                <%
                    Calendar c =Calendar.getInstance();
                    c.setTimeInMillis(article.getCreate_time()*1000);
                    String date = TimeUtil.getYyyy_mm_dd(c);
                %>
                <p class="post-meta"><a href="#"><%=userName%></a> 发布于 <%=date%></p>
            </div>
            <% } %>

            <!-- Pager -->
            <ul class="pager">
                <%if (mpage.getCurrentPageNo() > 1) {%>
                <li class="previous">
                    <%mpage.setCurrentPageNo(mpage.getCurrentPageNo()-1);%>
                    <a href="/index.action?<%=mpage.getUrlCondition()%>">&larr; 上一页</a>
                    <%mpage.setCurrentPageNo(mpage.getCurrentPageNo()+1);%>
                </li>
                <% } %>
                <% if (mpage.getCurrentPageNo() < mpage.getTotalPageNum()) { %>
                <li class="next">
                    <%mpage.setCurrentPageNo(mpage.getCurrentPageNo()+1);%>
                    <a href="/index.action?<%=mpage.getUrlCondition()%>">下一页 &rarr;</a>
                    <%mpage.setCurrentPageNo(mpage.getCurrentPageNo()-1);%>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/blog/common/footer.jsp"/>

<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
<jsp:include page="/WEB-INF/require/requireDwr.jsp"/>
<jsp:include page="/WEB-INF/require/requireValidatorJs.jsp"/>
</body>
</html>
