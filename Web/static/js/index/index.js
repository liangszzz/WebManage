$(function () {
    initIndexData()
    ininHead();
    initMenu();


    layui.use('element', function () {
        var element = layui.element;

        element.on("tab(tab_main)", function (res) {
        });

        element.on("nav(letf_menu)", function (res) {
            if (res[0].rel) {
                // element.tabDelete('tab_main', res[0].id);
                // element.tabAdd('tab_main', {
                //     title: res[0].text,
                //     content: "<iframe id='iframe_" + res[0] + "'  scrolling='no'" +
                //         " frameborder='0' style='margin: 0px;padding: 0px' src='" + getUrlHead() + res[0].rel + "'></iframe>",
                //     id: res[0].id
                // });
                // var ifm = document.getElementById("iframe_" + res[0]);
                // ifm.height = $(".layui-body").height()-1;
                // ifm.width = $(".layui-tab-title").width()-1;
                // element.tabChange('tab_main', res[0].id);
                $("#my_body").load(getUrlHead() + res[0].rel)
            }
        });

        element.tabAdd('tab_main', {
            title: 'index',
            content: '',
            id: "index"
        });

        element.tabChange('tab_main', 'index');
        element.init();
    });
});

var user;
var menus;


function initIndexData() {

    $.ajaxSettings.async = false;
    $.post(getHttpHead() + "login/indexData", function (data) {
        if (data.code == 0) {
            user = data.dataMap.user;
            menus = data.dataMap.menus;
        }
    });
    $.ajaxSettings.async = true;
}

function ininHead() {
    if (user == null) return
    $("#username").text(user.username)
}

function logout() {
    $.post(getHttpHead() + "login/logout", function (res) {
        if (res.code == 0) {
            window.localStorage.clear();
            toLogin()
        }
    });
}

function myInfo() {

}

function updatePwd() {

}

function initMenu() {
    if (menus == null) return

    menus.sort(function (a, b) {
        return a.sort - b.sort;
    });

    for (var j = 0; j < menus.length; j++) {
        var menu = menus[j];
        if (menu.parentId == null || menu.parentId == '') {
            var html = "<li class=\"layui-nav-item\" id='menu_" + menu.id + "'>" +
                "<a id=\"menu_" + menu.id + "\" href=\"javascript:void(0)\">" + menu.name + "</a>" +
                "</li>"
            $("#letf_menu").append(html);
        }
    }

    for (var j = 0; j < menus.length; j++) {
        var menu = menus[j];
        if (menu.parentId != null && menu.parentId != '') {
            var html = "<dd><a id=\"menu_" + menu.id + "\" href=\"javascript:void(0)\" rel='" + menu.url + "'>" + menu.name + "</a></dd>";
            if ($("#menu_" + menu.parentId + " .layui-nav-child").length == 0) {
                html = "<dl class=\"layui-nav-child\">" +
                    "<dd><a id=\"menu_" + menu.id + "\" href=\"javascript:void(0)\" rel=\"" + menu.url + "\">" + menu.name + "</a></dd>";
                $("#menu_" + menu.parentId + "").append(html);
            }
            else {
                $("#menu_" + menu.parentId + " .layui-nav-child").append(html);
            }

        }
    }
}