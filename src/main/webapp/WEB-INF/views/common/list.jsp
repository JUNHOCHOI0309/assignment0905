<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">

  <div class="d-flex justify-content-between align-items-center mb-3">

    <h3 class="mb-0">학생 목록</h3>

    <a href="${pageContext.request.contextPath}/students/new" class="btn btn-primary">학생 등록</a>

  </div>

  <form class="form-inline mb-3" method="get">

    <input type="text" name="q" value="${param.q}" class="form-control mr-2" placeholder="이름/학번 검색">

    <button class="btn btn-outline-secondary" type="submit">검색</button>

  </form>

  <c:if test="${empty page.items}">

    <div class="alert alert-info">검색 결과가 없습니다.</div>

  </c:if>

  <c:if test="${not empty page.items}">

    <table class="table table-hover table-sm">

      <thead class="thead-light">

      <tr><th>#</th><th>학번</th><th>이름</th><th>학과</th><th></th></tr>

      </thead>

      <tbody>

      <c:forEach var="s" items="${page.items}">

        <tr>

          <td>${s.id}</td>

          <td>${s.studentNo}</td>

          <td><a href="${pageContext.request.contextPath}/students/${s.id}">${s.name}</a></td>

          <td>${s.dept}</td>

          <td class="text-right">

            <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/students/${s.id}/edit">수정</a>

            <form method="post" action="${pageContext.request.contextPath}/students/${s.id}/delete" class="d-inline" onsubmit="return confirm('삭제할까요?');">

              <button class="btn btn-sm btn-outline-danger" type="submit">삭제</button>

            </form>

          </td>

        </tr>

      </c:forEach>

      </tbody>

    </table>

    <!-- 단순 페이징 UI 예시: 서버에서 totalPages 전달 가정 -->

    <nav aria-label="Page navigation">

      <ul class="pagination">

        <c:forEach var="p" begin="1" end="${page.totalPages}">

          <li class="page-item ${p==page.number ? 'active' : ''}">

            <a class="page-link" href="?page=${p}&q=${param.q}">${p}</a>

          </li>

        </c:forEach>

      </ul>

    </nav>

  </c:if>

</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>