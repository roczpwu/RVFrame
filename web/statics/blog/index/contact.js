/**
 * Created by rocwu on 2016/11/5.
 */

/**
 * 提交联系我们内容
 */
function submitContact() {
    $.ajax({
        type : "post",
        url : "/ajax/submitContact.action",
        data : $("#contactForm").serialize(),
        cache : false,
        dataType : "json",
        success : function(blMessage) {
            if (blMessage.success) {
                alert("提交成功!");
                location.assign("/index.action?");
            } else {
                alert("提交失败，请稍后重试。");
            }
        },
        error : function(blMessage) {
            alert("提交失败...");
        }
    });
}