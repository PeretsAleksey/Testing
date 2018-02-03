<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<html>
<head>
    <style>
        body {
            background: url(/images/back.png) no-repeat;
            background-size: 100%
        }

    </style>
    <script>
        $(function () {
            $('[data-toggle="popover"]').popover()
        })
    </script>
</head>
<body>
<div class="container">
    <div align="center">
        <c:choose>
            <c:when test="${sessionScope.name eq 'addTheme'}">
                <h4><fmt:message key="enter_theme_name"/></h4>
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <input name="name" class="form-control" placeholder="<fmt:message key="result_jsp.theme_name"/>"><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="create_theme"/>">
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="homepage">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'addQuestion'}">
                <h4><fmt:message key="enter_question_name"/></h4>
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <input name="name" placeholder="<fmt:message key="question_name"/>" class="form-control"><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="create_question"/>">
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="editTest">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'addAnswer'}">
                <h4><fmt:message key="enter_answer_name"/></h4>
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <input name="name" placeholder="<fmt:message key="answer_name"/>" class="form-control"><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="create_answer"/>">
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="editQuestion">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'testDifficulty'}">
                <h4><fmt:message key="enter_difficulty"/></h4>
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">

                                <input name="name" placeholder="<fmt:message key="result_jsp.test_difficulty"/>" class="form-control"><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="change_difficulty"/>">
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="editTest">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'testTime'}">
                <h4><fmt:message key="enter_time"/></h4>
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <input name="name" placeholder="<fmt:message key="test_time_sec"/>" class="form-control"><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="change_time"/>">
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="editTest">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'addTest'}">
                <h4><fmt:message key="select_ndt"/></h4>
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">

                                <input name="name" placeholder="<fmt:message key="result_jsp.test_name"/>" class="form-control"><br>

                                <input name="difficulty" placeholder="<fmt:message key="result_jsp.test_difficulty"/>"
                                       data-container="body" data-toggle="popover" data-placement="left"
                                       data-content="<fmt:message key="available_difficulty"/>" data-trigger="focus"
                                       class="form-control"><br>

                                <input name="time" placeholder="<fmt:message key="test_time_sec"/>" class="form-control"><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="create_test"/>">
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="getTests">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'deleteTheme'}">
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <h4><fmt:message key="sure_theme"/></h4><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="admin_jsp.delete_theme"/>">
                                    <br>
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="homepage">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'deleteTest'}">
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <h4><fmt:message key="sure_test"/></h4><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="delete_test"/>">
                                    <br>
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="getTests">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'deleteQuestion'}">
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <h4><fmt:message key="sure_question"/></h4><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="delete_question"/>">
                                    <br>
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="editTest">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${sessionScope.name eq 'deleteAnswer'}">
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <h4><fmt:message key="sure_answer"/></h4><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="delete_answer"/>">
                                    <br>
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="editQuestion">
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="users_jsp.homepage"/>">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <h4><fmt:message key="new_name"/></h4>
                <table>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <input name="name" placeholder="<fmt:message key="users_jsp.name"/>" class="form-control"><br>
                                <input type="hidden" name="command" value="refactor">
                                <div align="center">
                                    <input type="submit" class="btn btn-primary" value="<fmt:message key="change_name"/>">
                                </div>
                            </form>
                            <br><br>
                            <div align="center">
                                <a href="javascript:history.back()">
                                    <button class="btn btn-default"><fmt:message key="users_jsp.homepage"/></button>
                                </a>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>