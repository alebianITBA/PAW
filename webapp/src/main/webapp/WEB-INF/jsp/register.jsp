<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="header.jsp"/>

<div class="container-fluid">

    <div class="row register-background">
        <div class="col-md-4 col-md-offset-4">

            <div class="register-panel">
                <h3 class="form-title text-center white-text"><spring:message code="GetStarted"/></h3>
                <spring:url value="/create_user" var="actionUrl"/>
                <form:form method="post" modelAttribute="registerForm" action="${actionUrl}" class="form-header">
                    <spring:message code="FirstName" var="FirstName"/>
                    <spring:message code="LastName" var="LastName"/>
                    <spring:message code="Mail" var="Mail"/>
                    <spring:message code="Password" var="Password"/>
                    <spring:message code="ConfirmPassword" var="ConfirmPassword"/>
                    <spring:message code="Register" var="Register"/>

                    <div class="form-group">
                        <form:input type="text" class="form-control input-lg" placeholder="${FirstName}*" path="firstName" id="firstName"/>
                        <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
                        <c:if test="${not empty firstNameErrors}">
                            <tr>
                                <td>${firstNameErrors}</td>
                            </tr>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <form:input type="text" class="form-control input-lg" placeholder="${LastName}*" path="lastName" id="lastName"/>
                        <c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
                        <c:if test="${not empty lastNameErrors}">
                            <tr>
                                <td>${lastNameErrors}</td>
                            </tr>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <form:input type="text" class="form-control input-lg" placeholder="${Mail}*" path="email" id="email"/>
                        <c:set var="emailErrors"><form:errors path="email"/></c:set>
                        <c:if test="${not empty emailErrors}">
                            <tr>
                                <td>${emailErrors}</td>
                            </tr>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <form:input type="password" class="form-control input-lg" placeholder="${Password}*" path="password" id="password"/>
                        <c:set var="passwordErrors"><form:errors path="password"/></c:set>
                        <c:if test="${not empty passwordErrors}">
                            <tr>
                                <td>${passwordErrors}</td>
                            </tr>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <form:input type="password" class="form-control input-lg" placeholder="${ConfirmPassword}*" path="confirmPassword" id="confirmPassword"/>
                        <c:set var="confirmPasswordErrors"><form:errors path="confirmPassword"/></c:set>
                        <c:if test="${not empty confirmPasswordErrors}">
                            <tr>
                                <td>${confirmPasswordErrors}</td>
                            </tr>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-info btn-block btn-lg" value="${Register}"></div>
                        <p class="privacy text-left white-text"><spring:message code="RequiredFields"/></a>.</p>
                </form:form>
            </div>

        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>
