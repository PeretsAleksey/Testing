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
</head>
<body>
<br>
<div align="center">
    <div class="row">
        <div class="col-md-5"></div>
        <div class="col-md-2">
            <form action="controller" method="post">
                <select name="locale" class="form-control">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                        <option value="${locale.key}" ${selected}>${locale.value}</option>
                    </c:forEach>
                </select>
                <br>
                <input type="hidden" name="command" value="changeLocale">
                <input type="submit" class="btn btn-primary" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">
            </form>
        </div>
        <div class="col-md-5"></div>
    </div>
    <br>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="homepage">
        <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
    </form>
</div>
</body>
</html>
