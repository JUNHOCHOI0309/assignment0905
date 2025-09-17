<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">

    <h3 class="mb-3">학생 상세 정보</h3>

    <c:choose>
        <c:when test="${empty student}">
            <div class="alert alert-danger">학생 정보를 찾을 수 없습니다.</div>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered">
                <tr><th>ID</th><td>${student.id}</td></tr>
                <tr><th>학번</th><td>${student.studentNo}</td></tr>
                <tr><th>이름</th><td>${student.name}</td></tr>
                <tr><th>이메일</th><td>${student.email}</td></tr>
                <tr><th>학과</th><td>${student.dept}</td></tr>
                <tr><th>생성일</th><td>${student.createdAt}</td></tr>
            </table>

            <div class="mt-3 text-right">
                <a href="${pageContext.request.contextPath}/front/students/${student.id}/edit" class="btn btn-primary">수정</a>
                <form method="post" action="${pageContext.request.contextPath}/front/students/${student.id}/delete"
                      class="d-inline" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                    <button type="submit" class="btn btn-danger">삭제</button>
                </form>
                <a href="${pageContext.request.contextPath}/front/students" class="btn btn-secondary">목록</a>
            </div>

            <!-- ====== Enrollment 섹션 시작 ====== -->
            <h5 class="mt-5">수강 강좌</h5>

            <c:if test="${empty enrollments}">
                <div class="alert alert-info">수강 중인 강좌가 없습니다.</div>
            </c:if>

            <c:if test="${not empty enrollments}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>강좌 코드</th>
                        <th>과목명</th>
                        <th>담당 교수</th>
                        <th>신청일</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="enroll" items="${enrollments}" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${enroll.course.code}</td>
                            <td>${enroll.course.title}</td>
                            <td>${enroll.course.professor}</td>
                            <td>${enroll.enrolledAt}</td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/front/enrollments/cancel"
                                      onsubmit="return confirm('수강 취소하시겠습니까?');">
                                    <input type="hidden" name="studentId" value="${student.id}">
                                    <input type="hidden" name="courseId" value="${enroll.course.id}">
                                    <button type="submit" class="btn btn-sm btn-outline-danger">취소</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <!-- ====== 수강 신청 폼 ====== -->
            <h5 class="mt-4">수강 신청</h5>
            <form method="post" action="${pageContext.request.contextPath}/front/enrollments">
                <input type="hidden" name="studentId" value="${student.id}">
                <select name="courseId" class="form-control mb-2" required>
                    <c:forEach var="c" items="${availableCourses}">
                        <option value="${c.id}">${c.title} (${c.code})</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-success">신청</button>
            </form>
            <!-- ====== Enrollment 섹션 끝 ====== -->

        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
