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

                <h1>zespół</h1>

                <a href="/admin/team/new" style="margin: 20px"><button>Nowy...</button></a>

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
                                <table>
                                    <tr>
                                        <td><a href="/admin/team/edit/${member.id}"><button>Edytuj</button></a></td>
                                        <td><a href="/admin/team/delete/${member.id}"><button>Usuń</button></a></td>
                                        <td><a href="/admin/team/show/${member.id}"><button>
                                            <c:if test="${member.active}">
                                                Ukryj
                                            </c:if>
                                            <c:if test="${!member.active}">
                                                Pokaż
                                            </c:if>
                                        </button></a></td>
                                    </tr>
                                </table>
                            </div>
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