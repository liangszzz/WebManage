$(function () {
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader("token", getToken());
        },
        complete: function (xhr, status) {
            if (status == "success") {
                var json = xhr.responseJSON;
                if (json != null && json.code != 0) {
                    if(json.code==1){
                        layer.alert(json.msg);
                        setTimeout(function () {
                            toLogin();
                        },1000)
                    }
                    else {
                        layer.alert(json.msg);
                    }
                }
            }
        },
        error: function (xhr) {
            alert(xhr.status+"  "+xhr.message)
        }
    });
});

function getUrlHead() {
    return "/WebManage/Web/";
}

function getHttpHead() {
    return "http://127.0.0.1:8080/";
}

function toLogin() {
    location.href = getUrlHead() + "/login.html"
}

function getToken() {
    return window.sessionStorage.getItem("token");
}

function getFormJson(form) {
    var json_arr = form.serializeArray()
    var json = {};
    for (var i = 0; i < json_arr.length; i++) {
        if(json_arr[i].value !=null && json_arr[i].value !=""){
            json[json_arr[i].name] = json_arr[i].value
        }
    }
    return json;
}

function getUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");
    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var res = arrPara.filter(function (value) {
            var index = value.indexOf("=");
            if (value.substring(0, index) == paraName) {
                return true;
            }
        });
        if (res.length == 1) {
            return res[0].substring(paraName.length + 1, res[0].length);
        }
        else {
            throw "paramter not found error"
        }
    }
    else {
        throw "paramter not found error"
    }
}
