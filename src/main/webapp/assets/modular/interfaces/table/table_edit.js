/**
 * 详情对话框
 */
var TablenfoDlg = {
    data: {
        dictTypeId: "",
        code: "",
        name: "",
        parentId: "",
        status: "",
        description: "",
        createTime: "",
        updateTime: "",
        createUser: "",
        updateUser: ""
    }
};

layui.use(['form', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;

    //获取菜单信息
    var ajax = new $ax(Feng.ctxPath + "/table/getTableInfo?optionId=" + Feng.getUrlParam("optionId"));
    var result = ajax.start();
    form.val('tableForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/table/edit", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + "/table?interId=" + $("#interId").val();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + "/table?interId=" + $("#interId").val();
    });

});