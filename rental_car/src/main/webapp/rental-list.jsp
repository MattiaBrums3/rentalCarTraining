<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Prenotazioni</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista Prenotazioni</h2></caption>
        <tr>
            <th>idPrenotazione</th>
            <th>Utente</th>
            <th>Veicolo</th>
            <th>Data di Inizio</th>
            <th>Data di Fine</th>
            <th><a href="#">Nuova Prenotazione</a></th>
        </tr>
        <c:forEach var="rental" items="${listRentals}">
            <tr>
                <td><c:out value="${rental.id}" /></td>
                <td><c:out value="${rental.user.name} ${rental.user.surname}" /></td>
                <td><c:out value="${rental.vehicle.model}" /></td>
                <td><fmt:formatDate value="${rental.dateOfStart}" pattern="dd-MM-yyyy" /></td>
                <td><fmt:formatDate value="${rental.dateOfEnd}" pattern="dd-MM-yyyy" /></td>
                <td>
                    <a href="#">Accetta</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Rifiuta</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

