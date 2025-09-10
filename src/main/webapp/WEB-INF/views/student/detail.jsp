<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">

    <!-- 학생 기본 정보 -->
    <h3 class="mb-3">학생 상세 정보</h3>
    <table class="table table-bordered">
        <tr>
            <th>학번</th>
            <td>${student.studentNo}</td>
        </tr>
        <tr>
            <th>이름</th>
            <td>${student.name}</td>
        </tr>
        <tr>
            <th>이메일</th>
            <td>${student.email}</td>
        </tr>
        <tr>
            <th>학과</th>
            <td>${student.dept}</td>
        </tr>
    </table>

    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">목록</a>
        <a href="${pageContext.request.contextPath}/students/edit?id=${student.id}" class="btn btn-primary">수정</a>
    </div>

    <!-- 수강신청 -->
    <h4 class="mt-4">수강신청</h4>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/enrollments">
        <input type="hidden" name="studentId" value="${student.id}">
        <div class="form-inline">
            <select name="courseId" class="form-control mr-2">
                <c:forEach var="c" items="${availableCourses}">
                    <option value="${c.id}">${c.title} (${c.code})</option>
                </c:forEach>
            </select>
            <button class="btn btn-success">신청</button>
        </div>
    </form>

    <!-- 수강 중인 강좌 목록 -->
    <h4 class="mt-4">수강 중인 강좌</h4>
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>강좌명</th>
            <th>신청일</th>
            <th>취소</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${enrollments}">
            <tr>
                <td>${e.courseTitle}</td>
                <td>${e.enrolledAt}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/enrollments/cancel">
                        <input type="hidden" name="studentId" value="${student.id}">
                        <input type="hidden" name="courseId" value="${e.courseId}">
                        <button class="btn btn-danger btn-sm">취소</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty enrollments}">
            <tr>
                <td colspan="3" class="text-center text-muted">수강 중인 강좌가 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>

</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
