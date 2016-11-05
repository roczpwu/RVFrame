<%--
  关于我们.
  User: rocwu
  Date: 2016/11/5
  Time: 下午7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://wonderoc.com/favicon.ico" type="image/x-icon">
    <title>关于我们</title>
    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <link href="/statics/blog/css/site.css" rel="stylesheet" type="text/css">
    <link href="/statics/blog/index/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/blog/common/header.jsp"/>

<div class="container-fluid intro-header about-header">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="site-heading">
                <h1>关于我们</h1>
                <hr class="small">
                <span class="subheading">牛刀小试</span>
            </div>
        </div>
    </div>
</div>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <p>源代码已经上传github <a href="https://github.com/roczpwu/RVFrame" target="_blank">https://github.com/roczpwu/RVFrame</a></p>
            <p>初期可能存在bug，欢迎反馈至邮箱 roczpwu@gmail.com</p>
            <p></p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/blog/common/footer.jsp"/>

<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
</body>
</html>