$.ajaxSetup({
    statusCode: {
        401: function(jqXHR, textStatus, errorThrown) {
            // 处理未登录或认证失败的情况
            // 例如，重定向到登录页面
            alert("对不起，请先登陆！");
            window.location.href = '/login.html';
        }
    }
});