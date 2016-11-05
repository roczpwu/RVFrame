<%--
  联系我们.
  User: rocwu
  Date: 2016/11/5
  Time: 下午22:07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="w" uri="/roc-tags.tld" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://wonderoc.com/favicon.ico" type="image/x-icon">
    <title>联系我们</title>
    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <jsp:include page="/WEB-INF/require/requireValidatorCss.jsp"/>
    <link href="/statics/blog/css/site.css" rel="stylesheet" type="text/css">
    <link href="/statics/blog/index/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/blog/common/header.jsp"/>

<div class="container-fluid intro-header contact-header">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="site-heading">
                <h1>联系我们</h1>
                <hr class="small">
                <span class="subheading">或许我们有你的答案</span>
            </div>
        </div>
    </div>
</div>

<!-- Main Content -->
<div class="container">
    <div class="site-contact">
        <p>
            请将您的意见或建议填在以下表单中发送给我们，谢谢！
        </p>
        <div class="row">
            <div class="col-lg-5">
                <w:form action="/ajax/submitContact" formId="contactForm" method="post">
                    <w:input name="name" label="姓名"/>
                    <w:input name="email" label="邮箱"/>
                    <w:input name="title" label="标题"/>
                    <w:textarea name="content" label="正文"/>
                    <div class="form-group">
                        <button onclick="submitContact()" type="button" class="btn btn-primary" name="submit-button">提交</button>
                    </div>
                </w:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/blog/common/footer.jsp"/>

<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
<jsp:include page="/WEB-INF/require/requireValidatorJs.jsp"/>
<script src="/statics/blog/index/contact.js"></script>

<script>
    $(document).ready(function() {
        <w:validator formId="contactForm"/>
    });
</script>
</body>
</html>
