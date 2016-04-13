<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />
<div class="container-fluid custom-container be-detail-container">
	<spring:url value="/users/post" var="actionUrl" />

	<form:form method="post" modelAttribute="user" action="${actionUrl}">
		<div class="row" style="width: 75%; padding: 100px; margin: auto;">
			<form class="form-horizontal">
				<div class="form-group" style="padding-bottom: 20px;">
					<label for="firstName" class="col-sm-2 control-label">First
						Name</label>
					<div class="col-sm-10">
						<form:input type="text" class="form-control" required=""
							placeholder="First Name" path="firstName" id="firstName" />
					</div>
				</div>
				<div class="form-group" style="padding-bottom: 20px;">
					<label for="lastName" class="col-sm-2 control-label">Last
						Name</label>
					<div class="col-sm-10">
						<form:input type="text" class="form-control" required=""
							placeholder="Last Name" path="lastName" id="lastName" />
					</div>
				</div>
				<div class="form-group" style="padding-bottom: 20px;">
					<label for="email" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<form:input type="text" class="form-control" required=""
							placeholder="Email" path="email" id="email" />
					</div>
				</div>
				<div class="form-group" style="padding-bottom: 20px;">
					<label for="password" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<form:input type="text" class="form-control" required=""
							placeholder="Password" path="password" id="password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" style="margin-top: 30px;"
							class="be-popup-sign-button" value="SIGN IN">
					</div>
				</div>
			</form>
		</div>
	</form:form>

</div>




<jsp:include page="../footer.jsp" />