<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
    <div align="center">
        <h2>Lista Utenti</h2>
        Ricerca per:
        <select id="searchField" name="searchField">
            <option value="id" selected>idUtente</option>
            <option value="name">Nome</option>
            <option value="surname">Cognome</option>
            <option value="birthDate">Data di Nascita</option>
            <option value="fiscalCode">Codice Fiscale</option>
            <option value="username">Username</option>
        </select>
        <input type="text" id="searchUsers" onkeyup="searchFunction()" value="" placeholder="Ricerca..." />
        <table id="usersTable" border="1" cellpadding="5">
            <thead>
                <th>idUtente</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Data di Nascita</th>
                <th>Codice Fiscale</th>
                <th>Username</th>
                <th><a href="newUser">Nuovo Utente</a></th>
            </thead>
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
                        <a href="rentals?id=<c:out value='${user.id}' />">Prenotazioni</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <script>
        function searchFunction() {
            var input, field, filter, table, tr, td, i, txtValue;
            input = document.getElementById("searchUsers");
            field = document.getElementById("searchField").value;
            filter = input.value.toUpperCase();
            table = document.getElementById("usersTable");
            tr = table.getElementsByTagName("tr");
            for (i = 0; i < tr.length; i++) {
                if (field == "id"){
                    td = tr[i].getElementsByTagName("td")[0];
                } else if (field == "name") {
                    td = tr[i].getElementsByTagName("td")[1];
                } else if (field == "surname") {
                    td = tr[i].getElementsByTagName("td")[2];
                } else if (field == "birthDate") {
                    td = tr[i].getElementsByTagName("td")[3];
                } else if (field == "fiscalCode") {
                    td = tr[i].getElementsByTagName("td")[4];
                } else if (field == "username") {
                    td = tr[i].getElementsByTagName("td")[5];
                }
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
</body>
</html>
