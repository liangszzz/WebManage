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


    var form = layui.form, layer = layui.layer;


    $('.btn_bar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    form.verify({
        title: function (value) {
            if (value.length < 5) {
                return '标题至少得5个字符啊';
            }
        }
    });

    form.on('submit(form)', function (data) {
        data.field.show = data.field.show == "on" ? 1 : 0;
        data.field.type = data.field.type == "on" ? 1 : 0;
        $.post(getHttpHead() + "menu/save", data.field, function (res) {
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
        elem: '#tree' //指定元素
        , nodes: getNodes(),
        click: function (item) { //点击节点回调
            current_item = item;
            form.val('form_save', item);
        }
    });


    var obj_default = {
        id: "",
        name: "",
        url: "",
        type: 0,
        show: 0,
        sort: 0,
        desc: "",
        parentId: null
    }
    current_item = null;
    form.val('form_save', obj_default);


    var active = {
        reload: function () {
            $("#tree").html('');
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
                url: "",
                type: 0,
                show: 0,
                sort: 0,
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
                url: "",
                type: 0,
                show: 0,
                sort: 0,
                desc: "",
                parentId: current_item.parentId
            }
            current_item = null;
            form.val('form_save', brother);
        },
        toAddRoot: function () {
            var root = {
                id: "",
                name: "",
                url: "",
                type: 0,
                show: 0,
                sort: 0,
                desc: "",
                parentId: null
            }
            current_item = null;
            form.val('form_save', root);
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
                $.post(getHttpHead() + "menu/delById", {id: current_item.id}, function (res) {
                    if (res.code == 0) {
                        layer.msg("删除成功!");
                        form.val('form_save', obj_default);
                        current_item = null;
                        active.reload();
                    }
                })
            }
        }
    }

});