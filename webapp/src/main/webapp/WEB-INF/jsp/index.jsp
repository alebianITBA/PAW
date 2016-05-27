<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<jsp:include page="./header.jsp"/>

<div class="content">
	<div class="container">
		<c:if test="${not empty offers}">
			<div class="col-md-4 praesent f-right">
				<c:forEach items="${offers}" var="offer">
						<div class="l_g_r">
							<div class="dapibus">
								<h2>
									<a href="<c:url value='/job_offers/${offer.id}'/>">${offer.title}</a>
								</h2>
								<p class="adm">
									<spring:message code="PostedBy"/>
									<a href="<c:url value='/users/${offer.user.id}'/>">${offer.user.firstName} ${offer.user.lastName}</a>
								</p>
								<p>${offer.description}</p>
								<p class="skill-container">
									<c:forEach items="${offer.skills}" var="skill">
										<a class="no-underline" href="<c:url value='/job_offers?skill_id=${skill.id}'/>"><span class="label label-info">${skill.name}</span></a>
									</c:forEach>
								</p>
								<spring:url value="/job_offers/${offer.id}/apply" var="actionUrl"/>
								<form:form method="post" action="${actionUrl}">
									<button class="btn btn-info btn-block btn-lg" type="submit">
										<spring:message code="Apply"/>
									</button>
								</form:form>
							</div>
						</div>
				</c:forEach>
			</div>
		</c:if>

		<div class="col-md-8 praesent f-left">
			<div class="l_g_r biography-into">
				<div class="dapibus">
					<h4>
						<spring:message code="SharePost"/>
					</h4>
					<spring:url value="/posts/" var="postUrl"/>
					<form:form method="post" modelAttribute="postForm" action="${postUrl}" class="form-header" role="form" id="#">
						<div class="form-group">
							<spring:message code="Title" var="title"/>
							<form:input type="text" class="form-control input-lg" placeholder="${title}*" path="title"/>
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
						<spring:message code="Submit" var="submit"/>
						<input type="submit" class="btn btn-info btn-block btn-lg" value="${submit}"/>
					</form:form>
					<c:choose>
						<c:when test="${fn:length(posts) gt 0}">
							<c:forEach items="${posts}" var="post">
								<hr>
									<h4>${post.title}</h4>
									<p class="adm">
										<spring:message code="PostedBy"/>
										<a href="<c:url value='/users/${post.user.id}'/>">${post.user.firstName} ${post.user.lastName}</a>
									</p>
									<p>${post.description}</p>
								</hr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<hr>
								<p style="text-align: center;">
									<b>
										<spring:message code="NoPost"/><br/>
										<spring:message code="BeTheFirstOneToPost"/></b>
								</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="./footer.jsp"/>
<script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>
