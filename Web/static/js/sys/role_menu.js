$(function () {

    function getNodes() {
        var nodes;
        $.ajaxSettings.async = false;
        $.post(getHttpHead() + "menu/query", function (res) {
            if (res.code == 0) {
                nodes = res.data;
            }
        });
        $.ajaxSettings.async = true;
        return nodes;
    }

    layui.use(['tree', 'table', 'layer','element','form'], function () {
        var layer = layui.layer,table = layui.table,form = layui.form;

        var currentItem=null;

        layui.tree({
            elem: '#select_tree' //指定元素
            , nodes: getNodes(),
            click: function (item) { //点击节点回调
                form.val('form_save', item);
                currentItem=item;
            }
        });
        table.render({
            elem: '#selected_table',
            url: getHttpHead() + 'role/queryLink/'+getUrlParam("id"),
            method: 'post',
            cols: [[
                {type: "numbers", width: 80, title: '序号', sort: true},
                {field: 'name', width: 100, title: '名称'},
                {field: 'url', width: 200, title: '路径'},
                {fixed: 'right',width: 150, align: 'center', toolbar: '#table_bar', title: '操作'} //这里的toolbar值是模板元素的选择器
            ]],
            done: function (res, curr, count) {
            }
        });

        table.on('tool(selected_table)', function (obj) {
            active[obj.event].call(this,obj.data.id);
        });
        var roleId=getUrlParam("id");

        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        var active={
            reload:function(){
                $("#select_tree").html('')
                layui.tree({
                    elem: '#select_tree' //指定元素
                    , nodes: getNodes(),
                    click: function (item) { //点击节点回调
                        form.val('form_save', item);
                        currentItem=item;
                    }
                });
                currentItem=null;
                form.val('form_save', {"name":"","url":"","desc":""});
                table.reload('selected_table');
            },
            addMenuToRole:function(){
                if(currentItem==null){
                    layer.msg("请选择节点!")
                    return;
                }
                var menuId=currentItem.id;
                $.post(getHttpHead()+"role/addLink",{roleId:roleId,menuId:menuId},function (res) {
                    if(res.code==0){
                        layer.msg("添加成功!");
                        active.reload();
                    }
                })
            },
            delMenuFromRole:function (menuId) {
                $.post(getHttpHead()+"role/delLink",{roleId:roleId,menuId:menuId},function (res) {
                    if(res.code==0){
                        layer.msg("删除成功!");
                        active.reload();
                    }
                })
            }
        }
    });




});