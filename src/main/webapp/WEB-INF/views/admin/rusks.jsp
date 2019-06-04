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

                <%@include file="/WEB-INF/views/include/articles-bar.jspf" %>

            </div>

        </div>

        <div class="col-lg-9">
            <div class="article">
                <h1>Suchary</h1>
                <p>${error}</p>
                <hr>
                <form action="/admin/rusks/save" method="post" style="margin: 20px; max-width: 100%">
                    <table>
                        <tr>
                            <td><input name="image" type="text" required></td>
                            <td><input type="submit" value="dodaj link"></td>
                        </tr>
                    </table>
                </form>
                <form style="margin: 20px" enctype="multipart/form-data" action="/admin/rusks/add" method="post">
                    <table>
                        <tr>
                            <td><input type="file" name="image" id="image_upload"
                                       accept="image/*" style="max-width: 275px">
                            </td>
                            <td><input type="submit" value="Wyślij"></td>
                        </tr>
                    </table>
                </form>
                <hr>
                <div class="row" style="margin: 10px">
                    <c:forEach var="rusk" items="${rusks}">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <img class="card-img-top" src="${rusk.img}" alt="obrazek image śmieszne fun">
                            <a href="/admin/rusks/delete/${rusk.id}">
                                <button class="btn-danger">Usuń</button>
                            </a>
                        </div>
                    </c:forEach>
                </div>

            </div>

        </div>

    </div>

</div>

<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>

</html>