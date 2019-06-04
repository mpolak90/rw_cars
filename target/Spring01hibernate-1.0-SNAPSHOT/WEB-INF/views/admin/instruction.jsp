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

                <h1>Intrukcja edytora</h1>
                <p>
                    Wszystkie znaki cudzysłowu zostaną automatycznie usunięte. Wszystkie tagi formatowania
                    tekstu posiadają znaczniki otwarcia i zamknięcia. Niedomknięty tag może skutkować
                    nieprawidłowym formatowaniem tekstu. Nie ma możliwości cofnięcia już zapisanych zmian. Podstawowe
                    znaczniki formatowania zostały przedstawione w tabeli poniżej.
                </p>
                <table style="margin: 20px" border="1">
                    <tr>
                        <th>tagi</th>
                        <th>znaczenie</th>
                    </tr>
                    <tr>
                        <td>&ltp&gttekst&lt/p&gt</td>
                        <td>oddzielny akapit</td>
                    </tr>
                    <tr>
                        <td>&ltb&gttekst&lt/b&gt</td>
                        <td><b>pogrubienie</b></td>
                    </tr>
                    <tr>
                        <td>&lti&gttekst&lt/i&gt</td>
                        <td><i>kursywa</i></td>
                    </tr>
                    <tr>
                        <td>&ltu&gttekst&lt/u&gt</td>
                        <td><u>podkreślenie</u></td>
                    </tr>
                    <tr>
                        <td>&lthr&gt
                        <td>pozioma kreska
                            <hr>
                        </td>
                    </tr>
                    <tr>
                        <td>&ltbr&gt
                        <td>enter (nowa linia)</td>
                    </tr>
                </table>
            </div>

        </div>

    </div>

</div>

<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>

</html>