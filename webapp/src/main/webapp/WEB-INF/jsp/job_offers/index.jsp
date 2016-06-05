<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<jsp:include page="../header.jsp"/>
<div class="content">
	<div class="container">
		<c:if test="${skills[0] != null}">
			<div class="col-md-8 praesent">
				<div class="l_g_r biography-into">
					<div class="dapibus">
						<c:forEach items="${job_offers}" var="offer">
							<div class="media">
								<div class="media-left">
									<a href="<c:url value='/users/${offer.userId}'/>">
										<img class="user-placeholder" src="${offer.user.gravatar}"></a>
									</div>
									<div class="media-body">
										<h4>
											<a href="<c:url value='/job_offers/${offer.id}'/>">${offer.title}</a>
										</h4>
										<p>${offer.description}</p>
										<c:forEach items="${offer.skills}" var="skill">
											<a class="no-underline" href="<c:url value='/job_offers?skill_id=${skill.id}'/>"><span class="label label-info">${skill.name}</span></a>
										</c:forEach>
									</div>

									<div>
										<div class="apply-button">
											<c:choose>
												<c:when test="${offer.status == 'OFFER_OWNER'}">
													<a href="<c:url value='/job_offers/${offer.id}/edit'/>">
														<button class="btn btn-primary" type="submit">
															<spring:message code="Edit"/>
														</button>
													</a>
												</c:when>
												<c:when test="${offer.status == 'READY_TO_APPLY'}">
													<spring:url value="/job_offers/${offer.id}/apply" var="actionUrl"/>
													<form:form method="post" action="${actionUrl}">
														<button class="btn btn-info" type="submit">
															<spring:message code="Apply"/>
														</button>
													</form:form>
												</c:when>
												<c:otherwise>
													<span data-toggle="tooltip" data-placement="top" title="<spring:message code="AlreadyApplied" />">
														<button class="btn btn-info disabled" type="submit">
															<spring:message code="Apply"/>
														</button>
													</span>
												</c:otherwise>
											</c:choose>
										</div>
									</div>

								</div>
								<hr>
								</c:forEach>


							</div>
								<div class="l_g_r biography-into center">
										<div class="row">
											<div id="pagination" data-value="${item_count}"></div>
										</div>
								</div>
						</div>
					</div>
				</c:if>
				<div class="col-md-4 praesent">
					<div class="l_g_r b-margin">
						<div class="dapibus biography-into">
							<div class="input-group b-margin">

								<select name="skills-filter" id="skills-select-filter" class="form-control input-lg">
									<c:forEach items="${skills}" var="skill">
										<option value="${skill.id}">${skill.name}</option>
									</c:forEach>
								</select>
							</div>
							<button id="filter-offers" class="btn btn-info btn-block btn-lg form-control ">
								<spring:message code="Filter"/>
							</button>
						</div>
					</div>

					<div class="l_g_r">
						<div class="dapibus biography-into">
							<h4>
								<spring:message code="NeedToHire"/>
							</h4>
							<spring:url value="job_offers" var="offerUrl"/>
							<form:form method="post" modelAttribute="jobOfferForm" action="${offerUrl}" class="form-header" role="form" id="#">
								<div class="form-group">
									<spring:message code="JobOfferTitle" var="jobOfferTitle"/>
									<form:input type="text" class="form-control input-lg" placeholder="${jobOfferTitle}*" path="title"/>
									<c:set var="titleErrors">
										<form:errors path="title"/>
									</c:set>
									<c:if test="${not empty titleErrors}">
										<tr>
											<td>${titleErrors}</td>
										</tr>
									</c:if>
								</div>
								<div class="form-group">
									<spring:message code="Content" var="content"/>
									<form:textarea class="form-control input-lg post-textarea" placeholder="${content}*" path="description"/>
									<c:set var="descriptionErrors">
										<form:errors path="description"/>
									</c:set>
									<c:if test="${not empty descriptionErrors}">
										<tr>
											<td>${descriptionErrors}</td>
										</tr>
									</c:if>
								</div>

								<div class="form-group">
									<spring:message code="ChooseSkills" var="ChooseSkills"/>
									<form:select id="skills-select" path="selectedSkillIds" itemValue="id" multiple="true" items="${skills}" itemLabel="name" data-placeholder="${ChooseSkills}" class="form-control"/>
								</div>

								<spring:message code="Submit" var="submit"/>
								<input type="submit" class="btn btn-info btn-block btn-lg" value="${submit}"/>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../footer.jsp"/>

		<script type="text/javascript" src="<c:url value='/script/job_offers.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/script/chosen/chosen.jquery.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/script/pagination.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>
		<link rel="stylesheet" href="<c:url value='/style/chosen.css'/>">
			<script type="text/javascript">
				$('#skills-select').chosen({no_results_text: "Oops, no skills found!", max_selected_options: 5});
			</script>
