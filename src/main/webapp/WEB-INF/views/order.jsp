<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="/WEB-INF/views/include/head.jspx" %>

<body>

<h1 style="display: none" id="current">service</h1>
<%@include file="/WEB-INF/views/include/nav.jspf" %>

<%@include file="include/modal.jspf" %>
<!-- Page Content -->
<div class="container">

    <div class="row">


        <div class="col-lg-12">

            <div class="article">

                <h1>Zamów auto</h1>

                ${article.description}

                <c:if test="${article.img != null}">
                <img src="${article.img}" alt="obraz image ${article.title}"
                     class="img-fluid">
                </c:if>
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