<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="/WEB-INF/views/include/head.jspx" %>

<body>

<h1 style="display: none" id="current">about</h1>
<%@include file="/WEB-INF/views/include/nav.jspf" %>

<%@include file="include/modal.jspf" %>
<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <div class="article">

                <%@include file="include/about-bar.jspf" %>

            </div>

        </div>

        <div class="col-lg-9">

            <div class="article">

                <h1>Zespół</h1>
                <div class="row" style="margin: 10px">
                <c:forEach items="${team}" var="member">


                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100">
                            <img class="card-img-top"
                                             src="${member.img}" alt="">
                            <div class="card-body">
                                <h4 class="card-title">
                                   ${member.name}
                                </h4>
                                <p class="card-text">
                                    ${member.description}
                                </p>
                            </div>
                        </div>
                    </div>

                    </c:forEach>
                    </div>
                </div>

            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<%@include file="include/footer.jspf" %>

</body>
</html>