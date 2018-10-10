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

    var form = layui.form;


    $('.btn_bar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    form.on('submit(form)', function (data) {
        $.post(getHttpHead() + "dept/save", data.field, function (res) {
            if (res.code == 0) {
                layer.msg("保存成功!");
                active.reload()
            }
        });
        return false;
    });

    //当前节点
    var current_item;
    layui.tree({
        elem: '#dept_tree' //指定元素
        , nodes: getNodes(),
        click: function (item) { //点击节点回调
            current_item = item;
            form.val('form_save', item);
        }
    });

    var active = {
        reload: function () {
            $("#dept_tree").html('');
            layui.tree({
                elem: '#tree' //指定元素
                , nodes: getNodes(),
                click: function (item) { //点击节点回调
                    current_item = item;
                    form.val('form_save', item);
                }
            });
        },
        toAddChild: function () {
            var child = {
                id: "",
                name: "",
                desc: "",
                parentId: current_item.id
            }
            current_item = null;
            form.val('form_save', child);
        },
        toAddBrother: function () {
            var brother = {
                id: "",
                name: "",
                desc: "",
                parentId: current_item.parentId
            }
            current_item = null;
            form.val('form_save', brother);
        },
        toAddRoot: function () {
            var brother = {
                id: "",
                name: "",
                desc: "",
                parentId: null
            }
            current_item = null;
            form.val('form_save', brother);
        },
        delSelected: function () {
            if (current_item == null) {
                layer.msg("请先选择节点!")
                return
            }
            if (current_item.spread) {
                layer.msg("请先删除子节点!");
                return;
            }
            else {
                $.post(getHttpHead() + "dept/delById", {id: current_item.id}, function (res) {
                    if (res.code == 0) {
                        layer.msg("删除成功!");
                        var node = {
                            id: "",
                            name: "",
                            desc: "",
                            parentId: null
                        };
                        form.val('form_save', node);
                        current_item = null;
                        active.reload();
                    }
                })
            }
        }
    }

});