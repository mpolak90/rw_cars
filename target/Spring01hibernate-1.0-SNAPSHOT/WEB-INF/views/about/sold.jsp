<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="/WEB-INF/views/include/head.jspx" %>

<body>

<h1 style="display: none" id="current">about</h1>
<%@include file="/WEB-INF/views/include/nav.jspf" %>

<%@include file="/WEB-INF/views/include/modal.jspf" %>
<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <div class="article">

                <%@include file="/WEB-INF/views/include/about-bar.jspf" %>

            </div>

        </div>

        <div class="col-lg-9">

            <div class="article">

                <h1>Sprzedane samochody</h1>

                ${article.description}
                <c:if test="${article.img != null}">
                    <img src="${article.img}" alt="obraz image ${article.title}"
                         class="img-fluid">
                </c:if>

                <div class="row" style="margin: 10px">

                    <c:forEach items="${cars}" var="car">

                        <%@include file="/WEB-INF/views/include/display-sold.jspf" %>

                    </c:forEach>

                </div>
            </div>
        </div>
        <!-- /.row -->

    </div>
    <!-- /.col-lg-9 -->

</div>
<!-- /.row -->

<%@include file="/WEB-INF/views/include/footer.jspf" %>

</body>
</html>