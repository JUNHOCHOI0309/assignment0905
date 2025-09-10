<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">
    <h3 class="mb-3">강좌 상세</h3>

    <table class="table table-bordered">
        <tbody>
        <tr><th style="width:140px">ID</th><td>${course.id}</td></tr>
        <tr><th>과목코드</th><td>${course.code}</td></tr>
        <tr><th>과목명</th><td>${course.title}</td></tr>
        <tr><th>담당 교수</th><td>${course.professor}</td></tr>
        <tr><th>학점</th><td>${course.credit}</td></tr>
        </tbody>
    </table>

    <div class="text-right">
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/courses?action=list">목록</a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/courses?action=edit&id=${course.id}">수정</a>
        <form method="post" action="${pageContext.request.contextPath}/courses" class="d-inline" onsubmit="return confirm('삭제할까요?');">
            <input type="hidden" name="action" value="delete"/>
            <input type="hidden" name="id" value="${course.id}"/>
            <button class="btn btn-danger" type="submit">삭제</button>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
