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
    var WmsAsnBackOrder = {
        tableId: "asnBackOrderTable",    //表格id
        condition: {
            asnback_id: "",
            danj_no: "",
            shangp_id: ""
        }
    };

    /**
     * 初始化表格的列
     */
    WmsAsnBackOrder.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'asnback_id', hide: true, sort: true, title: 'id'},
            {field: 'danj_no', sort: true, title: '单据号'},
            {field: 'hanghao', sort: true, title: '行号'},
            {field: 'wlzx_code', sort: true, title: '物流中心'},
            {field: 'huoz_id', sort: true, title: '货主ID'},
            {field: 'riqi_date', sort: true, title: '单据日期'},
            {field: 'danw_id', sort: true, title: '单位ID'},
            {field: 'caigou_staff', sort: true, title: '联系人'},
            {field: 'caoz_staff', sort: true, title: '操作人'},
            {field: 'zhij_staff', sort: true, title: '质检员'},
            {field: 'shouh_staff', sort: true, title: '收货员'},
            {field: 'yew_type', sort: true, title: '业务类型'},
            {field: 'ruk_type', sort: true, title: '入库类型'},
            {field: 'hangh_cgd', sort: true, title: '采购订单行'},
            {field: 'yewdj_no', sort: true, title: '业务单据编号'},
            {field: 'shengchan_char', sort: true, title: '生产日期'},
            {field: 'youx_char', sort: true, title: '有效日期'},
            {field: 'yans_rlt', sort: true, title: '质量状态'},
            {field: 'shangp_id', sort: true, title: '物料ID'},
            {field: 'num', sort: true, title: '数量'},
            {field: 'note', sort: true, title: '备注'},
            {field: 'tiaom_num', sort: true, title: '条目数'},
            {
                field: 'zt', sort: true, title: '状态', templet: function (d) {
                    if (d.zt === 'N') {
                        return "未推送";
                    } else if(d.zt=='R'){
                        return "失败";
                    }else if(d.zt=='Z'){
                        return "等待";
                    }else if(d.zt=='Y'){
                        return "成功"
                    }
                }
            },
            {field: 'error_msg', sort: true, title: '错误信息'},
            {field: 'rkkpd_no', sort: true, title: '入库开票单号'},
            {field: 'maker', sort: true, title: '生产厂家'},
            {field: 'chandi', sort: true, title: '产地'},
            {field: 'areacode', sort: true, title: '区域'},
            {field: 'cunc_condition', sort: true, title: '存储条件'},
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
    WmsAsnBackOrder.search = function () {
        var queryData = {};
        queryData['shangp_id'] = $("#shangp_id").val();
        queryData['danj_no'] = $("#danj_no").val();
        queryData['zt'] = $("#zt").val();
        table.reload(WmsAsnBackOrder.tableId, {where: queryData});
    };

    /**
     * 点击编辑菜单按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    WmsAsnBackOrder.onEditWmsAsnBackOrder = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑入库回传',
            content: Feng.ctxPath + '/asnBackOrder/asnBackOrder_edit?asnBackOrderId=' + data.asnback_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(WmsAsnBackOrder.tableId);
            }
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsAsnBackOrder.tableId,
        url: Feng.ctxPath + '/asnBackOrder/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: WmsAsnBackOrder.initColumn()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsAsnBackOrder.search();
    });


    // 工具条点击事件
    table.on('tool(' + WmsAsnBackOrder.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsAsnBackOrder.onEditWmsAsnBackOrder(data);
        }
    });
});
