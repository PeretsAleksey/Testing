<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib uri="http://nure.ua.perets/SummaryTask4" prefix="MyTags" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<html>
<head>
    <title>Edit test page</title>

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
        <h4><fmt:message key="result_jsp.theme_name"/>: ${sessionScope.themeName}</h4>
        <h4><fmt:message key="result_jsp.test_name"/>: ${sessionScope.testName}</h4>
        <h4><fmt:message key="result_jsp.test_difficulty"/>: ${sessionScope.testDifficulty}</h4>
        <h4><fmt:message key="test_time"/>: <MyTags:formatToHour number="${sessionScope.testTime}"/> <fmt:message key="hours"/>
            <MyTags:formatToMin number="${sessionScope.testTime}"/> <fmt:message key="min"/> ${sessionScope.testTime%60}
            <fmt:message key="sec"/></h4><br>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="panel panel-primary">
                    <div class="panel-heading"><b><fmt:message key="admin_jsp.choose_action"/></b></div>
                    <div class="panel-body">
                        <table>
                            <tr>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="refactorPage">
                                        <input type="hidden" name="name" value="test">
                                        <input type="submit" class="btn btn-link" value="<fmt:message key="change_test_name"/>">
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="refactorPage"> <input
                                            type="hidden" name="name" value="testDifficulty"> <input
                                            type="submit" class="btn btn-link" value="<fmt:message key="change_difficulty"/>">
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="refactorPage"> <input
                                            type="hidden" name="name" value="testTime"> <input
                                            type="submit" class="btn btn-link" value="<fmt:message key="change_time"/>">
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="refactorPage">
                                        <input type="hidden" name="name" value="addQuestion"><input
                                            type="submit" class="btn btn-link" value="<fmt:message key="add_question"/>">
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <br>
        <br>
        <br>
        <table style="text-align: center;" class="table table-hover table-condensed table-sm">
            <thead>
            <tr>
                <td><b><fmt:message key="question_name"/></b></td>
                <td><b><fmt:message key="admin_jsp.theme_edit"/></b></td>
                <td><b><fmt:message key="admin_jsp.theme_delete"/></b></td>
            </tr>
            </thead>
            <c:forEach var="bean" items="${sessionScope.questionList}">
                <tr>
                    <td style="padding: 10px;">${bean.name}</td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="questionId" value="${bean.id}">
                            <input type="hidden" name="questionName" value="${bean.name}">
                            <input type="hidden" name="command" value="editQuestion">
                            <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="edit_question"/>">
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="questionId" value="${bean.id}">
                            <input type="hidden" name="command" value="refactorPage">
                            <input type="hidden" name="name" value="deleteQuestion">
                            <input type="submit" class="btn btn-danger btn-sm" value="<fmt:message key="delete_question"/>">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="getTests">
            <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
        </form>
    </div>
</div>
</body>
</html>