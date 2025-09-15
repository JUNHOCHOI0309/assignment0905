<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="mb-0">강좌 목록</h3>
        <a href="${pageContext.request.contextPath}/courses?action=write" class="btn btn-primary">강좌 등록</a>
    </div>

    <form class="form-inline mb-3" method="get" action="${pageContext.request.contextPath}/courses">
        <input type="text" name="q" value="${q}" class="form-control mr-2" placeholder="과목명/코드 검색">
        <button class="btn btn-outline-secondary" type="submit">검색</button>
    </form>

    <c:choose>
        <c:when test="${empty courses}">
            <div class="alert alert-info">검색 결과가 없습니다.</div>
        </c:when>
        <c:otherwise>
            <table class="table table-hover table-sm">
                <thead class="thead-light">
                <tr>
                    <th>No</th><th>과목코드</th><th>과목명</th><th>담당 교수</th><th>학점</th><th class="text-right">수정 | 삭제</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${courses}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.code}</td>
                        <td><a href="${pageContext.request.contextPath}/courses?action=view&id=${c.id}">${c.title}</a></td>
                        <td>${c.professor}</td>
                        <td>${c.credit}</td>
                        <td class="text-right">
                            <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/courses?action=edit&id=${c.id}">수정</a>
                            <form method="post" action="${pageContext.request.contextPath}/courses" class="d-inline" onsubmit="return confirm('삭제할까요?');">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="id" value="${c.id}"/>
                                <button class="btn btn-sm btn-outline-danger" type="submit">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 페이지네이션 -->
            <c:set var="sizeVal" value="${size == 0 ? 10 : size}" />
            <c:set var="totalPages" value="${(totalCount + sizeVal - 1) / sizeVal}" />

            <c:if test="${totalPages > 1}">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:forEach var="p" begin="1" end="${totalPages}">
                            <li class="page-item ${p == page ? 'active' : ''}">
                                <a class="page-link" href="?page=${p}&size=${sizeVal}&q=${q}">${p}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
