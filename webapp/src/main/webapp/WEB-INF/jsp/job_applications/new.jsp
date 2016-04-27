<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />

<div id="content-block" class="gallery-block">
	<div class="container custom-container be-detail-container">
		<div class="row">
			<spring:url value="/job_applications/create" var="actionUrl" />
			<form:form method="post" modelAttribute="jobApplication"
				action="${actionUrl}">

				<div class="input-col col-xs-12 col-sm-12">
					<div class="form-group fg_icon focus-2">
						<div class="form-label">User ID</div>
						<form:input path="userId" class="form-input" required="true"
							placeholder="User ID" />
					</div>
				</div>

				<div class="input-col col-xs-12 col-sm-12">
					<div class="form-group focus-2">
						<div class="form-label">Description</div>
						<form:textarea path="description" class="form-input"
							required="true" placeholder="Job Application description..." />
					</div>
				</div>

				<form:hidden path="jobOfferId" value="${jobOfferId}" />
				<br>
				<br>
				<div class="row">
					<input type="submit"
						class="btn full btn-input color-1 size-4 hover-1" value="APPLY">
				</div>
			</form:form>
		</div>
	</div>
</div>

<jsp:include page="../footer.jsp" />
