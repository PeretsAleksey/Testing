<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib uri="http://nure.ua.perets/SummaryTask4" prefix="MyTags" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<html>
<head>
    <title>Tests page</title>

    <style type="text/css">
        form {
            width: auto;
            margin: 0 auto;
        }

        .table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th {
            background-color: white;
        }

        body {
            background: url(/images/back.png) no-repeat;
            background-size: 100%
        }
    </style>
</head>

<body>
<div class="container">
    <div align="center">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form action="controller" method="post" class="form-inline">
                    <fmt:message key="sort_by"/>
                    <select name="sort" class="form-control" onchange="this.form.submit()">
                        <option selected disabled><fmt:message key="sort_parameter"/> </option>
                        <option value="name"><fmt:message key="admin_jsp.theme_name"/></option>
                        <option value="diff"><fmt:message key="difficulty"/></option>
                        <option value="qcount"><fmt:message key="questions_count"/></option>
                    </select>
                    <input type="hidden" name="command" value="getTests">
                </form>
            </div>
        </div>
        <div class="col-md-4"></div>
        <br>
        <h4><fmt:message key="result_jsp.theme_name"/>: ${sessionScope.themeName}</h4><br>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <c:if test="${sessionScope.user.roleId eq 0 }">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><b><fmt:message key="admin_jsp.choose_action"/></b></div>
                        <div class="panel-body">
                            <table>
                                <tr>
                                    <td>
                                        <c:if test="${sessionScope.user.roleId eq 0 }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="refactorPage">
                                                <input type="hidden" name="name" value="theme">
                                                <input type="submit" class="btn btn-link" value="<fmt:message key="change_theme_name"/>">
                                            </form>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${sessionScope.user.roleId eq 0 }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="refactorPage">
                                                <input type="hidden" name="name" value="addTest">
                                                <input type="submit" class="btn btn-link" value="<fmt:message key="add_test"/>">
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="col-md-4"></div>
        </div>
        <br>
        <br>
        <br>
        <table style="text-align: center;" class="table table-hover table-condensed table-sm">
            <thead>
            <tr>
                <td><b><fmt:message key="result_jsp.test_name"/></b></td>
                <td><b><fmt:message key="result_jsp.test_difficulty"/></b></td>
                <td><b><fmt:message key="questions_count"/></b></td>
                <td><b><fmt:message key="test_time"/></b></td>
                <td><b><fmt:message key="pass"/></b></td>
                <c:if test="${sessionScope.user.roleId eq 0 }">
                    <td><b><fmt:message key="edit"/></b></td>
                    <td><b><fmt:message key="delete"/></b></td>
                </c:if>
            </tr>
            </thead>
            <c:forEach var="bean" items="${sessionScope.tests}">
                <tr>
                    <td style="padding: 10px;">${bean.name}</td>
                    <td style="padding: 10px;">${bean.difficulty}</td>
                    <td style="padding: 10px;">${bean.questionsCount}</td>
                    <td style="padding: 10px;"><MyTags:formatToHour number="${bean.timeForTest}"/> <fmt:message key="hours"/>
                        <MyTags:formatToMin number="${bean.timeForTest}"/> <fmt:message key="min"/>
                            ${bean.timeForTest%60} <fmt:message key="sec"/></td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="passTest">
                            <input type="hidden" name="questionId" value="0">
                            <input type="hidden" name="testId" value="${bean.id}">
                            <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="Pass_test"/>">
                        </form>
                    </td>
                    <c:if test="${sessionScope.user.roleId eq 0 }">
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="testId" value="${bean.id}">
                            <input type="hidden" name="testName" value="${bean.name}">
                            <input type="hidden" name="testDifficulty" value="${bean.difficulty}">
                            <input type="hidden" name="testTime" value="${bean.timeForTest}">
                            <input type="hidden" name="command" value="editTest">
                            <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="edit_test"/>">
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="refactorPage">
                            <input type="hidden" name="name" value="deleteTest">
                            <input type="hidden" name="testId" value="${bean.id}">
                            <input type="submit" class="btn btn-danger btn-sm" value="<fmt:message key="delete_test"/>">
                        </form>
                        </c:if>
                </tr>
            </c:forEach>
        </table>
        <br>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="homepage"> <input
                type="submit" class="btn btn-default" value="<fmt:message key="settings_jsp.link.back_to_main_page"/>">
        </form>
    </div>
</div>
</body>
</html>