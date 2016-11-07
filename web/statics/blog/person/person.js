/**
 * Created by rocwu on 2016/11/6.
 */

function editUserInfo(user_id) {
    location.assign("/personEditPage.action?user_id="+user_id);
}

function uploadUserPic() {
    var x = $("#x").val();
    var y = $("#y").val();
    var w = $("#w").val();
    var h = $("#h").val();

    var formData = new FormData();
    formData.append("fileData",$("#imgInp")[0].files[0]);
    formData.append("x",x);
    formData.append("y",y);
    formData.append("w",w);
    formData.append("h",h);
    $.ajax({
        type : "POST",  //提交方式
        url : "/ajax/uploadPhoto.action",
        processData : false,
        contentType : false,
        data : formData,
        success : function(result) {//返回数据根据结果进行相应的处理
            if ( result.success ) {
                $("#img-show").attr("src", result.data);
                $("[name='userInfoDTO.face_url']").attr("value", result.data);
            } else {
                alert(result.message);
            }
        }
    });
}