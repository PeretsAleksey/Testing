<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<html>
<head>
    <title>Edit test page</title>

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
        <h4><fmt:message key="question_name"/>: ${sessionScope.questionName}</h4><br>

        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading"><b><fmt:message key="admin_jsp.choose_action"/></b></div>
                    <div class="panel-body">
                        <table>
                            <tr>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="refactorPage">
                                        <input type="hidden" name="name" value="question">
                                        <input type="submit" class="btn btn-link" value="<fmt:message key="change_name_question"/>">
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="refactorPage">
                                        <input type="hidden" name="name" value="addAnswer">
                                        <input type="submit" class="btn btn-link" value="<fmt:message key="add_answer"/>">
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>
        <br><br><br>

        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <table style="text-align: center;" class="table table-hover table-condensed table-sm">
                    <thead>
                    <tr>
                        <td><b><fmt:message key="answer_name"/></b></td>
                        <td><b><fmt:message key="answer_status"/></b></td>
                        <td><b><fmt:message key="edit"/></b></td>
                        <td><b><fmt:message key="delete"/></b></td>
                    </tr>
                    </thead>
                    <c:forEach var="bean" items="${sessionScope.answersList}">
                        <tr>
                            <td style="padding: 10px;">${bean.name}</td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="answerStatus">
                                    <input type="hidden" name="answerId" value="${bean.id}">
                                    <c:if test="${bean.correct eq true}">
                                        <input type="submit" class="btn btn-success btn-sm" value="<fmt:message key="correct"/>">
                                    </c:if>
                                    <c:if test="${bean.correct eq false}">
                                        <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="incorrect"/>">
                                    </c:if>
                                </form>
                            </td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="refactorPage">
                                    <input type="hidden" name="name" value="answer">
                                    <input type="hidden" name="answerId" value="${bean.id}">
                                    <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="change_name_answer"/>">
                                </form>
                            </td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="answerId" value="${bean.id}">
                                    <input type="hidden" name="command" value="refactorPage">
                                    <input type="hidden" name="name" value="deleteAnswer">
                                    <input type="submit" class="btn btn-danger btn-sm" value="<fmt:message key="delete_answer"/>">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="col-md-2"></div>
            <br>

        </div>
        <div>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="editTest">
                <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
            </form>
        </div>
    </div>
</body>
</html>