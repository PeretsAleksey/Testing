<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%-- set the locale --%>
<fmt:setLocale value="${param.locale}" scope="session"/>

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resources"/>

<%-- set current locale to session --%>
<c:set var="currentLocale" value="${param.locale}" scope="session"/>

<html>
<head>
    <style>
        body {
            background: url(images/back.png) no-repeat;
            background-size: 100%
        }
    </style>
</head>
<body>
<br><br>
<div align="center">
    <h4><fmt:message key="change_language"/></h4>
    <br>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="homepage">
        <input type="submit" class="btn btn-default" value="<fmt:message key="to_homepage"/>">
    </form>

</div>
</body>
</html>
