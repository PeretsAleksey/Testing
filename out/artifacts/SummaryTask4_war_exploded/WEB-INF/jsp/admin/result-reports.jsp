<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <title>Reports result</title>
    <style>
        body {
            background: url(/images/back.png) no-repeat;
            background-size: 100%
        }
    </style>
</head>
<body>
<br><br><br>
<div class="container">
    <div align="center">
        <h4><fmt:message key="report_formed"/></h4>
        <br>
        <c:if test="${sessionScope.user.roleId eq 0}">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="homepage">
                <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
