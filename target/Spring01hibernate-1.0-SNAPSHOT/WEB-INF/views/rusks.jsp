<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="include/head.jspx" %>

<body>

<h1 style="display: none" id="current">rusks</h1>
<%@include file="/WEB-INF/views/include/nav.jspf" %>

<%@include file="include/modal.jspf" %>

<div class="container">

    <div class="article">

        <h1>Trochę uśmiechu dla każdego!</h1>

        <hr>

        <div class="row" style="margin: 10px">

            <c:forEach var="rusk" items="${rusks}">
                <div class="col-lg-4 col-md-6 mb-4">
                    <a href="${rusk.img}"><img class="card-img-top" src="${rusk.img}" alt="obrazek image śmieszne fun"></a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="include/footer.jspf" %>

</body>

</html>