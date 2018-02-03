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
</head>
<body onload="startTimer()">
<div class="container">
    <div align="center">
        <form action="controller" method="post">
            <c:if test="${requestScope.timeout ne true}">
                <c:if test="${sessionScope.singleQuestion ne 0 }">
                    <h4><fmt:message key="question_n"/> ${sessionScope.questionId+1}/${sessionScope.questionCount}</h4>
                </c:if>
                <c:if test="${sessionScope.singleQuestion ne 0 }">
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div align="center" class="col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading"><b> ${requestScope.question.name}</b></div>
                                <div class="panel-body">
                                    <c:forEach var="bean" items="${sessionScope.answers}">
                                        <div align="left"><input type="checkbox" name="answer" value="${bean.name}">${bean.name}</div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </c:if>
                <br>
                <c:if test="${sessionScope.singleQuestion eq 0 }">
                    <h4><fmt:message key="test_passed"/></h4>
                </c:if>
            </c:if>
            <c:if test="${requestScope.timeout eq true}">
                <h4><fmt:message key="test_timeout"/></h4>
            </c:if>
            <c:if test="${requestScope.timeout ne true}">
                <c:if test="${sessionScope.singleQuestion gt 1 }">
                    <input type="submit" class="btn btn-primary" name="previous" value="<fmt:message key="previous_answer"/>">
                    <input type="submit" class="btn btn-primary" name="next" value="<fmt:message key="next_answer"/>">
                </c:if>
                <c:if test="${sessionScope.singleQuestion gt 0 }">
                    <input type="submit" class="btn btn-success" name="commit" value="<fmt:message key="commit"/>">
                </c:if></c:if>
            <input type="submit" class="btn btn-primary" name="end" value="<fmt:message key="end_test"/>">
            <input type="hidden" name="command" value="passTest">
            <input type="hidden" name="qid" value="${requestScope.question.id}">
            <input type="hidden" name="uid" value="${sessionScope.user.id}">
        </form>
    </div>
</div>
</body>
</html>