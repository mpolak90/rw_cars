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

                <h1 id="title">${article.title}</h1>
                <p>${error}</p>
                <form action="/admin/articles/update/${article.id}" style="margin: 20px" method="post">
                    <table class="w-100">
                        <tr>
                            <td>
                                <textarea name="description" class="w-100"
                                          style="height: 250px">${article.description}</textarea>

                            </td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Zapisz zmiany"></td>
                        </tr>
                    </table>
                </form>
                <hr>
                <div style="margin: 20px">
                                    Aktualny obraz pod artykułem:
                    <c:if test="${article.img == null}">
                        brak
                    </c:if>
                    <c:if test="${article.img != null}">
                        <div><img style="max-width: 100%" src="${article.img}" alt="obraz do artykułu"></div>
                        <a href="/admin/articles/image/delete/${article.id}"><button>Usuń obraz</button></a>
                    </c:if>
                </div>
                <form style="margin: 20px" action="/admin/articles/image/${article.id}" method="post" enctype="multipart/form-data">
                    <table class="w-100">
                        <tr>
                            <td>
                                <input type="file" accept="image/*" name="image">
                            </td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Wyślij zdjęcie"></td>
                        </tr>
                    </table>
                </form>
                <form style="margin: 20px" action="/admin/articles/link/${article.id}" method="post">
                    <table>
                        <tr>
                            <td>
                                <input type="text" name="link" value="${article.img}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Wstaw z linka">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>

</html>