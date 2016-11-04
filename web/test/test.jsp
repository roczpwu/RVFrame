<%@ page import="java.util.*" %>
<%@ page import="org.apache.struts2.dispatcher.Dispatcher" %>
<%--
  Created by IntelliJ IDEA.
  User: rocwu
  Date: 16/8/9
  Time: 上午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="w" uri="/roc-tags.tld"%>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>

    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <jsp:include page="/WEB-INF/require/requireValidatorCss.jsp"/>
</head>
<body>

<div class="wrap">
    <%
        Map<String,String> list = new LinkedHashMap<String,String>();
        list.put("首页","http://www.baidu.com");
        list.put("关于我们","http://www.baidu.com");
        list.put("联系我们","http://www.baidu.com");
        list.put("登录","http://www.baidu.com");
        request.setAttribute("list", list);
    %>
    <%--<w:navbar homeTitle="Roc & Vodka" homeUrl="#" rightNavMap="${list}"/>--%>

    <div class="container">
        <div class="row">
            <button onclick="test();">test</button>
        </div>

        <w:form action="/test.action" method="post" formId="test">
            <w:input name="message" label="messsage"/>
        </w:form>
    </div>


<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
<script type="text/javascript" language="JavaScript" src="/statics/user/test/js/test.js"></script>
<jsp:include page="/WEB-INF/require/requireDwr.jsp"/>
<jsp:include page="/WEB-INF/require/requireValidatorJs.jsp"/>

<script type="text/javascript">
    $(document).ready(function(){
        <w:validator formId="test"/>
    });
</script>

</body>
</html>
