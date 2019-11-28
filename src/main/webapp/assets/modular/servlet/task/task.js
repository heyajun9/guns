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
    var Task = {
        tableId: "taskTable",    //表格id
        condition: {
            jobId: "",
            jobName: "",
            startStaus:"",
            runStatus:"",
            className: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Task.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'jobId', hide: true, sort: true, title: 'id'},
            {field: 'jobName', sort: true, title: '任务名'},
            {field: 'jobGroupName', sort: true, title: '任务组名'},
            {field: 'triggerName', sort: true, title: '触发器'},
            {field: 'triggerGroupName', sort: true, title: '触发器组'},
            {field: 'corn', sort: true, title: '时间'},
            {field: 'className', sort: true, title: '类名'},
            {field: 'methodName', sort: true, title: '方法名'},
            {
                field: 'startStaus', sort: true, title: '启动状态', templet: function (d) {
                    if (d.startStaus === '0') {
                        return "启动";
                    } else {
                        return "关闭";
                    }
                }
            },
            {
                field: 'runStatus', sort: true, title: '运行状态', templet: function (d) {
                    if (d.runStatus === '0') {
                        return "生效";
                    } else {
                        return "失效";
                    }
                }
            },
            {field: 'note', sort: true, title: '描述'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 300}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Task.search = function () {
        var queryData = {};
        queryData['jobName'] = $("#jobName").val();
        queryData['className'] = $("#className").val();
        table.reload(Task.tableId, {where: queryData});
    };

    /**
     * 弹出添加菜单对话框
     */
    Task.openAddTask = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加任务',
            content: Feng.ctxPath + '/task/task_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Task.tableId);
            }
        });
    };

    /**
     * 点击编辑菜单按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Task.onEditTask = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑任务',
            content: Feng.ctxPath + '/task/task_edit?jobId=' + data.jobId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Task.tableId);
            }
        });
    };

    /**
     * 点击启动菜单按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Task.startTask = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/task/startTask", function () {
                Feng.success("启动成功!");
                Task.condition.jobId = "";
                table.reload(Task.tableId);
            }, function (data) {
                Feng.error("启动失败!" + data.responseJSON.message + "!");
            });
            ajax.set("jobId", data.jobId);
            ajax.start();
        };
        Feng.confirm("是否启动任务" + data.jobName + "?", operation);
    };
    /**
     * 点击关闭菜单按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Task.endTask = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/task/endTask", function () {
                Feng.success("关闭成功!");
                Task.condition.jobId = "";
                table.reload(Task.tableId);
            }, function (data) {
                Feng.error("关闭失败!" + data.responseJSON.message + "!");
            });
            ajax.set("jobId", data.jobId);
            ajax.start();
        };
        Feng.confirm("是否关闭任务" + data.jobName + "?", operation);
    };
    /**
     * 点击立即执行单按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Task.startOnce = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/task/startOnce", function () {
                Feng.success("立即执行成功!");
                Task.condition.jobId = "";
                table.reload(Task.tableId);
            }, function (data) {
                Feng.error("立即执行失败!" + data.responseJSON.message + "!");
            });
            ajax.set("jobId", data.jobId);
            ajax.start();
        };
        Feng.confirm("是否立即执行任务" + data.jobName + "?", operation);
    };

    /**
     * 点击删除菜单按钮
     *
     * @param data 点击按钮时候的行数据
     */
    Task.onDeleteTask = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/task/delete", function () {
                Feng.success("删除成功!");
                Task.condition.jobId = "";
                table.reload(Task.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("jobId", data.jobId);
            ajax.start();
        };
        Feng.confirm("是否删除任务" + data.jobName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Task.tableId,
        url: Feng.ctxPath + '/task/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Task.initColumn()
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Task.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Task.openAddTask();
    });

    // 工具条点击事件
    table.on('tool(' + Task.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Task.onEditTask(data);
        } else if (layEvent === 'delete') {
            Task.onDeleteTask(data);
        }else if (layEvent === 'startTask') {
            Task.startTask(data);
        }else if (layEvent === 'endTask') {
            Task.endTask(data);
        }else if (layEvent === 'startOnce') {
            Task.startOnce(data);
        }
    });
});
