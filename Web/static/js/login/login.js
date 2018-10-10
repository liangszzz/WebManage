
var form = layui.form //获取form模块

// form.verify()

form.verify({
    username: function(value, item){ //value：表单的值、item：表单的DOM对象
        if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
            return '用户名不能有特殊字符';
        }
        if(/(^\_)|(\__)|(\_+$)/.test(value)){
            return '用户名首尾不能出现下划线\'_\'';
        }

        if(/^\d+\d+\d$/.test(value)){
            return '用户名不能全为数字';
        }
    }
});


//监听提交按钮
form.on('submit(form)', function (data) {
    $.post(getHttpHead() + "login/doLogin", data.field, function (result) {
        if (result.code == 0) {
            window.sessionStorage.setItem("token", result.msg);
            location.href = getUrlHead() + "page/index/index.html";
        }
    });
});