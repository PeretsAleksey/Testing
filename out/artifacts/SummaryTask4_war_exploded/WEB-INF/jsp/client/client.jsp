<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <title>Student page</title>
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
        <br>
        <div class="row">
            <div class="col-md-3">
                <div align="center"><fmt:message key="student_homepage"/></div>
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
                    ${sessionScope.user.fName} ${sessionScope.user.lName} (<fmt:message key="users_jsp.student"/>)
                    <a href="controller?command=logout"><fmt:message key="admin_jsp.logout"/></a>
                </div>
            </div>
        </div>
        <br>
        <br>
        <br>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading"><b><fmt:message key="admin_jsp.choose_action"/></b></div>
                    <div class="panel-body">
                        <table>
                            <tr>
                                <td>
                                    <form action="controller" method="get">
                                        <input type="hidden" name="command" value="getUsers"> <input
                                            type="submit" class="btn btn-link" value="<fmt:message key="personal_information"/>">
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="get">
                                        <input type="hidden" name="command" value="result"> <input
                                            type="submit" class="btn btn-link" value="<fmt:message key="u_results"/>">
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>
        <br>
        <h4><fmt:message key="theme_to_pass"/></h4>
        <br>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <table style="text-align: center;" class="table table-hover table-condensed table-sm ">
                    <thead>
                    <tr>
                        <td><b><fmt:message key="result_jsp.theme_name"/></b></td>
                        <td><b><fmt:message key="show"/></b></td>
                    </tr>
                    </thead>
                    <c:forEach var="bean" items="${sessionScope.themesList}">
                        <c:if test="${bean.blocked eq false }">
                            <tr>
                                <td style="padding: 10px;">${bean.name}</td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="theme" value="${bean.id}">
                                        <input type="hidden" name="themeName" value="${bean.name}">
                                        <input type="hidden" name="command" value="getTests">
                                        <input type="submit" class="btn btn-primary" value="<fmt:message key="show_tests"/>">
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</body>
</html>
