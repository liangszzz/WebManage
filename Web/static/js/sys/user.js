$(function () {
    var table = layui.table, form = layui.form;

    table.on('checkbox(table)', function (obj) {

    });

    //监听工具条
    table.on('tool(table)', function (obj) {
        active[obj.event].call(this, obj.data.username);
    });
    //监听按钮
    $('.btn_bar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    table.render({
        elem: '#table',
        url: getHttpHead() + 'user/query',
        method: 'post',
        headers: {
            token: getToken()
        },
        cols: [[
            {type: 'checkbox'},
            {type: "numbers", width: 80, title: '序号', sort: true},
            {field: 'username', width: 150, title: '用户名'},
            {field: 'email', width: 150, title: '邮箱'},
            {field: 'phone', width: 150, title: '电话'},
            {field: 'address', width: 300, title: '地址'},
            {fixed: 'right', align: 'center', toolbar: '#table_bar', title: '操作'} //这里的toolbar值是模板元素的选择器
        ]],
        limit: 10,
        page: true,
        done: function (res, curr, count) {
            this.where = {};
        }
    });

    form.on('submit(formSubmit)', function (data) {
        active.reload();
        return false;
    });

    var active = {
        reload: function () {
            debugger
            table.reload('table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: getFormJson($("#search_form"))
            });
        },
        toAdd: function () { //获取选中数据
            layer.open({
                title: '查看',
                content: $("#form_html").html(),
                yes: function (index, layero) {
                    active.save();
                    layer.close(index);
                }
            });
            form.val('form_save', {});
        },
        toDetail: function (id) {
            $.post(getHttpHead() + "user/form/" + id, function (result) {
                if (result.code == 0) {
                    layer.open({
                        title: '查看',
                        content: $("#form_html").html()
                    });
                    form.val('form_save', result.entity);
                    $("#form_save input,textarea,select,checkbox").addClass("layui-disabled");
                }
            });
        },
        toEdit: function (id) {
            $.post(getHttpHead() + "user/form/" + id, function (result) {
                if (result.code == 0) {
                    layer.open({
                        title: '查看',
                        content: $("#form_html").html(),
                        yes: function (index, layero) {
                            active.save();
                            layer.close(index);
                        }
                    });
                    form.val('form_save', result.entity);
                }
            });
        },
        doDel: function (id) {
            layer.confirm("确定要删除吗啊?", function () {
                $.post(getHttpHead() + "user/doDel/" + id, function (result) {
                    if (result.code == 0) {
                        layer.msg("删除成功!")
                        active.reload();
                    }
                });
            });
        },
        delSelect: function () { //获取选中数目
            var checkStatus = table.checkStatus('table');
            layer.confirm("确定要删除吗啊?", function () {
                $.ajax({
                    url: getHttpHead() + "user/doDelAll",
                    type: "POST",
                    contentType: 'application/json;charset=utf-8', //设置请求头信息
                    dataType: "json",
                    data: JSON.stringify(checkStatus.data),
                    success: function (data) {
                        layer.closeAll()
                        active.reload()
                    },
                    error: function (res) {
                        layer.alert(res.responseText);
                    }
                });
            });
        },
        save: function () {
            var data = getFormJson($("#form_save"));
            $.post(getHttpHead() + "user/save", data, function (result) {
                active.reload();
            });
        },
        roles: function (id) {
            layer.open({
                title: '设置角色',
                type: 2,
                shade: 0.5,//显示遮罩透明度 0 没有
                maxmin: true,
                area: ['807px', '656px'],
                content: getUrlHead() + "page/sys/user_role.html?username=" + id,
                btn: ["关闭"],
                yes: function (index, layero) {
                    layer.close(index);
                }
            });
        },
        depts: function (id) {
            layer.open({
                title: '设置部门',
                type: 2,
                shade: 0.5,//显示遮罩透明度 0 没有
                maxmin: true,
                area: ['807px', '656px'],
                content: getUrlHead() + "page/sys/user_dept.html?username=" + id,
                btn: ["关闭"],
                yes: function (index, layero) {
                    layer.close(index);
                }
            });
        }
    };
});