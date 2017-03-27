<%@ page import="com.roc.core.user.UserDTO" %>
<%@ page import="org.roc.wim.blog.demoBean.UserInfoDTO" %>
<%@ page import="com.roc.core.Utils.StringUtil" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="w" uri="/roc-tags.tld" %>
<%--
  个人资料编辑页.
  User: rocwu
  Date: 2016/11/6
  Time: 下午7:53
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO user = (UserDTO) request.getAttribute("userDTO");
    UserInfoDTO userInfo = (UserInfoDTO) request.getAttribute("userInfoDTO");
    if (StringUtil.isEmpty(userInfo.getFace_url()))
        userInfo.setFace_url("/statics/blog/images/default.png");
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
    <link href="/statics/blog/demoBean/demoBean.css" rel="stylesheet" type="text/css">
    <link href="/statics/common/jcorp/css/jquery.Jcrop.css" rel="stylesheet" type="text/css">
</head>
<body>


<jsp:include page="/WEB-INF/blog/common/header.jsp"/>

<div class="container">

    <w:form action="/personEdit.action" method="post">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-0 col-md-6 col-md-offset-1 ProfileCard">
                <div class="row title-section">
                    <span class="tips">编辑个人资料</span>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">头像</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <img id="img-show" src="<%=userInfo.getFace_url()%>" class="pull-left" style="width: 100px" onclick="imgClick()"/>
                    </div>
                    <input type="hidden" id="x" name="x" />
                    <input type="hidden" id="y" name="y" />
                    <input type="hidden" id="w" name="w" />
                    <input type="hidden" id="h" name="h" />
                    <input type='file' id="imgInp" style="display: none"/>
                    <input name="userInfoDTO.face_url" type="hidden" value="<%=userInfo.getFace_url()%>"/>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">姓名</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <w:input name="userInfoDTO.name" value="<%=userInfo.getName()%>"/>
                    </div>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">性别</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <%
                            int gender = userInfo.getGender();
                            if (gender!=0&&gender!=1) gender=2;
                        %>
                        <label class="radio-inline">
                            <input type="radio" name="userInfoDTO.gender" id="inlineRadio1" value="0" <%if(gender==0){%>checked<%}%>> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="userInfoDTO.gender" id="inlineRadio2" value="1" <%if(gender==1){%>checked<%}%>> 女
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="userInfoDTO.gender" id="inlineRadio3" value="2" <%if(gender==2){%>checked<%}%>> 保密
                        </label>
                    </div>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">一句话介绍</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <w:input name="userInfoDTO.short_desc" value="<%=userInfo.getShort_desc()%>"/>
                    </div>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">个人简介</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <w:textarea name="userInfoDTO.introduction" value="<%=userInfo.getIntroduction()%>"/>
                    </div>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">所在行业</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <w:input name="userInfoDTO.domain" value="<%=userInfo.getDomain()%>"/>
                    </div>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">居住地</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <w:input name="userInfoDTO.location" value="<%=userInfo.getLocation()%>"/>
                    </div>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">所在企业</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <w:input name="userInfoDTO.company" value="<%=userInfo.getCompany()%>"/>
                    </div>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-3 col-md-3">
                        <h5 class="ProfileEditCard-itemLabel pull-right">毕业院校</h5>
                    </div>
                    <div class="col-lg-9 col-md-9">
                        <w:input name="userInfoDTO.college" value="<%=userInfo.getCollege()%>"/>
                    </div>
                </div>
                <hr class="split-line"/>
                <div class="row">
                    <div class="col-lg-12 col-md-12 form-group">
                        <button type="submit" class="btn btn-primary pull-right" name="login-button">保存</button>
                    </div>
                </div>

            </div>
            <div class="col-lg-4 col-md-4">
                开发中...
            </div>
        </div>
    </w:form>
</div>

<jsp:include page="/WEB-INF/blog/common/footer.jsp"/>
<jsp:include page="/WEB-INF/require/requireJs.jsp"/>
<script src="/statics/blog/demoBean/demoBean.js"></script>
<script src="/statics/common/jcorp/js/jquery.Jcrop.js"></script>

<script type="text/javascript" language="JavaScript">

    var JCropper;
    function readURL(input) {

    }

    $("#imgInp").change(function(){
        if (this.files && this.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#test_img').attr('src', e.target.result);
                if (typeof  JCropper != 'undefined')
                    JCropper.destroy();
                $('#test_img').Jcrop({
                    allowSelect: false,
                    setSelect : [ 0, 0, 100, 100 ],
                    setImage: e.target.result,
                    boxWidth:300,
                    boxHeight:300,
                    keySupport:false,
                    aspectRatio:1.0,
                    onChange : changeImgSize,
                    onSelect : changeImgSize,
                }, function () {
                    JCropper = this;
                });
                $("#btnOpenModel").click();
            };

            reader.readAsDataURL(this.files[0]);
        }
    });
    function changeImgSize(obj) {
        $("#x").val(obj.x);
        $("#y").val(obj.y);
        $("#w").val(obj.w);
        $("#h").val(obj.h);
    }
    $(document).ready(function() {
    });
    function imgClick() {
        $("#imgInp").click();
    }
</script>


<!-- Trigger the modal with a button -->
<button style="display: none" id="btnOpenModel" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 340px">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background: #1D7FE2; border-radius: 5px 5px 0px 0px; padding: 10px">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h6 class="modal-title">修改头像</h6>
            </div>
            <div class="modal-body">
                <img id="test_img" src="" style="background: white; width: 300px; height: 300px">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="uploadUserPic()">确定</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<html>