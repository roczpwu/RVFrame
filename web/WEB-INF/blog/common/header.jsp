<%--
  头部公共页
  User: rocwu
  Date: 16/8/30
  Time: 上午1:25
--%>
<%@ page import="com.roc.core.user.UserManager" %>
<%@ page import="com.roc.core.user.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="w" uri="/roc-tags.tld"%>

<%
//    Map<String,String> list = new LinkedHashMap<>();
//    list.put("首页","http://www.baidu.com");
//    list.put("关于我们","http://www.baidu.com");
//    list.put("联系我们","http://www.baidu.com");
//    list.put("登录","http://www.baidu.com");
//
//    request.setAttribute("list", list);
%>
<%--<w:navbar className="navbar navbar-inverse navbar-custom" homeTitle="Roc & Vodka" homeUrl="#" rightNavMap="${list}"/>--%>

<nav class="navbar navbar-inverse navbar-custom "><!--navbar-fixed-top-->
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index.action">Roc & Vodka</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/index.action">首页</a></li>
                <li><a href="/about.action">关于我们</a></li>
                <li><a href="/contactPage.action">联系我们</a></li>
                <%if (UserManager.isGuest()) { %>
                <li><a href="/registerPage.action">注册</a></li>
                <li><a href="/loginPage.action">登录</a></li>
                <% } else {
                    UserDTO user = UserManager.getUser();
                %>
                <li><a href="/blogEditPage.action">发表博客</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="true"><%=user.getUser_name()%>
                        <b class="caret"></b>
                    </a>
                    <ul id="w2" class="dropdown-menu">
                        <li><a href="/personView.action?user_id=<%=user.getFid()%>" tabindex="-1">
                            <i class="icon-home"></i> 个人主页
                        </a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="#" tabindex="-1">
                            <i class="icon-user"></i> 个人中心
                        </a>
                        </li>
                        <li>
                            <a href="/logout.action" data-method="post" tabindex="-1">
                                <i class="icon-signout"></i> 退出
                            </a>
                        </li>
                    </ul>
                </li>
                <% } %>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>