<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:if test="${category != null}">
        <title>Modifica Categoria</title>
    </c:if>
    <c:if test="${category == null}">
        <title>Nuova Categoria</title>
    </c:if>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="center">
    <c:if test="${category != null}">
        <form action="updateCategory" method="post">
    </c:if>
    <c:if test="${category == null}">
        <form action="insertCategory" method="post">
    </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${category != null}">
                            Modifica Categoria
                        </c:if>
                        <c:if test="${category == null}">
                            Nuova Categoria
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${category != null}">
                    <input type="hidden" name="id" value="<c:out value='${category.id}' />" />
                </c:if>
                <tr>
                    <th>Tipologia: </th>
                    <td>
                        <input type="text" name="typology" value="<c:out value='${category.typology}' />" required />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <c:if test="${category != null}">
                            <input type="submit" value="Modifica" />
                        </c:if>
                        <c:if test="${category == null}">
                            <input type="submit" value="Inserisci" />
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>