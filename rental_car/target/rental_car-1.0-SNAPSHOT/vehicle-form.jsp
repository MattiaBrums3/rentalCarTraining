<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:if test="${vehicle != null}">
        <title>Modifica Veicolo</title>
    </c:if>
    <c:if test="${vehicle == null}">
        <title>Nuovo Veicolo</title>
    </c:if>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="center">
    <c:if test="${vehicle != null}">
    <form action="updateVehicle" method="post">
        </c:if>
        <c:if test="${vehicle == null}">
        <form action="insertVehicle" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${vehicle != null}">
                            Modifica Veicolo
                        </c:if>
                        <c:if test="${vehicle == null}">
                            Nuovo Veicolo
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${vehicle != null}">
                    <input type="hidden" name="id" value="<c:out value='${vehicle.id}' />" />
                </c:if>
                <tr>
                    <th>Modello: </th>
                    <td>
                        <input type="text" name="model" value="<c:out value='${vehicle.model}' />" required />
                    </td>
                </tr>
                <tr>
                    <th>Casa Produttrice: </th>
                    <td>
                        <input type="text" name="manufacturer" value="<c:out value='${vehicle.manufacturer}' />" required />
                    </td>
                </tr>
                <tr>
                    <th>Targa: </th>
                    <td>
                        <input type="text" name="licensePlate" value="<c:out value='${vehicle.licensePlate}' />" required />
                    </td>
                </tr>
                <tr>
                    <th>Anno Immatricolazione: </th>
                    <td>
                        <input type="text" name="yearOfRegistration" value="<c:out value='${vehicle.yearOfRegistration}' />" required />
                    </td>
                </tr>
                <tr>
                    <th>Categoria: </th>
                    <td>
                        <select name="category">
                            <option selected>Seleziona...</option>
                            <c:forEach var="category" items="${listCategories}">
                                <option value="${category.id}">${category.typology}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <c:if test="${vehicle != null}">
                            <input type="submit" value="Modifica" />
                        </c:if>
                        <c:if test="${vehicle == null}">
                            <input type="submit" value="Inserisci" />
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
