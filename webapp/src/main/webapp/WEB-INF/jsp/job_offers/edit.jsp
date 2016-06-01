<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<jsp:include page="../header.jsp" />
<div class="content">
	<div class="container">
		<div class="col-md-3 praesent">
			<div class="l_g_r">
				<div class="dapibus biography-into center">
					<a href="<c:url value='/users/${job.user.id}'/>"> <img
						class="user-placeholder" src="${job.user.gravatar}">
						<h4>${job.user.firstName}${job.user.lastName}</h4>
						<p class="center">${job.user.email}</p></a>
					<c:if test="${job.user.id == loggedUser.id}">
						<h3>
							<span class="label label-info">${quantityApplications} <spring:message
									code="Applications" /></span>
						</h3>
					</c:if>
				</div>
			</div>
		</div>
		<div class="col-md-9 praesent">
			<div class="l_g_r b-margin">
				<div class="dapibus biography-into">

					<spring:url value="/job_offers" var="offerUrl" />
					<form:form method="post" modelAttribute="jobOfferForm"
						action="${offerUrl}" class="form-header" role="form" id="#">
						
						<form:input type="hidden" class="form-control input-lg"
								value="${jobOfferForm.id}" path="id" />
						
						<div class="form-group">
							<form:input type="text" class="form-control input-lg"
								value="${jobOfferForm.title}" path="title" />
							<c:set var="titleErrors">
								<form:errors path="title" />
							</c:set>
							<c:if test="${not empty titleErrors}">
								<tr>
									<td>${titleErrors}</td>
								</tr>
							</c:if>
						</div>
						<div class="form-group">
							<spring:message code="Content" var="content" />
							<form:textarea class="form-control input-lg post-textarea"
								value="${jobOfferForm.description}*" path="description" />
							<c:set var="descriptionErrors">
								<form:errors path="description" />
							</c:set>
							<c:if test="${not empty descriptionErrors}">
								<tr>
									<td>${descriptionErrors}</td>
								</tr>
							</c:if>
						</div>

						<div class="form-group">
							<spring:message code="ChooseSkills" var="ChooseSkills" />
							<form:select id="skills-select" path="selectedSkillIds"
								itemValue="id" multiple="true" items="${skills}"
								itemLabel="name" data-placeholder="${ChooseSkills}"
								class="form-control" />
						</div>

						<spring:message code="Modify" var="modify" />
						<input type="submit" class="btn btn-info btn-block btn-lg"
							value="${modify}" />
					</form:form>
				</div>
			</div>
			<c:if test="${job.user.id == loggedUser.id}">
				<div class="l_g_r">
					<div class="dapibus biography-into">
						<h2 class="media-heading">
							<spring:message code="Applications" />
						</h2>

						<c:choose>
							<c:when test="${empty applications}">
								<p>
									<spring:message code="NoApplicants" />
								</p>
							</c:when>
							<c:otherwise>
								<c:forEach items="${applications}" var="application">
									<div class="media">
										<div class="media-left">
											<a href="<c:url value='/users/${application.user.id}'/>">
												<img class="user-placeholder"
												src="${application.user.gravatar}">
											</a>
										</div>
										<div class="media-body">
											<h3 class="media-heading">${application.user.firstName}
												${application.user.lastName}</h3>
											<h4 class="media-heading">${application.user.email}</h4>
											<c:forEach items="${application.user.skills}" var="skill">
												<span class="label label-info">${skill.name}</span>
											</c:forEach>
										</div>
										<hr>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp" />
<script type="text/javascript"
	src="<c:url value='/script/chosen/chosen.jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/style/chosen.css'/>">
<script type="text/javascript">
	$('#skills-select').chosen({
		no_results_text : "Oops, no skills found!",
		max_selected_options : 5
	});

	function setSelected(item, index) {
		if (item != "") {
			document.getElementById("skills-select").options[index].selected = true;	
		}
	}

	var selectedSkills = "${jobOfferForm.selectedSkillIds}";
	if (selectedSkills != "") {
		var skillsArr = selectedSkills.split(",");
		skillsArr.forEach(setSelected);
		$('#skills-select').trigger("chosen:updated");	
	}
	
</script>
