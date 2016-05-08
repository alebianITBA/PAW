<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="./header.jsp" />

<div class="container-fluid">
  <div class="row">
    <div class="col-md-4 col-md-offset-4">

      <spring:url value="/create" var="actionUrl" />
      <form:form method="post" modelAttribute="registerForm" action="${actionUrl}">
        <form class="form-horizontal">
          <div class="input-group">
            <form:input type="text" class="form-control" required="" placeholder="Email*" path="email" id="email" />
            <form:errors path="email" />
          </div>
          <div class="input-group">
            <form:input type="text" class="form-control" required="" placeholder="First name*" path="firstName" id="firstName" />
            <form:errors path="firstName"/>
          </div>
          <div class="input-group">
            <form:input type="text" class="form-control" required="" placeholder="Last name*" path="lastName" id="lastName" />
            <form:errors path="lastName"/>
          </div>
          <div class="input-group">
            <form:input type="password" class="form-control" required="" placeholder="Password*" path="password" id="password" />
            <form:errors path="password" />
          </div>
          <div class="input-group">
            <form:input type="password" class="form-control" required="" placeholder="Confirm password*" path="confirmPassword" id="confirmPassword" />
            <form:errors path="confirmPassword" />
          </div>
          <input type="submit" class="btn btn-primary" value="Register">
        </form>
      </form:form>

    </div>
  </div>
</div>

<jsp:include page="./footer.jsp" />
<script type="text/javascript" src="/script/strength.js"></script>
<link rel="stylesheet" href="/style/strength.css">
<script type="text/javascript">$("#password").strength();</script>
