<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">
  <h3 class="mb-3">강좌 수정</h3>

  <form method="post" action="${pageContext.request.contextPath}/courses">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${course.id}"/>

    <div class="form-group">
      <label>과목코드</label>
      <input name="code" value="${course.code}" class="form-control" required>
    </div>

    <div class="form-group">
      <label>과목명</label>
      <input name="title" value="${course.title}" class="form-control" required>
    </div>

    <div class="form-group">
      <label>담당 교수</label>
      <input name="professor" value="${course.professor}" class="form-control">
    </div>

    <div class="form-group">
      <label>학점</label>
      <input name="credit" type="number" value="${course.credit}" class="form-control" min="0" max="10">
    </div>

    <div class="text-right">
      <a href="${pageContext.request.contextPath}/courses?action=list" class="btn btn-secondary">취소</a>
      <button class="btn btn-primary" type="submit">수정</button>
    </div>
  </form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
