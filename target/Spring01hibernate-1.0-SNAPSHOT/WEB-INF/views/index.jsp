<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="include/head.jspx" %>

<body>

<%@include file="include/nav.jspf"%>

<%@include file="include/modal.jspf"%>

<h1 id="current" style="display: none">index</h1>
<!-- Page Content -->
<div class="container" style="max-width: 1200px">

    <div class="row">

        <%--<div class="col-lg-3">

            <h1 class="my-4">Oferty</h1>
            <div class="list-group">
                <a href="/" class="list-group-item">Najnowsze</a>
                <a href="/promo" class="list-group-item">Promocje</a>
                <a href="/previews" class="list-group-item">Zapowiedzi</a>

            </div>

        </div>--%>
        <!-- /.col-lg-3 -->

        <div class="col-lg-12">

            <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                <ol class="carousel-indicators">
                    <%--<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>--%>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img class="d-block img-fluid" src="/images/auctions/image1.png" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="/images/auctions/image2.png" alt="Second slide">
                    </div>
<%--                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="/images/auctions/bigger_three.jpg" alt="Third slide">
                    </div>--%>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <h1 class="title">Aktualne oferty</h1>

            <div class="row" style="max-width: 1000px; margin-left: auto; margin-right: auto">

                <c:forEach items="${cars}" var="car">
                    <%@include file="include/display.jspf" %>
                </c:forEach>

            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<%@include file="include/footer.jspf" %>

</body>

</html>