<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<html>
<head>
    <title>Personal information</title>
    <style>
        body {
            background: url(/images/back.png) no-repeat;
            background-size: 100%
        }
        .table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th {
            background-color: white;
        }
    </style>
</head>
<body>

<div class="container">
    <br>
    <div align="center">
        <div class="row">
            <div class="col-md-3">
                <a href="controller?command=homepage"><fmt:message key="all_homepage"/></a>
            </div>
            <div class="col-md-6"></div>
            <div class="col-md-3">
                <div align="center">
                    ${sessionScope.user.fName} ${sessionScope.user.lName}
                    <c:if test="${sessionScope.user.roleId eq 0}">(<fmt:message key="users_jsp.admin"/>)</c:if>
                    <c:if test="${sessionScope.user.roleId eq 1}">(<fmt:message key="users_jsp.student"/>)</c:if>
                    <a href="controller?command=logout"><fmt:message key="admin_jsp.logout"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
    <div class="container">
        <br><br><br>
        <div align="center">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <table style="text-align: center;" class="table table-hover table-condensed table-sm ">
                        <thead>
                        <tr>
                            <td><b><fmt:message key="users_jsp.login"/></b></td>
                            <td><b><fmt:message key="users_jsp.fname"/></b></td>
                            <td><b><fmt:message key="users_jsp.email"/></b></td>
                            <td><b><fmt:message key="users_jsp.blocking"/></b></td>
                        </tr>
                        </thead>
                        <tr>
                            <td> ${sessionScope.user.login} </td>
                            <td> ${sessionScope.user.fName} ${sessionScope.user.lName}</td>
                            <td> ${sessionScope.user.eMail} </td>
                            <td> ${sessionScope.user.blocked} </td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-2"></div>
            </div>
            <form action="controller" method="post">
                <div align="center">
                    <input type="hidden" name="command" value="homepage"><br>
                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>



