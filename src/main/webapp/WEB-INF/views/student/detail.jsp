<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">

  <h3 class="mb-3">학생 상세 정보</h3>

  <c:if test="${empty student}">
    <div class="alert alert-danger">학생 정보를 찾을 수 없습니다.</div>
  </c:if>

  <c:if test="${not empty student}">
    <table class="table table-bordered">
      <tr>
        <th>ID</th>
        <td>${student.id}</td>
      </tr>
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
      <tr>
        <th>생성일</th>
        <td>${student.createdAt}</td>
      </tr>
    </table>

    <div class="mt-3 text-right">
      <a href="${pageContext.request.contextPath}/front/students/${student.id}/edit" class="btn btn-primary">수정</a>

      <form method="post"
            action="${pageContext.request.contextPath}/front/students/${student.id}/delete"
            class="d-inline"
            onsubmit="return confirm('정말 삭제하시겠습니까?');">
        <button type="submit" class="btn btn-danger">삭제</button>
      </form>

      <a href="${pageContext.request.contextPath}/front/students" class="btn btn-secondary">목록</a>
    </div>
  </c:if>

</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
