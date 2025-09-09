<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">

    <h3 class="mb-3">${mode == 'edit' ? '학생 수정' : '학생 등록'}</h3>

    <c:if test="${not empty errors}">

        <div class="alert alert-danger">

            <ul class="mb-0">

                <c:forEach var="e" items="${errors}"><li>${e}</li></c:forEach>

            </ul>

        </div>

    </c:if>

    <form method="post" action="${action}">

        <div class="form-group">

            <label>학번</label>

            <input name="studentNo" value="${student.studentNo}" class="form-control" required>

            <small class="form-text text-muted">예: 20250001</small>

        </div>

        <div class="form-group">

            <label>이름</label>

            <input name="name" value="${student.name}" class="form-control" required>

        </div>

        <div class="form-group">

            <label>이메일</label>

            <input name="email" value="${student.email}" type="email" class="form-control">

        </div>

        <div class="form-group">

            <label>학과</label>

            <input name="dept" value="${student.dept}" class="form-control">

        </div>

        <div class="text-right">

            <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">취소</a>

            <button class="btn btn-primary" type="submit">${mode == 'edit' ? '수정' : '등록'}</button>

        </div>

    </form>

</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>