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

                <h1>${title}</h1>
                <form action="/admin/team/step" method="post" style="margin: 20px">
                    <table>
                        <tr>
                            <td>
                                Nazwa członka:
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="name" required value="${member.name}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Opis:
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <textarea name="description" class="w-100" style="height: 200px" required>${member.description}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="hidden" value="${member.id}" name="id">
                                <input type="submit" value="Dalej...">
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