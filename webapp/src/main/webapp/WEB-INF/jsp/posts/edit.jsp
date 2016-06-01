<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<jsp:include page="../header.jsp"/>

<div class="content">
	<div class="container">
		<div class="col-md-12 praesent f-left">
			<div class="l_g_r biography-into">
				<div class="dapibus">
					<h4>
						<spring:message code="SharePost"/>
					</h4>
					<spring:url value="/posts" var="postUrl"/>
					<form:form method="post" modelAttribute="postForm" action="${postUrl}" class="form-header" role="form">
						<form:input type="hidden" path="id"/>
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
					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../footer.jsp"/>
<script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>