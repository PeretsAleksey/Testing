<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<head>
    <title>Admin page</title>
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
    <br>
    <div align="center">
        <div class="row">
            <div class="col-md-3">
                <div align="center"><fmt:message key="admin_jsp.homepage"/></div>
            </div>
            <div class="col-md-6">
                <div align="right">
                    <form action="setting.jsp" method="post">
                        <input type="submit" class="btn btn-primary btn-xs" value="<fmt:message key="index_jsp.link.settings"/>">
                    </form>
                </div>
            </div>
            <div class="col-md-3">
                <div align="center">
                    ${sessionScope.user.fName} ${sessionScope.user.lName} (<fmt:message key="users_jsp.admin"/>)
                    <a href="controller?command=logout"><fmt:message key="admin_jsp.logout"/></a>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <br>
    <div align="center">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading"><b><fmt:message key="admin_jsp.choose_action"/></b></div>
                    <div class="panel-body">
                        <table>
                            <tr>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="refactorPage">
                                        <input type="hidden" name="name" value="addTheme">
                                        <input type="submit" class="btn btn-link" value="<fmt:message key="admin_jsp.add_theme"/>">
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="getUsers">
                                        <input type="submit" class="btn btn-link" value="<fmt:message key="admin_jsp.show_users"/>">
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="result">
                                        <input type="submit" class="btn btn-link" value="<fmt:message key="admin_jsp.show_results"/>">
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
        <br>
        <h4><fmt:message key="admin_jsp.pass_edit"/></h4><br>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <table style="text-align: center;" class="table table-hover table-condensed table-sm ">
                    <thead>
                    <tr>
                        <td><b><fmt:message key="admin_jsp.theme_name"/></b></td>
                        <td><b><fmt:message key="admin_jsp.theme_status"/></b></td>
                        <td><b><fmt:message key="admin_jsp.theme_edit"/></b></td>
                        <td><b><fmt:message key="admin_jsp.theme_delete"/></b></td>
                    </tr>
                    </thead>
                    <c:forEach var="bean" items="${sessionScope.themesList}">
                        <tr>
                            <td style="padding: 10px;">${bean.name}</td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="blockTheme">
                                    <c:if test="${bean.blocked eq true}">
                                        <input type="hidden" name="themeId" value="${bean.id}">
                                        <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="admin_jsp.unblock"/>">
                                    </c:if>
                                    <c:if test="${bean.blocked eq false}">
                                        <input type="hidden" name="themeId" value="${bean.id}">
                                        <input type="submit" class="btn btn-warning btn-sm" value="<fmt:message key="admin_jsp.block"/>">
                                    </c:if>
                                </form>
                            </td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="theme" value="${bean.id}">
                                    <input type="hidden" name="themeName" value="${bean.name}">
                                    <input type="hidden" name="command" value="getTests">
                                    <input type="submit" class="btn btn-primary btn-sm"
                                           value="<fmt:message key="admin_jsp.pass_edit_tests"/>">
                                </form>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="refactorPage">
                                    <input type="hidden" name="theme" value="${bean.id}">
                                    <input type="hidden" name="name" value="deleteTheme">
                                    <input type="submit" class="btn btn-danger btn-sm" value="<fmt:message key="admin_jsp.delete_theme"/>">
                                </form>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
</div>
</body>
</html>
