/**
 * 详情对话框
 */
var AsnBackOrderInfoDlg = {
    data: {
        asnback_id: "",
        danj_no: ""
    }
};

layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取编辑信息
    var ajax = new $ax(Feng.ctxPath + "/asnBackOrder/getAsnBackOrderInfo?asnBackOrderId=" + Feng.getUrlParam("asnBackOrderId"));
    var result = ajax.start();
    form.val('asnBackOrderForm', result.data);


    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/asnBackOrder/edit", function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});