<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <style>
        body {
            background: url(images/back.png) no-repeat;
            background-size: 100%
        }
    </style>
    <script>
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
    </script>


</head>
<title>Login page</title>
<body>


<c:if test="${sessionScope.successLogin}">
    <c:if test="${sessionScope.user.roleId eq 0}">
        <jsp:forward page="WEB-INF/jsp/admin/admin.jsp"/>
    </c:if>
    <c:if test="${sessionScope.user.roleId eq 1}">
        <jsp:forward page="WEB-INF/jsp/client/client.jsp"/>
    </c:if>
</c:if>

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a href="login.jsp" class="navbar-brand">Testing</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="login.jsp">Home page</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div align="center">
        <h4>Enter your login and password to enter</h4>
        <div class="row">
            <div class="col-md-5"></div>
            <div class="col-md-2">
                <form action="controller" method="post" class="form-horizontal">
                    <div align="left">
                        <div class="form-group">
                            <input type="hidden" name="command" value="login">
                            <label class="control-label ">Login</label>
                            <input name="login" class="form-control" placeholder="Login">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Password">
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <input type="submit" class="btn btn-success col-md-4" value="Sing in">
                    <div class="col-md-1"></div>
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="RegistrationPage">
                    <input type="submit" class="btn btn-default col-md-4" value="Registration">

                </form>
            </div>
            <div class="col-md-5"></div>
        </div>
    </div>
</div>


</body>
</html>