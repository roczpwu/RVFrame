/**
 * Created by rocwu on 16/8/9.
 */

function test() {
    alert("begin");
    AjaxInterface.testAjax(1,"2", function(result){

        alert(result.success);
    });
    alert("done");
}