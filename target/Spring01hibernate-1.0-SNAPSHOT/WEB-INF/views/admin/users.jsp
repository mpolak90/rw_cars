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
                <table style="margin: 20px" class="w-100">
                    <tr>
                        <th>login</th>
                        <th>admin</th>
                        <th>hasło</th>
                        <th>uprawnienia</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.login}</td>
                            <td>
                                <c:if test="${user.admin}">
                                    tak
                                </c:if>
                                <c:if test="${!user.admin}">
                                    nie
                                </c:if>
                            </td>
                            <td><a href="/admin/users/password/${user.id}">
                                <button>zmień</button>
                            </a></td>
                            <td><a href="/admin/users/admin/${user.id}">
                                <button>
                                    <c:if test="${user.admin}">
                                        odbierz
                                    </c:if>
                                    <c:if test="${!user.admin}">
                                        nadaj
                                    </c:if>
                                </button>
                            </a></td>
                            <td>
                                <a href="/admin/users/block/${user.id}">
                                    <c:if test="${user.active}">
                                        <button>zablokuj</button>
                                    </c:if>
                                    <c:if test="${!user.active}">
                                        <button>odblokuj</button>
                                    </c:if>
                                </a>
                            </td>
                            <c:if test="${!user.active}">
                                <td>
                                    <a href="/admin/users/delete/${user.id}">
                                        <button>usuń</button>
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>

    </div>

</div>

<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>

</html>