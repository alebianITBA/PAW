<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="header.jsp" />

<div class="container-fluid">

  <div class="row register-background">
    <div class="col-md-4 col-md-offset-4" >

      <div class="register-panel">
        <h3 class="form-title text-center white-text">
			<spring:message code="WelcomeLogIn" />
		</h3>
        <form class="form-header" method="post" enctype="application/x-www-form-urlencoded">
            <div class="form-group">
               <input type="text" name="j_email" class="form-control" placeholder="Email" />
            </div>
            <div class="form-group">
               <input type="password" name="j_password" class="form-control" placeholder="Password" />
            </div>
            <spring:message code="LogIn" var="LogIn"/>
            <input type="submit" class="btn btn-info btn-block btn-lg" value="${LogIn}"></input>
         </form>
      </div>

    </div>
  </div>

</div>

<jsp:include page="footer.jsp" />