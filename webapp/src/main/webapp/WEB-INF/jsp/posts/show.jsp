<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<jsp:include page="../header.jsp" />

<div class="content">
	<div class="container">
		<div class="col-md-12 praesent f-left">
			<div class="l_g_r b-margin">
				<div class="dapibus biography-into">
					<h4>${post.title}</h4>
					<p class="adm">
						<spring:message code="PostedBy" />
						<a href="<c:url value='/users/${post.user.id}'/>">${post.user.firstName}
							${post.user.lastName}</a>
					</p>
					<p>${post.description}</p>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../footer.jsp" />
<script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>