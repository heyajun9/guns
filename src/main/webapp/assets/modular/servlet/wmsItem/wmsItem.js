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
    var WmsItem = {
        tableId: "itemTable",    //表格id
        condition: {
            shangp_id: "",
            lius_no: "",
            chinese_name: ""
        }
    };

    /**
     * 初始化表格的列
     */
    WmsItem.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'lius_no', hide: true, sort: true, title: 'id'},
            {field: 'shangp_id', sort: true, title: '物料ID'},
            {field: 'shangp_no', sort: true, title: '物料编码'},
            {field: 'wlzx_code', sort: true, title: '物流中心'},
            {field: 'huoz_id', sort: true, title: '货主ID'},
            {field: 'chinese_name', sort: true, title: '物料名称'},
            {field: 'zhuj_code', sort: true, title: '助记码'},
            {field: 'yaop_guig', sort: true, title: '药品规格'},
            {field: 'maker', sort: true, title: '生产厂家'},
            {field: 'chandi', sort: true, title: '产地'},
            {field: 'baoz_num', sort: true, title: '包装数量'},
            {field: 'baoz_danw', sort: true, title: '包装单位'},
            {field: 'chaif_lid', sort: true, title: '请求地址'},
            {field: 'kaipdw_min', sort: true, title: '最小开票单位'},
            {field: 'yaop_category', sort: true, title: '药品规格'},
            {field: 'cunc_condition', sort: true, title: '请求地址'},
            {field: 'beactive', sort: true, title: '生效'},
            {field: 'zhongbz', sort: true, title: '请求地址'},
            {field: 'jixing', sort: true, title: '请求地址'},
            {field: 'tongy_name', sort: true, title: '请求地址'},
            {field: 'lot_flg', sort: true, title: '请求地址'},
            {field: 'zengp_flg', sort: true, title: '请求地址'},
            {field: 'jiang_flg', sort: true, title: '请求地址'},
            {field: 'fangc_flg', sort: true, title: '请求地址'},
            {field: 'tgyp_flg', sort: true, title: '请求地址'},
            {field: 'jink_flg', sort: true, title: '请求地址'},
            {field: 'yis_flg', sort: true, title: '请求地址'},
            {field: 'gzyp_flg', sort: true, title: '请求地址'},
            {field: 'yxp_flg', sort: true, title: '请求地址'},
            {field: 'caigou_staff', sort: true, title: '请求地址'},
            {field: 'ypyh_type', sort: true, title: '请求地址'},
            {field: 'geiy_way', sort: true, title: '请求地址'},
            {field: 'yaop_xingz', sort: true, title: '请求地址'},
            {field: 'shiyz', sort: true, title: '请求地址'},
            {field: 'gengx_time', sort: true, title: '请求地址'},
            {field: 'yp_flg', sort: true, title: '请求地址'},
            {field: 'zt', sort: true, title: '状态'},
            {field: 'piz_no', sort: true, title: '请求地址'},
            {field: 'merge_flg', sort: true, title: '请求地址'},
            {field: 'english_name', sort: true, title: '请求地址'},
            {field: 'sf_zdkz', sort: true, title: '请求地址'},
            {field: 'sf_tdyp', sort: true, title: '请求地址'},
            {field: 'shouy_flg', sort: true, title: '请求地址'},
            {field: 'sf_zy', sort: true, title: '请求地址'},
            {field: 'medicinespecicalcontrol', sort: true, title: '请求地址'},
            {field: 'barcode', sort: true, title: '请求地址'},
            {field: 'weight', sort: true, title: '请求地址'},
            {field: 'height', sort: true, title: '请求地址'},
            {field: 'length', sort: true, title: '请求地址'},
            {field: 'width', sort: true, title: '请求地址'},
            {field: 'youxq_flg', sort: true, title: '请求地址'},
            {field: 'forbid_days', sort: true, title: '请求地址'},
            {field: 'commodity', sort: true, title: '请求地址'},
            {field: 'demurrage_day', sort: true, title: '请求地址'},
            {field: 'foreboed_days', sort: true, title: '请求地址'},
            {field: 'ban_days', sort: true, title: '请求地址'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsItem.search = function () {
        var queryData = {};
        queryData['shangp_id'] = $("#shangp_id").val();
        queryData['chinese_name'] = $("#chinese_name").val();
        table.reload(WmsItem.tableId, {where: queryData});
    };



    /**
     * 点击删除菜单按钮
     *
     * @param data 点击按钮时候的行数据
     */
    WmsItem.onDeleteWmsItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsItem/delete", function () {
                Feng.success("删除成功!");
                WmsItem.condition.lius_no = "";
                table.reload(WmsItem.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("wmsItemId", data.lius_no);
            ajax.start();
        };
        Feng.confirm("是否删除物料" + data.chinese_name + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsItem.tableId,
        url: Feng.ctxPath + '/wmsItem/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: WmsItem.initColumn()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsItem.search();
    });


    // 工具条点击事件
    table.on('tool(' + WmsItem.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delete') {
            WmsItem.onDeleteWmsItem(data);
        }
    });
});
