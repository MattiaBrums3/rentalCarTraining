<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div align="center">
        <h1>Admin Homepage</h1>
        <h3>
            <a href="category">Categorie</a>
            &nbsp;&nbsp;&nbsp;
            <a href="vehicle">Veicoli</a>

        </h3>
    </div>

    <jsp:include page="user-list.jsp"/>

</body>
</html>
