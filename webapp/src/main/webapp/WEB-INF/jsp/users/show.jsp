<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="java.util.*" %>

<jsp:include page="../header.jsp"/>

<div class="content">
    <div class="container">
      <div class="row">
        <div class="col-md-12 praesent">
            <div class="l_g_r biography-into">
                <div class="dapibus center">
                    <img class="user-image" src="${user.gravatar}">
                        <div >
                            <h1>${user.firstName} ${user.lastName} <span class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="<spring:message code="Edit"/>"/></h1>
                        </div>
                        <div>
                            <h3>${user.email}</h3>
                        </div>
                        <div>
                            <c:forEach items="${user.skills}" var="skill">
                              <a class="no-underline" href="<c:url value='/job_offers?skill_id=${skill.id}'/>"><span class="label label-info">${skill.name}</span></a>
                            </c:forEach>
                            <span class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="<spring:message code="Edit"/>"/>
                        </div>
                    </div>
                </div>
            </div>
          </div>

          <div class="row">
            <c:if test="${not empty posts}">
                <div class="col-md-6 praesent">
                    <div class="l_g_r biography-into">
                        <div class="dapibus">
                            <h3><spring:message code="RecentPosts"/></h3>
                            <c:forEach items="${posts}" var="post">
                                <hr>
                                    <h4>${post.title}</h4>
                                    <p>${post.description}</p>
                                    <div>
                                      <div class="apply-button">
                                        <c:if test="${post.user.id == loggedUser.id}">
                                          <div data-toggle="tooltip" data-placement="top" title="<spring:message code="Edit"/>">
                                            <span class="glyphicon glyphicon-pencil"/>
                                          </div>
                                          <div data-toggle="tooltip" data-placement="top" title="<spring:message code="Delete"/>">
                                            <span class="glyphicon glyphicon-remove remove-button" data-href="<c:url value='/posts/${post.id}'/>"/>
                                          </div>
                                        </c:if>
                                      </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty offers}">
                    <div class="col-md-6 praesent">
                        <div class="l_g_r biography-into">
                            <div class="dapibus">
                                <h3><spring:message code="JobOffersCreated"/></h3>
                                <c:forEach items="${offers}" var="offer">
                                    <hr>
                                        <div class="media">
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
                                                <c:if test="${offer.user.id == loggedUser.id}">
                                                  <div data-toggle="tooltip" data-placement="top" title="<spring:message code="Edit"/>">
                                                    <span class="glyphicon glyphicon-pencil"/>
                                                  </div>
                                                  <div data-toggle="tooltip" data-placement="top" title="<spring:message code="Delete"/>">
                                                    <span class="glyphicon glyphicon-remove remove-button" data-href="<c:url value='/job_offers/${offer.id}'/>"/>
                                                  </div>
                                                  <div data-toggle="tooltip" data-placement="top" title="<spring:message code="Finish"/>">
                                                    <span class="glyphicon glyphicon-briefcase"/>
                                                  </div>
                                                </c:if>
                                              </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                </c:if>
              </div>

              <div class="row">
                <c:if test="${not empty offersApplied}">
                    <div class="col-md-6 praesent">
                        <div class="l_g_r biography-into">
                            <div class="dapibus">
                                <h3><spring:message code="JobOffersApplied"/></h3>
                                <c:forEach items="${offersApplied}" var="applied">
                                    <hr>
                                        <div class="media">
                                            <div class="media-body">
                                                <h4>
                                                    <a href="<c:url value='/job_offers/${applied.jobOffer.id}'/>">${applied.jobOffer.title}</a>
                                                </h4>
                                                <p>${applied.description}</p>
                                            </div>
                                            <div>
                                              <div class="apply-button">
                                                  <div data-toggle="tooltip" data-placement="top" title="<spring:message code="Unapply"/>">
                                                    <span class="glyphicon glyphicon-remove remove-button" data-href="<c:url value='/job_application/${applied.id}'/>"/>
                                                  </div>
                                              </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                </c:if>
              </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
<script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>
