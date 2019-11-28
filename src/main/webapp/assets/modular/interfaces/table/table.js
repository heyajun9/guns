layui.use(['table', 'ax', 'treetable'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var treetable = layui.treetable;
    /**
     * 表关系管理
     */
    var Option = {
        tableId: "optionTable",
        condition: {
            optionId: "",
            downColumnName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Option.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'optionId', hide: true, sort: true, title: 'id'},
            {field: 'upColumnName', sort: true, title: '上游字段'},
            {field: 'downColumnName', sort: true, title: '下游字段'},
            {field: 'parentMenu', sort: true, title: '父字段'},
            {field: 'defaultValue', sort: true, title: '默认值'},
            {field: 'isFlag', sort: true, title: '是否方括号'},
            {field: 'typeName', sort: true, title: '类型'},
            {field: 'columnSize', sort: true, title: '大小'},
            {
                field: 'nullable', sort: true, title: '状态', templet: function (d) {
                    if (d.nullable === '1') {
                        return "为空";
                    } else {
                        return "不为空";
                    }
                }
            },
            {field: 'tableName', sort: true, title: '表名'},
            {field: 'remarks', sort: true, title: '描述'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'createUser', sort: true, title: '创建人'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Option.search = function () {
        var queryData = {};
        queryData['downColumnName'] = $("#downColumnName").val();
        Option.initTable(Option.tableId, queryData);
    };

    /**
     * 弹出添加对话框
     */
    Option.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/table/table_add?interId=' + $("#interId").val();
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Option.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/table/table_edit?optionId=' + data.optionId;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Option.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/table/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Option.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("optionId", data.optionId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    /**
     * 渲染表格
     */
    var tableResult = table.render({
            elem: '#' + Option.tableId,
            url: Feng.ctxPath + '/table/list?interId=' + $("#interId").val(),
            page: true,
            height: "full-98",
            cellMinWidth: 100,
            cols: Option.initColumn()
        });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Option.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Option.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Option.exportExcel();
    });

    // 关闭页面
    $('#btnBack').click(function () {
        window.location.href = Feng.ctxPath + "/inter";
    });

    // 工具条点击事件
    table.on('tool(' + Option.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Option.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Option.onDeleteItem(data);
        }
    });
});
