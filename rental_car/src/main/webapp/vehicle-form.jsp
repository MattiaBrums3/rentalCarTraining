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
                        <input type="text" name="licensePlate" maxlength="7" minlenght="7" style="text-transform:uppercase" value="<c:out value='${vehicle.licensePlate}' />" required />
                    </td>
                </tr>
                <tr>
                    <th>Anno Immatricolazione: </th>
                    <td>
                        <input type="number" name="yearOfRegistration" maxlength="4" minlenght="4" style="text-transform:uppercase" value="<c:out value='${vehicle.yearOfRegistration}' />" required />
                    </td>
                </tr>
                <tr>
                    <th>Categoria: </th>
                    <td>
                        <select name="category">
                            <c:if test="${vehicle == null}">
                                <option selected>Seleziona...</option>
                                <c:forEach var="category" items="${listCategories}">
                                    <option value="${category.id}">${category.typology}</option>
                                </c:forEach>
                            </c:if>
                            <c:if test="${vehicle != null}">
                                <c:forEach var="category" items="${listCategories}">
                                    <c:if test="${category.id == vehicle.category.id}">
                                        <option selected value="${category.id}">${category.typology}</option>
                                    </c:if>
                                    <c:if test="${category.id != vehicle.category.id}">
                                        <option value="${category.id}">${category.typology}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
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
