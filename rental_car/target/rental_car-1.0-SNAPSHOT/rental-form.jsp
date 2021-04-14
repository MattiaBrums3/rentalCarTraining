<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <c:if test="${rental != null}">
        <title>Modifica Prenotazione</title>
    </c:if>
    <c:if test="${rental == null}">
        <title>Nuova Prenotazione</title>
    </c:if>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/css-form.css" type="text/css" />
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div align="center">
        <div class="register">
            <div class="row">
                <div class="col-sm register-right">
                    <c:if test="${rental != null}">
                        <form action="updateRental" method="post">
                    </c:if>
                    <c:if test="${rental == null}">
                        <form action="insertRental" method="post">
                    </c:if>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <c:if test="${rental != null}">
                                <h3 class="register-heading">Modifica Prenotazione</h3>
                            </c:if>
                            <c:if test="${rental == null}">
                                <h3 class="register-heading">Nuova Prenotazione</h3>
                            </c:if>
                            <c:if test="${rental != null}">
                                <input type="hidden" name="id" value="<c:out value='${rental.id}' />" />
                            </c:if>
                            <div class="row register-form">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <select name="vehicle" class="form-control" title="Veicolo">
                                            <c:if test="${rental == null}">
                                                <option selected>VEICOLO *</option>
                                                <c:forEach var="vehicle" items="${listVehicles}">
                                                    <option value="${vehicle.id}">${vehicle.model}</option>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${rental != null}">
                                                <c:forEach var="vehicle" items="${listVehicles}">
                                                    <c:if test="${vehicle.id == rental.vehicle.id}">
                                                        <option selected value="${vehicle.id}">${vehicle.model}</option>
                                                    </c:if>
                                                    <c:if test="${vehicle.id != rental.vehicle.id}">
                                                        <option value="${vehicle.id}">${vehicle.model}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" name="date_of_end" class="form-control" value="<fmt:formatDate value="${rental.dateOfEnd}" pattern="yyyy-MM-dd" />" placeholder="DATA DI FINE *" onfocus="(this.type='date')" title="Data di Fine" required />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="date_of_start" class="form-control" value="<fmt:formatDate value="${rental.dateOfStart}" pattern="yyyy-MM-dd" />" placeholder="DATA DI INIZIO *" onfocus="(this.type='date')" title="Data di Inizio" required />
                                    </div>
                                    <c:if test="${rental != null}">
                                        <input type="submit" value="Modifica" class="btnRegister" />
                                    </c:if>
                                    <c:if test="${rental == null}">
                                        <input type="submit" value="Inserisci" class="btnRegister" />
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
