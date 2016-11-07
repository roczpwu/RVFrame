<%@ page import="com.roc.core.user.UserDTO" %>
<%@ page import="org.roc.wim.blog.person.UserInfoDTO" %>
<%@ page import="com.roc.core.user.UserManager" %><%--
  个人主页展示页.
  User: rocwu
  Date: 2016/11/7
  Time: 下午1:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO user = (UserDTO) request.getAttribute("userDTO");
    UserInfoDTO userInfo = (UserInfoDTO) request.getAttribute("userInfoDTO");
%>

<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://wonderoc.com/favicon.ico" type="image/x-icon">
    <title>个人主页</title>
    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <link href="/statics/blog/css/site.css" rel="stylesheet" type="text/css">
    <link href="/statics/blog/person/person.css" rel="stylesheet" type="text/css">
</head>
<body>


<jsp:include page="/WEB-INF/blog/common/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-0 col-md-6 col-md-offset-1 ProfileCard">
            <div class="row">
                <div class="col-lg-3 col-md-3" style="text-align: center">
                    <img src="<%=userInfo.getFace_url()%>" style="width: 100px"/>
                </div>
                <div class="col-lg-9 col-md-9">
                    <div class="row title-section">
                        <span class="name">${userInfoDTO.name}</span>
                        <div class="bio ellipsis">${userInfoDTO.short_desc}</div>
                    </div>
                    <div class="row item">
                        <i class="icon icon-map-marker"></i>
                        <span class="info-wrap">
                            <span class="item">${userInfoDTO.location}</span>
                            <span class="item">${userInfoDTO.domain}</span>
                            <span class="item">
                                <%if (userInfo.getGender() == 0){%>♂<%}
                                else if (userInfo.getGender() == 1){%>♀<%}
                                else{%>保密<%}%>
                            </span>
                        </span>
                    </div>
                    <div class="row item">
                        <i class="icon icon-briefcase"></i>
                        <span class="info-wrap">
                            <span class="item">${userInfoDTO.company}</span>
                        </span>
                    </div>
                    <div class="row item">
                        <i class="icon icon-book"></i>
                        <span class="info-wrap">
                            <span class="item">${userInfoDTO.college}</span>
                        </span>
                    </div>
                    <hr class="split-line">
                    <div class="row item">
                        <span class="info-wrap">
                            ${userInfoDTO.introduction}
                        </span>
                    </div>
                    <hr class="split-line">
                    <%if (!UserManager.isGuest() && user.getFid() == UserManager.getUser().getFid()) {%>
                    <div class="row item">
                        <button class="pull-right btn-primary btn" onclick="editUserInfo(<%=user.getFid()%>)">编辑我的资料</button>
                    </div>
                    <%}%>
                </div>
            </div>


            <div class="row profile-nav">
                <a class="item active" href="#">
                    主页
                </a>
                <a class="item" href="#">
                    文章（0）
                </a>
                <a class="item" href="#">
                    收藏（0）
                </a>
            </div>

        </div>
        <div class="col-lg-4 col-md-4">
            开发中...
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/blog/common/footer.jsp"/>
<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
<script src="/statics/blog/person/person.js"></script>

</body>
</html>
<html>
