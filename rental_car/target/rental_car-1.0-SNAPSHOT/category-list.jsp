<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <jsp:include page="header.jsp"/>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista Categorie</h2></caption>
            <tr>
                <th>idCategoria</th>
                <th>Tipologia</th>
                <th><a href="newCategory">Nuova Categoria</a></th>
            </tr>
            <c:forEach var="category" items="${listCategories}">
                <tr>
                    <td><c:out value="${category.id}" /></td>
                    <td><c:out value="${category.typology}" /></td>
                    <td>
                        <a href="editCategory?id=<c:out value='${category.id}' />">Modifica</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteCategory?id=<c:out value='${category.id}' />">Elimina</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
