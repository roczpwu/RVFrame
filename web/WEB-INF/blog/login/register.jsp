<%@ taglib prefix="w" uri="/roc-tags.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  注册页.
  User: rocwu
  Date: 2016/10/25
  Time: 下午11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://wonderoc.com/favicon.ico" type="image/x-icon">
    <title>注册</title>
    <jsp:include page="/WEB-INF/require/requireCss.jsp"/>
    <jsp:include page="/WEB-INF/require/requireValidatorCss.jsp"/>
    <link href="/statics/blog/css/site.css" rel="stylesheet" type="text/css">
    <link href="/statics/blog/login/register.css" rel="stylesheet" type="text/css">
</head>
<body>

<jsp:include page="/WEB-INF/blog/common/header.jsp"/>

<div class="container-fluid intro-header">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="site-heading">
                <h1>加入我们</h1>
                <hr class="small">
                <span class="subheading">充满奥秘的世界等你徜徉</span>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <p>请填写以下注册信息：</p>
        <div class="form-group has-feedback has-error">
            <small class="help-block">${message}</small>
        </div>
        <div class="col-lg-5">
            <w:form formId="registerForm" action="/register.action" method="post">
                <w:input name="username" label="用户名" value="${username}"/>
                <w:input name="email" label="电子邮箱" value="${email}"/>
                <w:input name="password" label="密码" type="password"/>
                <w:input name="repassword" label="确认密码" type="password"/>
                <w:captcha name="captcha" label="验证码"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" name="login-button">注册</button>
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
        $('#registerForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                'username': {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空',
                            trim: true,
                        },
                    }
                },
                'email': {
                    message: 'The email is not valid',
                    validators: {
                        emailAddress: {
                            message: '请填写email格式',
                        },
                        notEmpty: {
                            message: 'email不能为空',
                            trim: true,
                        },
                    }
                },
                password: {
                    message: 'The password is not valid',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空',
                            trim: true,
                        },
                        different: {
                            field: 'username',
                            message: '密码和用户名不能相同'
                        },
                        identical: {
                            field: 'repassword',
                            message: '两次密码输入不同'
                        },
                    }
                },
                repassword: {
                    message: 'The repassword is not valid',
                    validators: {
                        identical: {
                            field: 'password',
                            message: '两次密码输入不同'
                        },
                    }
                },
                'captcha': {
                    message: 'The captcha is not valid',
                    validators: {
                        callback: {
                            message: '验证码错误',
                            callback: function(value, validator) {
                                var ret = false;
                                htmlobj=$.ajax({
                                    url:"/captchaVerify.action?v="+$("input[name='captcha-v']").val()+"&captcha="+$("input[name='captcha']").val()+"&name=captcha",
                                    async:false,
                                    type:"json",
                                    success : function(data) {
                                        ret = JSON.parse(data);
                                        ret = ret.success;
                                    }
                                });
                                return ret;
                            },
                        }
                    }
                },
            }

        });
        $("img[name='captcha-img']").click(function () {
            var v = new Date().getTime()%1000;
            $("img[name='captcha-img']").attr('src', '/captcha.action?v='+v+'&name=captcha');
            $("input[name='captcha-v']").val(v);
            $("input[name='captcha']").val('');
        });

        <%--<w:validator formId="registerForm"/>--%>
    });
</script>

</body>
</html>
<html>
