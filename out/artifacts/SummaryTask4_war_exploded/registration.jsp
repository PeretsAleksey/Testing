<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <script>
        $(function () {
            $('[data-toggle="popover"]').popover()
        })
    </script>
    <style>
        body {
            background: url(images/back.png) no-repeat;
            background-size: 100%
        }
    </style>
</head>
<title>Registration page</title>
<body>

<div class="container-fluid">
    <div align="center">
        <br>
        <h4>Registration page</h4><br>
        <div align="left">
            <form action="controller" method="post" class="form-horizontal">
                <div class="row">
                    <div class="col-md-5"></div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label class="control-label">Login *</label>
                            <input name="setLogin" class="form-control" data-container="body" data-toggle="popover" data-placement="left"
                                   data-content="Latin symbols and digits are allowed" data-trigger="focus" required
                                   value="${requestScope.setlogin}"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Password *</label>
                            <input type="password" name="setPassword" class="form-control" data-container="body"
                                   data-toggle="popover" data-placement="left" data-content="Latin symbols and digits are allowed"
                                   data-trigger="focus" required value="${requestScope.setPassword}"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Confirm password *</label>
                            <input type="password" name="confirmPassword" class="form-control" required  value="${requestScope.confirmPassword}"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">First name *</label>
                            <input name="setFName" class="form-control" data-container="body" data-toggle="popover" data-placement="left"
                                   data-content="Cyrillic  and latin symbols and digits are allowed." data-trigger="focus" required
                                   value="${requestScope.setFName}"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Last name *</label>
                            <input name="setLName" class="form-control" data-container="body" data-toggle="popover" data-placement="left"
                                   data-content="Cyrillic  and latin symbols and digits are allowed." data-trigger="focus" required
                                   value="${requestScope.setLName}"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">E-mail *</label>
                            <input name="setEMail" type="email" class="form-control" required  value="${requestScope.setEMail}"/>
                        </div>
                        <p class="help-block">* Fields are required</p>
                    </div>
                </div>
                <br>
                <div align="center">
                    <input type="hidden" name="command" value="registration">
                    <input type="submit" class="btn btn-success" value="Register">
                </div>
                <div class="col-md-5"></div>
            </form>
        </div>
        <form action="controller" method="post">
            <div align="center">
                <input type="hidden" name="command" value="loginpage">
                <input type="submit" class="btn btn-default" value="Login page">
            </div>
        </form>
    </div>
</div>

</body>
</html>
