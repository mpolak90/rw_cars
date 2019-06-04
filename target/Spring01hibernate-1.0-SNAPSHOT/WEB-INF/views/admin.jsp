<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="include/head.jspx"%>

<body>
<form method="post" class="table-dark bg-blue" style="text-align: center; padding: 15px 0 15px 0">
    <table style="margin: auto">
        <tr>
            <td colspan="2" style="text-align: center; padding: 15px 0 15px 0"><img src="/images/icons/logo.png" class="logo"></td>
        </tr>
        <c:if test="${error != null}">
            <span class="error">${error}</span>
        </c:if>

        <tr>
            <td style="color: whitesmoke">login:</td>
            <td><input type="text" name="login" required autofocus></td>
        </tr>
        <tr>
            <td style="color: whitesmoke">hasło:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center; padding: 15px 0 15px 0"><input type="submit" value="Zaloguj"></td>
        </tr>
    </table>
</form>
</body>
</html>
