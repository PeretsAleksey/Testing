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
        <c:if test="${sessionScope.user.roleId eq 1}">
            <h4><fmt:message key="u_results"/></h4>
        </c:if>
        <c:if test="${sessionScope.user.roleId eq 0}">
            <h4><fmt:message key="test_results"/></h4>
            <br>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="reports"> <input
                    type="submit" class="btn btn-primary" value="<fmt:message key="users_jsp.reports"/>">
            </form>
        </c:if>
        <br>
        <br>
        <br>
        <table style="text-align: center;" class="table table-hover table-condensed table-sm">
            <thead>
            <tr>
                <td><b><fmt:message key="result_jsp.user_name"/></b></td>
                <td><b><fmt:message key="result_jsp.theme_name"/></b></td>
                <td><b><fmt:message key="result_jsp.test_name"/></b></td>
                <td><b><fmt:message key="result_jsp.test_difficulty"/></b></td>
                <td><b><fmt:message key="result_jsp.date_time"/></b></td>
                <td><b><fmt:message key="result_jsp.result"/></b></td>
            </tr>
            </thead>
            <c:forEach var="bean" items="${sessionScope.testsResult}">
                <tr>
                    <td>${bean.fName} ${bean.lName}</td>
                    <td>${bean.themeName}</td>
                    <td>${bean.testName}</td>
                    <td>${bean.testDifficulty}</td>
                    <td>${bean.date}</td>
                    <td>${bean.result}%</td>
                </tr>
            </c:forEach>
        </table>

        <br>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="homepage">
            <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
        </form>
    </div>
</div>
</body>
</html>