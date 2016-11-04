<%--
  博客编辑页.
  User: rocwu
  Date: 2016/10/25
  Time: 下午1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="w" uri="/roc-tags.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://wonderoc.com/favicon.ico" type="image/x-icon">
    <title>发表博客</title>
    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <jsp:include page="/WEB-INF/require/requireValidatorCss.jsp"/>
    <link href="/statics/blog/css/site.css" rel="stylesheet" type="text/css">
    <link href="/statics/blog/article/article.css" rel="stylesheet" type="text/css">
    <jsp:include page="/WEB-INF/require/requireSimditorCss.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/blog/common/header.jsp"/>

<div class="container-fluid intro-header">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="site-heading">
                <h1>发表博客</h1>
                <hr class="small">
                <span class="subheading">与大家分享你的所见所闻吧</span>
            </div>
        </div>
    </div>
</div>

<!-- Main Content -->
<div class="container">
    <div class="row  col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">

        <w:form action="/saveArticle.action" formId="blogForm" method="post">
            <w:input name="article.main_title" label="主标题"/>
            <w:input name="article.sub_title" label="副标题"/>
            <w:input name="article.tags" label="标签"/>
            <w:textarea name="article.content" label="正文" textAreaId="editor"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" name="login-button">发表</button>
            </div>
        </w:form>
    </div>
</div>

<jsp:include page="/WEB-INF/blog/common/footer.jsp"/>

<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
<jsp:include page="/WEB-INF/require/requireValidatorJs.jsp"/>
<jsp:include page="/WEB-INF/require/requireSimditorJs.jsp"/>

<script type="text/javascript" language="JavaScript">
    var preview, editor, mobileToolbar, toolbar;
    Simditor.locale = 'zh-CN';
    toolbar = ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment'];
    mobileToolbar = ['bold', 'underline', 'strikethrough', 'color', 'ul', 'ol'];
    editor = new Simditor({
       textarea: $('#editor'),
       placeholder: '这里输入文字...',
       toolbar: toolbar,
       pasteImage: false,
       defaultImage: '/statics/common/simditor/images/image.png',
       upload: {
           url: '/uploadArticleImg.action',
           params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
           fileKey: 'fileData', //服务器端获取文件数据的参数名
           connectionCount: 3, //总连接计数
           leaveConfirm: '上传正在进行中，你一定要离开这个页面吗？' //上传时离开页面确认
       }
    });
    function show() {
       preview = $('#preview');
       preview.html(editor.getValue())
    }

</script>

<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        <w:validator formId="blogForm"/>
    });
</script>

</body>
</html>
