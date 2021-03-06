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

                <div class="row" style="margin: 10px">

                    <%@include file="/WEB-INF/views/include/details-form.jspf" %>

                    <%@include file="/WEB-INF/views/include/display-details.jspf" %>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/new.js"></script>
<%@include file="/WEB-INF/views/include/footer.jspf" %>

</body>

</html>