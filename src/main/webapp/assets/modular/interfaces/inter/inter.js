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
    var Inter = {
        tableId: "interTable",    //表格id
        condition: {
            interId: "",
            interfaceName: "",
            downTable: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Inter.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'interId', hide: true, sort: true, title: 'id'},
            {
                field: 'interfaceName', sort: true, title: '接口名称', templet: function (d) {
                    var url = Feng.ctxPath + '/table?interId=' + d.interId;
                    return '<a style="color: #01AAED;" href="' + url + '">' + d.interfaceName + '</a>';
                }
            },
            {
                field: 'transferType', sort: true, title: '传输类型', templet: function (d) {
                    if (d.transferType === 'UP') {
                        return "上传";
                    } else {
                        return "下传";
                    }
                }
            },
            {
                field: 'interfaceType', sort: true, title: '接口类型', templet: function (d) {
                    if (d.interfaceType === 'I') {
                        return "入库上传";
                    } else if(d.interfaceType === 'O'){
                        return "出库上传";
                    }else if(d.interfaceType === 'P'){
                        return "盘点上传";
                    }else if(d.interfaceType === 'K'){
                        return "库存上传";
                    }else if(d.interfaceType === 'W'){
                        return "物料下传";
                    }else if(d.interfaceType === 'C'){
                        return "单位下传";
                    }else if(d.interfaceType === 'R'){
                        return "入库下传";
                    }else if(d.interfaceType === 'X'){
                        return "出库下传";
                    }else if(d.interfaceType === 'Z'){
                        return "职员下传";
                    }else if(d.interfaceType === 'U'){
                        return "库存下传";
                    }else if(d.interfaceType === 'T'){
                        return "盘点下传";
                    }
                }
            },
            {
                field: 'beactive', sort: true, title: '状态', templet: function (d) {
                    if (d.beactive === 'Y') {
                        return "生效";
                    } else {
                        return "失效";
                    }
                }
            },
            {field: 'dataMethod', sort: true, title: '数据格式'},
            {field: 'messageContext', sort: true, title: '业务类型'},
            {field: 'url', sort: true, title: '请求地址'},
            {field: 'upMethod', sort: true, title: '上传方法'},
            {field: 'downMethod', sort: true, title: '下传方法'},
            {field: 'upTable', sort: true, title: '上传表'},
            {field: 'downTable', sort: true, title: '下传表'},
            {field: 'downProduce', sort: true, title: '过程'},
            {field: 'userName', sort: true, title: '账号'},
            {field: 'password', sort: true, title: '密码'},
            {field: 'detail', hide: true, sort: true, title: '报文'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Inter.search = function () {
        var queryData = {};
        queryData['interfaceName'] = $("#interfaceName").val();
        queryData['interfaceType'] = $("#interfaceType").val();
        queryData['downTable'] = $("#downTable").val();
        table.reload(Inter.tableId, {where: queryData});
    };

    /**
     * 弹出添加菜单对话框
     */
    Inter.openAddInter = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加接口',
            content: Feng.ctxPath + '/inter/inter_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Inter.tableId);
            }
        });
    };

    /**
     * 设置字段对应关系
     */
    Inter.option = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '字段对应关系',
            content: Feng.ctxPath + '/inter/inter_option?downTable='+data.downTable,
            end: function () {
                admin.getTempData('formOk') && table.reload(Inter.tableId);
            }
        });
    };

    /**
     * 点击编辑菜单按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Inter.onEditInter = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑接口',
            content: Feng.ctxPath + '/inter/inter_edit?interId=' + data.interId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Inter.tableId);
            }
        });
    };
    /**
     * 接口报文详情
     */
    Inter.interDetail = function (param) {
        var ajax = new $ax(Feng.ctxPath + "/inter/detail/" + param.interId, function (data) {
            Feng.infoDetail("报文详情", data.detail);
        }, function (data) {
            Feng.error("获取报文详情失败!");
        });
        ajax.start();
    };

    /**
     * 点击删除菜单按钮
     *
     * @param data 点击按钮时候的行数据
     */
    Inter.onDeleteInter = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/inter/delete", function () {
                Feng.success("删除成功!");
                Inter.condition.interId = "";
                table.reload(Inter.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("interId", data.interId);
            ajax.start();
        };
        Feng.confirm("是否删除接口" + data.interfaceName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Inter.tableId,
        url: Feng.ctxPath + '/inter/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Inter.initColumn()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Inter.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Inter.openAddInter();
    });

    // 工具条点击事件
    table.on('tool(' + Inter.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Inter.onEditInter(data);
        } else if (layEvent === 'delete') {
            Inter.onDeleteInter(data);
        }else if (layEvent === 'option') {
            Inter.option(data);
        }else  if (layEvent === 'detail') {
            Inter.interDetail(data);
        }
    });
});
