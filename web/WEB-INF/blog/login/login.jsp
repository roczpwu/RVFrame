<%@ page import="com.roc.core.BLMessage" %>
<%@ taglib prefix="w" uri="/roc-tags.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  登录页.
  User: rocwu
  Date: 16/8/30
  Time: 上午1:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://wonderoc.com/favicon.ico" type="image/x-icon">
    <title>登录</title>
    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <jsp:include page="/WEB-INF/require/requireValidatorCss.jsp"/>
    <link href="/statics/blog/css/site.css" rel="stylesheet" type="text/css">
    <link href="/statics/blog/login/login.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%
        BLMessage message = (BLMessage) request.getAttribute("message");
    %>


    <jsp:include page="/WEB-INF/blog/common/header.jsp"/>

    <div class="container-fluid intro-header">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="site-heading">
                    <h1>启程</h1>
                    <hr class="small">
                    <span class="subheading">开始计算机艺术之旅</span>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-5">
                <w:form formId="loginForm" action="/login.action" method="post">
                    <w:input name="username" label="用户名" value="${username}"/>
                    <w:input name="password" label="密码" type="password" value="${password}"/>
                    <% if (message != null) { %>
                    <div class="form-group has-feedback has-error">
                        <small class="help-block">${message.message}</small>
                    </div>
                    <% } %>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary" name="login-button">登录</button>
                    </div>
                </w:form>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/blog/common/footer.jsp"/>

<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
<jsp:include page="/WEB-INF/require/requireDwr.jsp"/>
<jsp:include page="/WEB-INF/require/requireValidatorJs.jsp"/>

<script>
    $(document).ready(function() {
        <w:validator formId="loginForm"/>
    });
</script>

</body>
</html>
<html>
