<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>

<jsp:include page="../header.jsp"/>
<div class="content">
    <div class="container">
        <div class="col-md-3 praesent">
            <div class="l_g_r">
                <div class="dapibus biography-into center">
                    <a href="<c:url value='/users/${job.user.id}'/>">
                        <img class="user-placeholder" src="${job.user.gravatar}">
                        <h4>${job.user.firstName} ${job.user.lastName}</h4>
                        <p class="center">${job.user.email}</p></a>
                        <c:if test="${job.user.id == loggedUser.id}">
                            <h3>
                                <span class="label label-info">${quantityApplications}
                                    <spring:message code="Applications"/></span>
                            </h3>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-md-9 praesent">
                <div class="l_g_r b-margin">
                    <div class="dapibus biography-into">
                      <div>
                          <c:if test="${job.user.id == loggedUser.id}">
                            <div data-toggle="tooltip" data-placement="top" title="<spring:message code="Edit"/>">
                              <span class="glyphicon glyphicon-pencil"/>
                            </div>
                            <div data-toggle="tooltip" data-placement="top" title="<spring:message code="Delete"/>">
                              <span class="glyphicon glyphicon-remove"/>
                            </div>
                          </c:if>
                      </div>
                        <h2 class="media-heading">${job.title}
                        </h2>
                        <p>${job.description}</p>
                        <h3>
                            <c:forEach items="${job.skills}" var="skill">
                                <a class="no-underline" href="<c:url value='/job_offers?skill_id=${skill.id}'/>"><span class="label label-info">${skill.name}</span></a>
                            </c:forEach>
                        </h3>
                    </div>
                </div>
                <c:if test="${job.user.id == loggedUser.id}">
                    <div class="l_g_r">
                        <div class="dapibus biography-into">
                            <h2 class="media-heading"><spring:message code="Applications"/></h2>
                            <c:forEach items="${applications}" var="application">
                                <div class="media">
                                    <div class="media-left">
                                        <a href="<c:url value='/users/${application.user.id}'/>">
                                          <img class="user-placeholder" src="${application.user.gravatar}">
                                        </a>
                                        </div>
                                        <div class="media-body">
                                            <h3 class="media-heading">${application.user.firstName} ${application.user.lastName}</h3>
                                            <h4 class="media-heading">${application.user.email}</h4>
                                            <c:forEach items="${application.user.skills}" var="skill">
                                                <span class="label label-info">${skill.name}</span>
                                            </c:forEach>
                                        </div>
                                        <hr></div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${job.user.id != loggedUser.id}">
                            <div class="l_g_r">
                                <div class="dapibus biography-into">
                                    <h4><spring:message code="ApplyNow"/></h4>
                                    <c:choose>
                                        <c:when test="${job.closedAt != null}">
                                          <p><spring:message code="JobOfferFinished"/></p>
                                        </c:when>
                                        <c:when test="${alreadyApplied == true}">
                                          <p><spring:message code="AlreadyApplied"/></p>
                                        </c:when>
                                        <c:otherwise>
                                            <spring:url value="/job_offers/${job.id}/apply" var="actionUrl"/>
                                          <form:form method="post" action="${actionUrl}">
                														<button class="btn btn-info" type="submit">
                															<spring:message code="Apply"/>
                														</button>
                													</form:form>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <jsp:include page="../footer.jsp"/>
            <script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>
