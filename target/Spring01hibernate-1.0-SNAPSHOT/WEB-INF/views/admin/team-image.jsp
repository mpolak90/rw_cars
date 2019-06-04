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

                <h1>Wybierz zdjęcie</h1>

                <div>
                    <form action="/admin/team/image/link" style="margin: 20px" method="post">
                        <table>
                            <tr>
                                <td>
                                    <input type="text" value="${member.img}" name="image">
                                </td>
                                <td>
                                    <input type="hidden" value="${member.id}" name="id">
                                    <input type="submit" value="Zapisz z linka">
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form action="/admin/team/image/upload" enctype="multipart/form-data" style="margin: 20px" method="post">
                        <table>
                            <tr>
                                <td>
                                    <input type="file" accept="image/*" name="image">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="hidden" value="${member.id}" name="id">
                                    <input type="submit" value="Wyślij">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div style="margin: 20px">
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
                </div>
                <a href="/admin/articles" style="margin: 20px"><button>Pomiń...</button></a>
            </div>

        </div>

    </div>

</div>

<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>

</html>