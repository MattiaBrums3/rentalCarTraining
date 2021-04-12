<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>Admin Homepage</title>
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
<script type="text/javascript">
    var msg = "${sessionScope.msg}";
    if (msg != "") {
        alert(msg);
    }
</script>
<c:if test="${not empty sessionScope.msg}">
    <c:remove var="msg" scope="session" />
</c:if>
</html>
