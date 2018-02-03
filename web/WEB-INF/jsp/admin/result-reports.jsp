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


<c:if test="${sessionScope.user.roleId eq 0}">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="homepage"> <input
            type="submit" value="<fmt:message key="users_jsp.homepage"/>">
    </form>
</c:if>

</body>
</html>