<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="/WEB-INF/views/include/head.jspx" %>

<body>

<%@include file="/WEB-INF/views/include/nav-admin.jspf" %>

<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <div class="article">

                <%@include file="/WEB-INF/views/include/auction-bar.jspf" %>

            </div>

        </div>

        <div class="col-lg-9">

            <div class="article">
                <h1>${title}</h1>
                <table class="table-form" style="margin: 50px">
                    <tr>
                        <th>nazwa pojazdu</th>
                        <th>status</th>
                    </tr>
                    <c:forEach items="${offers}" var="offer">
                        <tr>
                            <td>${offer.name}</td>
                            <c:if test="${offer.status == 'sold'}">
                                <td>${offer.status} ${offer.sold}</td>
                            </c:if>
                            <c:if test="${offer.status != 'sold'}">
                                <td>${offer.status}</td>
                            </c:if>
                            <td><a href="/logged/auctions/details/${offer.id}">
                                <button class="btn-dark">Edytuj</button>
                            </a></td>
                            <c:if test="${offer.status != 'sold'}">
                                <td><a href="/logged/auctions/sold/${offer.id}">
                                    <button class="btn-dark"
                                            onclick="return confirm('Oznaczasz jako \'sprzedany\' samochodód ${offer.name}.');">
                                        Sprzedano
                                    </button>
                                </a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>

            </div>

        </div>

    </div>

</div>

<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>

</html>