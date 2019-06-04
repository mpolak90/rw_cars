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

                <%@include file="/WEB-INF/views/include/users-bar.jspf" %>

            </div>

        </div>

        <div class="col-lg-9">

            <div class="article">
                <h1>${title}</h1>
                <%@include file="/WEB-INF/views/include/user-password.jspf" %>

            </div>

        </div>

    </div>

</div>

<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>

</html>