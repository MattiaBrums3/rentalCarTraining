<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <div align="center">
        <h1>Admin Homepage</h1>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista Utenti</h2></caption>
            <tr>
                <th>idUser</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Data di Nascita</th>
                <th>Codice Fiscale</th>
                <th>Username</th>
                <th><a href="newUser">Nuovo Utente</a></th>
            </tr>
            <c:forEach var="user" items="${listUsers}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.surname}" /></td>
                    <td><fmt:formatDate value="${user.dateOfBirth}" pattern="dd-MM-yyyy" /></td>
                    <td><c:out value="${user.fiscalCode}" /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td>
                        <a href="editUser?id=<c:out value='${user.id}' />">Modifica</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteUser?id=<c:out value='${user.id}' />">Elimina</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#">Prenotazioni</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
