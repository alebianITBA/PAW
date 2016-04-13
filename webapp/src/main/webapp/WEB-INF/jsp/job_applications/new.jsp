<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />

<div id="content-block" class="gallery-block">
  <div class="container custom-container be-detail-container">
    <div class="row">
    <spring:url value="/job_applications/create" var="actionUrl" />
	<form:form method="post" modelAttribute="jobApplication" action="${actionUrl}">
        User ID:<br>
        <form:input type="text" path="userId" /><br>
        Description:<br>
        <form:input type="text" path="description"/><br><br>
        <form:hidden path="jobOfferId" value="${jobOfferId}"/><br><br>
        <input type="submit" value="Submit">
      </form:form>
    </div>
  </div>
</div>

<jsp:include page="../footer.jsp" />
