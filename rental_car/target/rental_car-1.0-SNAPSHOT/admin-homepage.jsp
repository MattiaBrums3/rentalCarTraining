<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>Admin Homepage</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="register">
        <div class="row">
            <div class="col-sm register-right">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <h1 class="register-heading">Admin Homepage</h1>
                        <div class="row register-form d-flex justify-content-center">
                            <div class="col-md-6">
                                    <a href="category"><input type="button" class="btnAdminMenu" value="Categorie" /></a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="vehicle"><input type="button" class="btnAdminMenu" value="Veicoli" /></a>
                            </div>
                        </div>
                        <jsp:include page="user-list.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
    var msg = "${sessionScope.msg}";
    if (msg != "") {
        alert(msg);
    }
</script>
<c:remove var="msg" />
</html>
