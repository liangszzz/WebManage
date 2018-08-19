$(function () {

    layui.use([ 'table', 'layer'], function () {
        var layer = layui.layer,table = layui.table,form = layui.form;

        var username=getUrlParam("username");
        table.render({
            elem: '#select_table',
            url: getHttpHead() + 'role/queryAll',
            method: 'post',
            cols: [[
                {type: "numbers", width: 80, title: '序号', sort: true},
                {field: 'roleName', width: 100, title: '名称'},
                {field: 'roleDesc', width: 200, title: '描述'},
                {fixed: 'right', align: 'center', toolbar: '#select_table_bar', title: '操作'} //这里的toolbar值是模板元素的选择器
            ]],
            done: function (res, curr, count) {
            }
        });

        table.render({
            elem: '#selected_table',
            url: getHttpHead() + 'role/queryRolesByUsername/'+username,
            method: 'post',
            cols: [[
                {type: "numbers", width: 80, title: '序号', sort: true},
                {field: 'roleName', width: 100, title: '名称'},
                {field: 'roleDesc', width: 200, title: '描述'},
                {fixed: 'right', align: 'center', toolbar: '#selected_table_bar', title: '操作'} //这里的toolbar值是模板元素的选择器
            ]],
            done: function (res, curr, count) {
            }
        });

        table.on('tool(selected_table)', function (obj) {
            active[obj.event].call(this,obj.data.roleId);
        });
        table.on('tool(select_table)', function (obj) {
            active[obj.event].call(this,obj.data.roleId);
        });

        var active={
            reload:function(){
                table.reload("selected_table",{});
                table.reload("select_table",{});
            },
            addRoleToUser:function (roleId) {
                $.post(getHttpHead()+"user/addRoleToUser",{username:username,roleId:roleId},function (res) {
                    if(res.code ==0 ){
                        layer.msg("添加成功!")
                        active.reload();
                    }
                });
            },
            delRoleFromUser:function (roleId) {
                $.post(getHttpHead()+"user/delRoleFromUser",{username:username,roleId:roleId},function (res) {
                    if(res.code ==0 ){
                        layer.msg("删除成功!")
                        active.reload();
                    }
                });
            }
        }
    });




});