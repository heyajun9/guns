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
    var WmsPicTicketOrder = {
        tableId: "pickTicketOrderTable",    //表格id
        condition: {
            pickticket: "",
            danj_no: "",
            shangp_id: ""
        }
    };

    /**
     * 初始化表格的列
     */
    WmsPicTicketOrder.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'picticket', hide: true, sort: true, title: 'id'},
            {field: 'danj_no', sort: true, title: '单据号'},
            {field: 'hanghao', sort: true, title: '行号'},
            {field: 'wlzx_code', sort: true, title: '物流中心'},
            {field: 'huoz_id', sort: true, title: '货主ID'},
            {field: 'riqi_date', sort: true, title: '单据日期'},
            {field: 'danw_id', sort: true, title: '单位ID'},
            {field: 'yew_staff', sort: true, title: '联系人'},
            {field: 'tih_way', sort: true, title: '提货方式'},
            {field: 'xiaos_type', sort: true, title: '销售类型'},
            {field: 'fah_type', sort: true, title: '发货类型'},
            {field: 'shangp_id', sort: true, title: '物料ID'},
            {field: 'num', sort: true, title: '数量'},
            {field: 'lot_request', sort: true, title: '批号'},
            {field: 'note', sort: true, title: '备注'},
            {field: 'ckd_type', sort: true, title: '打印要求'},
            {field: 'guke_name', sort: true, title: '客户名'},
            {field: 'guke_no', sort: true, title: '客户ID'},
            {field: 'address_no', sort: true, title: '地址编号'},
            {field: 'address', sort: true, title: '地址'},
            {field: 'shouh_phone', sort: true, title: '收货电话'},
            {field: 'shouh_staff', sort: true, title: '收货员'},
            {field: 'postcode', sort: true, title: '邮编'},
            {field: 'peis_notes', sort: true, title: '配送备注'},
            {field: 'daoh_time', sort: true, title: '到货时间'},
            {field: 'jiaj_flg', sort: true, title: '是否加急'},
            {field: 'tiaom_num', sort: true, title: '条目'},
            {field: 'price', sort: true, title: '价格'},
            {field: 'jifen', sort: true, title: '积分'},
            {field: 'amount', sort: true, title: '金额'},
            {field: 'tax_amount', sort: true, title: '含税金额'},
            {field: 'jies_price', sort: true, title: '结算金额'},
            {field: 'jies_amount', sort: true, title: '结算数量'},
            {field: 'zt', sort: true, title: '状态'},
            {field: 'carrier_name', sort: true, title: '承运商名'},
            {field: 'carrier_id', sort: true, title: '承运商ID'},
            {field: 'danj_no_wsdd', sort: true, title: '渠道订单号'},
            {field: 'shuip_type', sort: true, title: '发票类型'},
            {field: 'platform_username', sort: true, title: '平台用户'},
            {field: 'payer_id', sort: true, title: '付款方ID'},
            {field: 'payer_name', sort: true, title: '付款方'},
            {field: 'danj_no_sap', sort: true, title: '源头单据'},
            {field: 'bills_type', sort: true, title: '移动类型'},
            {field: 'chongh_erp_flg', sort: true, title: '是否冲红'},
            {field: 'province', sort: true, title: '省'},
            {field: 'city', sort: true, title: '市'},
            {field: 'area', sort: true, title: '区'},
            {field: 'strategyno', sort: true, title: '战略单号'},
            {field: 'baoz_danw', sort: true, title: '包装单位'},
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
            {field: 'produce_date', sort: true, title: '生产日期'},
            {field: 'expire_date', sort: true, title: '到期日期'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsPicTicketOrder.search = function () {
        var queryData = {};
        queryData['shangp_id'] = $("#shangp_id").val();
        queryData['danj_no'] = $("#danj_no").val();
        table.reload(WmsPicTicketOrder.tableId, {where: queryData});
    };



    /**
     * 点击删除菜单按钮
     *
     * @param data 点击按钮时候的行数据
     */
    WmsPicTicketOrder.onDeleteWmsPicTicketOrder = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/pickTicketOrder/delete", function () {
                Feng.success("删除成功!");
                WmsPicTicketOrder.condition.pickticket = "";
                table.reload(WmsPicTicketOrder.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("pickTicketOrderId", data.pickticket);
            ajax.start();
        };
        Feng.confirm("是否删除物料" + data.danj_no + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsPicTicketOrder.tableId,
        url: Feng.ctxPath + '/pickTicketOrder/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: WmsPicTicketOrder.initColumn()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsPicTicketOrder.search();
    });


    // 工具条点击事件
    table.on('tool(' + WmsPicTicketOrder.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delete') {
            WmsPicTicketOrder.onDeleteWmsPicTicketOrder(data);
        }
    });
});
