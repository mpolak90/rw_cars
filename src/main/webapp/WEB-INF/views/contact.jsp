<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl-PL">

<%@ include file="include/head.jspx" %>

<body>

<h1 style="display: none" id="current">contact</h1>
<%@include file="/WEB-INF/views/include/nav.jspf"%>

<%@include file="include/modal.jspf"%>
<!-- Page Content -->
<div class="container">

    <div class="article">

        <h1>Kontakt</h1>

        <hr>

        <c:if test="${response != null}">
            <p>Wiadomość została wysłana. Dziękujemy!</p>
        </c:if>

        <form method="post">
            <table class="table-form">
                <tr>
                    <td style="text-align: right">imię</td>
                    <td><input type="text" name="name" required></td>
                </tr>
                <tr>
                    <td style="text-align: right">mail</td>
                    <td><input type="email" name="email" required></td>
                </tr>
                <tr>
                    <td style="text-align: right">pytania / uwagi</td>
                    <td><textarea required class="text-area" name="content"></textarea></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center"><input type="submit" value="Wyślij" class="btn-dark">
                    </td>
                </tr>
            </table>
        </form>

        <hr>

        ${article.description}
        <c:if test="${article.img != null}">
            <img src="${article.img}" alt="obraz image ${article.title}"
                 class="img-fluid">
        </c:if>
    </div>

</div>

<%@include file="include/footer.jspf"%>

</body>

</html>