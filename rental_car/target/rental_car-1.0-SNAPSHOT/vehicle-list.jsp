<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Veicoli</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista Veicoli</h2></caption>
        <tr>
            <th>idVeicolo</th>
            <th>Modello</th>
            <th>Casa Produttrice</th>
            <th>Targa</th>
            <th>Anno Immatr.</th>
            <th>Categoria</th>
            <th><a href="newVehicle">Nuovo Veicolo</a></th>
        </tr>
        <c:forEach var="vehicle" items="${listVehicles}">
            <tr>
                <td><c:out value="${vehicle.id}" /></td>
                <td><c:out value="${vehicle.model}" /></td>
                <td><c:out value="${vehicle.manufacturer}" /></td>
                <td><c:out value="${vehicle.licensePlate}" /></td>
                <td><c:out value="${vehicle.yearOfRegistration}" /></td>
                <td><c:out value="${vehicle.category.typology}" /></td>
                <td>
                    <a href="editVehicle?id=<c:out value='${vehicle.id}' />">Modifica</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deleteVehicle?id=<c:out value='${vehicle.id}' />">Elimina</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
