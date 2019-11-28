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
    var WmsOrganization = {
        tableId: "organizationTable",    //表格id
        condition: {
            danw_id: "",
            lius_no: "",
            danw_name: ""
        }
    };

    /**
     * 初始化表格的列
     */
    WmsOrganization.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'lius_no', hide: true, sort: true, title: 'id'},
            {field: 'danw_id', sort: true, title: '单位ID'},
            {field: 'danw_no', sort: true, title: '单位编码'},
            {field: 'huoz_id', sort: true, title: '货主ID'},
            {field: 'wlzx_code', sort: true, title: '物流中心'},
            {field: 'danw_name', sort: true, title: '单位名称'},
            {field: 'zhuj_code', sort: true, title: '助记码'},
            {field: 'beactive', sort: true, title: '是否生效'},
            {field: 'gengx_time', sort: true, title: '更新时间'},
            {field: 'danw_simplename', sort: true, title: '单位简称'},
            {field: 'yew_staff', sort: true, title: '业务员'},
            {field: 'address', sort: true, title: '地址'},
            {field: 'shouh_phone', sort: true, title: '收货电话'},
            {field: 'shouh_staff', sort: true, title: '收货员'},
            {field: 'postcode', sort: true, title: '邮编'},
            {field: 'zt', sort: true, title: '状态'},
            {field: 'yaojno', sort: true, title: '药监码'},
            {field: 'xukz_no', sort: true, title: '许可证号'},
            {field: 'xukz_yxqdate', sort: true, title: '许可证有效期'},
            {field: 'yingyzz_yxqdate', sort: true, title: '营业执照有效期'},
            {field: 'zzjgdmz_no', sort: true, title: '组织机构代码'},
            {field: 'zzjgdmz_flg', sort: true, title: '是否有组织机构证'},
            {field: 'zzjgdmz_yxqdate', sort: true, title: '组织机构有效期'},
            {field: 'gmp_no', sort: true, title: 'GMP证号'},
            {field: 'gmp_flg', sort: true, title: '是否有GMP'},
            {field: 'gmp_yxqdate', sort: true, title: 'GMP有效期'},
            {field: 'gsp_no', sort: true, title: 'GSP证号'},
            {field: 'gsp_flg', sort: true, title: '是否有GSP'},
            {field: 'gsp_yxqdate', sort: true, title: 'GSP有效期'},
            {field: 'wts_yxqdate', sort: true, title: '法人委托书协议有效期'},
            {field: 'xy_yxqdate', sort: true, title: '质量保证协议有效期'},
            {field: 'swdjz_flg', sort: true, title: '是否有税务登记证'},
            {field: 'yzym_flg', sort: true, title: '是否有印字印模'},
            {field: 'khhxkz_flg', sort: true, title: '是否有开户行许可证'},
            {field: 'yingyzz_flg', sort: true, title: '是否有营业执照'},
            {field: 'yingyzz_no', sort: true, title: '营业执照号'},
            {field: 'city', sort: true, title: '市'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsOrganization.search = function () {
        var queryData = {};
        queryData['danw_id'] = $("#danw_id").val();
        queryData['danw_name'] = $("#danw_name").val();
        table.reload(WmsOrganization.tableId, {where: queryData});
    };



    /**
     * 点击删除菜单按钮
     *
     * @param data 点击按钮时候的行数据
     */
    WmsOrganization.onDeleteWmsOrganization = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/organization/delete", function () {
                Feng.success("删除成功!");
                WmsOrganization.condition.lius_no = "";
                table.reload(WmsOrganization.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("organizationId", data.lius_no);
            ajax.start();
        };
        Feng.confirm("是否删除单位" + data.danw_name + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsOrganization.tableId,
        url: Feng.ctxPath + '/organization/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: WmsOrganization.initColumn()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsOrganization.search();
    });


    // 工具条点击事件
    table.on('tool(' + WmsOrganization.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delete') {
            WmsOrganization.onDeleteWmsOrganization(data);
        }
    });
});
