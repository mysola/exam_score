<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>登录页</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>

        <h3>欢迎使用</h3>

        <form class="m-t" id="adminlogin" method="post" action="${pageContext.request.contextPath}/login">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="用户名" name="username" id="username" required="">

            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" name="password" id="password" required="">

            </div>
            <div class="form-group">
                <span>记 住 我</span>
                <input type="checkbox" name="remember-me" checked ='checked'/>

            </div>

            <button type="submit" class="btn btn-primary block full-width m-b" onclick="javascript:void(0);">登 录
            </button>
        </form>
    </div>
</div>

</body>

</html>
