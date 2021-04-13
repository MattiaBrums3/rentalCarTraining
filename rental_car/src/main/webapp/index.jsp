<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>Rental Car</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/css-login.css" />
</head>
<body>
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <h2 class="active"> ACCEDI A RENTAL CAR </h2>

            <form action="login" method="post">
                <input type="text" id="login" class="fadeIn second" name="username" placeholder="Username" required>
                <input type="password" id="login" class="fadeIn third" name="password" placeholder="Password" required>
                <input type="submit" class="fadeIn fourth" value="ACCEDI">
            </form>
        </div>
    </div>

    <script type="text/javascript">
        var msg = "${sessionScope.msg}";
        if (msg != "") {
            alert(msg);
        }
    </script>
    <c:remove var="msg" />
</body>
</html>