$(function () {

    function getNodes() {
        var nodes;
        $.ajaxSettings.async = false;
        $.post(getHttpHead() + "dept/query", function (res) {
            if (res.code == 0) {
                nodes = res.data;
            }
        });
        $.ajaxSettings.async = true;
        return nodes;
    }

    layui.use([ 'table', 'layer','tree'], function () {
        var layer = layui.layer,table = layui.table,form = layui.form;

        var username=getUrlParam("username");

        //当前节点
        var current_item;
        layui.tree({
            elem: '#select_tree' //指定元素
            , nodes: getNodes(),
            click: function (item) { //点击节点回调
                current_item = item;
                $("#current_dept").html(current_item.name)
            }
        });

        table.render({
            elem: '#selected_table',
            url: getHttpHead() + 'dept/queryDeptsByUsername/'+username,
            method: 'post',
            cols: [[
                {type: "numbers", title: '序号'},
                {field: 'name', title: '名称'},
                {field: 'desc', title: '描述'},
                {fixed: 'right', align: 'center', toolbar: '#table_bar', title: '操作'} //这里的toolbar值是模板元素的选择器
            ]],
            done: function (res, curr, count) {
            }
        });

        table.on('tool(selected_table)', function (obj) {
            active[obj.event].call(this,obj.data.id);
        });
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        var active={
            reload:function(){
                $('#select_tree').html('');
                layui.tree({
                    elem: '#select_tree' //指定元素
                    , nodes: getNodes(),
                    click: function (item) { //点击节点回调
                        current_item = item;
                        $("#current_dept").html(current_item.name)
                    }
                });
                table.reload("selected_table",{});
            },
            addDeptToUser:function () {
                if(current_item.spread){
                    layer.msg('请选择子节点!');
                    return
                }

                var deptId=current_item.id;
                $.post(getHttpHead()+"user/addDeptToUser",{username:username,deptId:deptId},function (res) {
                    if(res.code ==0 ){
                        layer.msg("添加成功!")
                        active.reload();
                    }
                });
            },
            delDeptFromUser:function (deptId) {
                $.post(getHttpHead()+"user/delDeptFromUser",{username:username,deptId:deptId},function (res) {
                    if(res.code ==0 ){
                        layer.msg("删除成功!")
                        active.reload();
                    }
                });
            }
        }
    });

});