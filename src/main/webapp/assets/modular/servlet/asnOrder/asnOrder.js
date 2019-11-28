layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;


    /**
     * 接口管理
     */
    var WmsAsnOrder = {
        tableId: "asnOrderTable",    //表格id
        condition: {
            asn_id: "",
            danj_no: "",
            shangp_id: ""
        }
    };

    /**
     * 初始化表格的列
     */
    WmsAsnOrder.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'asn_id', hide: true, sort: true, title: 'id'},
            {field: 'danj_no', sort: true, title: '单据号'},
            {field: 'hanghao', sort: true, title: '行号'},
            {field: 'wlzx_code', sort: true, title: '物流中心'},
            {field: 'huoz_id', sort: true, title: '货主ID'},
            {field: 'riqi_date', sort: true, title: '单据日期'},
            {field: 'danw_id', sort: true, title: '单位ID'},
            {field: 'lianx_staff', sort: true, title: '联系人'},
            {field: 'lianx_phone', sort: true, title: '联系电话'},
            {field: 'yew_staff', sort: true, title: '业务员'},
            {field: 'yew_type', sort: true, title: '业务类型'},
            {field: 'ruk_type', sort: true, title: '入库类型'},
            {field: 'shangp_id', sort: true, title: '物料ID'},
            {field: 'num', sort: true, title: '数量'},
            {field: 'note', sort: true, title: '备注'},
            {field: 'tiaom_num', sort: true, title: '条目数'},
            {field: 'zt', sort: true, title: '状态'},
            {field: 'yewdj_no_ck', sort: true, title: '调拨单号'},
            {field: 'new_arrival', sort: true, title: '是否新品'},
            {field: 'piz_no', sort: true, title: '批准文号'},
            {field: 'from_djbh', sort: true, title: '源头订单号'},
            {field: 'from_hanghao', sort: true, title: '源头行号'},
            {field: 'danj_riqi', sort: true, title: '单据日期'},
            {field: 'lot', sort: true, title: '批次'},
            {field: 'baoz_danw', sort: true, title: '包装单位'},
            {field: 'produce_date', sort: true, title: '生产日期'},
            {field: 'expire_date', sort: true, title: '到期日期'},
            {field: 'yid_type', sort: true, title: '移动类型'},
            {field: 'gongchang', sort: true, title: '工厂'},
            {field: 'kc_address', sort: true, title: '库存地址'},
            {field: 'to_address', sort: true, title: '目标地址'},
            {field: 'from_address', sort: true, title: '来源地址'},
            {field: 'to_lot', sort: true, title: '目的批次'},
            {field: 'tskc_flg', sort: true, title: '特殊库存标识'},
            {field: 'gongys_no', sort: true, title: '请求地址'},
            {field: 'dingd_no', sort: true, title: '订单号'},
            {field: 'chengbzx_no', sort: true, title: '成本中心'},
            {field: 'yldj_no', sort: true, title: '预留单号'},
            {field: 'yl_hanghao', sort: true, title: '预留行号'},
            {field: 'to_djbh', sort: true, title: '目的订单号'},
            {field: 'to_hanghao', sort: true, title: '目的行号'},
            {field: 'zt001', sort: true, title: '备用1'},
            {field: 'zt002', sort: true, title: '备用2'},
            {field: 'zt003', sort: true, title: '备用3'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsAsnOrder.search = function () {
        var queryData = {};
        queryData['shangp_id'] = $("#shangp_id").val();
        queryData['danj_no'] = $("#danj_no").val();
        table.reload(WmsAsnOrder.tableId, {where: queryData});
    };



    /**
     * 点击删除菜单按钮
     *
     * @param data 点击按钮时候的行数据
     */
    WmsAsnOrder.onDeleteWmsAsnOrder = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/asnOrder/delete", function () {
                Feng.success("删除成功!");
                WmsAsnOrder.condition.asn_id = "";
                table.reload(WmsAsnOrder.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("asnOrderId", data.asn_id);
            ajax.start();
        };
        Feng.confirm("是否删除入库单据" + data.danj_no + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsAsnOrder.tableId,
        url: Feng.ctxPath + '/asnOrder/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: WmsAsnOrder.initColumn()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsAsnOrder.search();
    });


    // 工具条点击事件
    table.on('tool(' + WmsAsnOrder.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delete') {
            WmsAsnOrder.onDeleteWmsAsnOrder(data);
        }
    });
});
