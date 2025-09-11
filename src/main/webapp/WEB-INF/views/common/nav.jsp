<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">

  <a class="navbar-brand" href="${pageContext.request.contextPath}/">Univ</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample" aria-controls="navbarsExample" aria-expanded="false" aria-label="Toggle navigation">

    <span class="navbar-toggler-icon"></span>

  </button>

  <div class="collapse navbar-collapse" id="navbarsExample">

    <ul class="navbar-nav mr-auto">

      <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/front/students">학생</a></li>

      <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/courses">강좌</a></li>

      <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/enrollments">수강</a></li>

    </ul>

  </div>

</nav>