<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
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
        The following error has occured:
        <h3>${requestScope.errorMessage}</h3>
        <a href="javascript:history.back()">
            <button class="btn btn-default"><fmt:message key="users_jsp.homepage"/></button>
        </a>
    </div>
</div>
</body>
</html>