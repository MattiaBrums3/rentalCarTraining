<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
</head>
<body>
    <div align="center" style="margin-left:250px; margin-right:250px;">
        <h2>Lista Utenti</h2>
        <div class="row d-flex justify-content-center">
            <div class="col-lg-2 col-md-3 col-sm-12 p-0">
                <select id="searchField" name="searchField" class="form-control search-slt">
                    <option value="id" selected>idUtente</option>
                    <option value="name">Nome</option>
                    <option value="surname">Cognome</option>
                    <option value="birthDate">Data di Nascita</option>
                    <option value="fiscalCode">Codice Fiscale</option>
                    <option value="username">Username</option>
                </select>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                <input type="text" id="searchUsers" class="form-control search-slt" onkeyup="searchFunction()" placeholder="Ricerca..." />
            </div>
        </div>
        <table id="usersTable" class="table table-striped" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th class="th-sm">idUtente</th>
                    <th class="th-sm">Nome</th>
                    <th class="th-sm">Cognome</th>
                    <th class="th-sm">Data di Nascita</th>
                    <th class="th-sm">Codice Fiscale</th>
                    <th class="th-sm">Username</th>
                    <th class="th-sm"><a href="newUser">Nuovo Utente</a></th>
                </tr>
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
        };
    </script>
    <script>
        $(document).ready(function () {
            $('#usersTable').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Italian.json"
                },
                "searching": false
            });
            $('.dataTables_length').addClass('bs-select');
        });
    </script>
    <script>
        const choices = new Choices('[data-trigger]',
            {
                searchEnabled: false
            });
    </script>
</body>
</html>
