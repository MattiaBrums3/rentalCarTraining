<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Prenotazioni</title>
</head>
<body>
<c:if test="${empty listRentals}">
    <div align="center">
        <h4>L'utente non ha effettuato prenotazioni.</h4>
    </div>
</c:if>
<c:if test="${not empty listRentals}">
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista Prenotazioni</h2></caption>
            <tr>
                <th>idPrenotazione</th>
                <th>Utente</th>
                <th>Veicolo</th>
                <th>Data di Inizio</th>
                <th>Data di Fine</th>
            </tr>
            <c:forEach var="rental" items="${listRentals}">
                <tr>
                    <td><c:out value="${rental.id}" /></td>
                    <td><c:out value="${rental.user.name} ${rental.user.surname}" /></td>
                    <td><c:out value="${rental.vehicle.model}" /></td>
                    <td><fmt:formatDate value="${rental.dateOfStart}" pattern="dd-MM-yyyy" /></td>
                    <td><fmt:formatDate value="${rental.dateOfEnd}" pattern="dd-MM-yyyy" /></td>
                    <td>
                        <c:if test="${rental.approved == false}">
                            <div align="center">
                                <a href="approveRental?approved=true&id=<c:out value='${rental.id}' />">Accetta</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="approveRental?approved=false&id=<c:out value='${rental.id}' />">Rifiuta</a>
                            </div>
                        </c:if>
                        <c:if test="${rental.approved == true}">
                            Prenotazione Approvata
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>

