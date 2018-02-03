<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<html>
<head>

    <style type="text/css">
        form {
            width: auto;
            margin: 0 auto;
        }
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
    <div align="center">
        <h4><fmt:message key="users_jsp.users_list"/></h4><br>
        <table style="text-align: center;" class="table table-hover table-condensed table-sm">
            <thead class="thead-default">
            <tr>
                <td><b><fmt:message key="users_jsp.id"/></b></td>
                <td><b><fmt:message key="users_jsp.login"/></b></td>
                <td><b><fmt:message key="users_jsp.fname"/></b></td>
                <td><b><fmt:message key="users_jsp.role"/></b></td>
                <td><b><fmt:message key="users_jsp.blocking"/></b></td>
            </tr>
            </thead>
            <c:forEach var="bean" items="${sessionScope.usersList}">
                <tr>
                    <td style="padding: 10px;">${bean.id}</td>
                    <td style="padding: 10px;">${bean.login}</td>
                    <td style="padding: 10px;">${bean.fName} ${bean.lName}</td>
                    <c:if test="${bean.roleId eq 0}">
                        <td style="padding: 10px;"><fmt:message key="users_jsp.admin"/></td>
                    </c:if>
                    <c:if test="${bean.roleId eq 1}">
                        <td style="padding: 10px;"><fmt:message key="users_jsp.student"/></td>
                    </c:if>
                    <td>
                        <c:if test="${bean.roleId eq 1}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="blockUser">
                            <c:if test="${bean.blocked eq true}">
                                <input type="hidden" name="login" value="${bean.login}">
                                <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="users_jsp.unblock"/>">
                            </c:if>
                            <c:if test="${bean.blocked eq false}">
                                <input type="hidden" name="login" value="${bean.login}">
                                <input type="submit" class="btn btn-warning btn-sm" value="<fmt:message key="users_jsp.block"/>">
                            </c:if>

                        </form>
                        </c:if>
                </tr>
            </c:forEach>
        </table>
        <form action="controller" method="post">
            <div align="center">
                <input type="hidden" name="command" value="homepage"><br>
                <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
            </div>
        </form>
    </div>
</div>

</body>
</html>