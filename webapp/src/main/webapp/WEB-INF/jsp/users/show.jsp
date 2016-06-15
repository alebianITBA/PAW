<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<jsp:include page="../header.jsp" />
<div class="content">
   <div class="container">
      <div class="row">
         <div class="col-md-4 praesent">
            <div class="l_g_r biography-into">
               <div class="dapibus center">
                  <img class="user-image" src="${user.gravatar}">
                  <div id="info-user" style="display: block;">
                     <div >
                        <h2 class="center">${user.firstName} ${user.lastName}  </h2>
                     </div>
                     <div>
                        <h3>${user.email}</h3>
                     </div>
                     <div>
                        <c:forEach items="${user.skills}" var="skill">
                           <a class="no-underline"
                              href="<c:url value='/job_offers?skill_id=${skill.id}'/>"><span
                              class="label label-info">${skill.name}</span></a>
                        </c:forEach>
                     </div>
                     <c:if test="${user.id == loggedUser.id}">
	                     <button type="button" class="btn btn-xs btn-info top-right-edit-button edit-button">
	                        <spring:message code="Edit"/>
	                     </button>
                     </c:if>
                  </div>
                  <div id="editing-info-user" style="display: none;">
                     <spring:url value="/users/${user.id}" var="userUrl" />
                     <form:form method="post" modelAttribute="userForm" class="form-header"
                        action="${userUrl}" role="form" style="width:100%; margin-top:20px;">
                        <form:input type="hidden"
                           value="${userForm.id}" path="id" />
                        <div class="form-group">
                           <form:input type="text" class="form-control input-lg"
                              value="${userForm.firstName}" path="firstName" />
                        </div>
                        <div class="form-group">
                           <form:input type="text" class="form-control input-lg"
                              value="${userForm.lastName}" path="lastName" />
                        </div>
                        <div>
                           <h3>${user.email}</h3>
                        </div>
                        <div class="form-group">
                           <form:select id="skills-select"
                              class="form-control"
                              path="selectedSkillIds"
                              itemValue="id" multiple="true" items="${skills}"
                              itemLabel="name" data-placeholder="${ChooseSkills}"
                              />
                        </div>
                        <spring:message code="Modify" var="modify" />
                        <input type="submit" class="btn btn-info btn-lg"
                           value="${modify}" />
                        <button type="button" class="btn btn-lg btn-info cancel-edit-button">
                           <spring:message code="Cancel"/>
                        </button>
                     </form:form>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-md-8 praesent">
            <c:if test="${not empty posts}">
               <div class="biography-into praesent">
                  <div class="dapibus">
                     <h3>
                        <spring:message code="RecentPosts" />
                     </h3>
                     <c:forEach items="${posts}" var="post">
                        <hr>
                        <div class="media">
                           <div class="media-body">
                              <h4>
                                 <a class="no-underline"
                                    href="<c:url value='/posts/${post.id}'/>">${post.title}</a>
                              </h4>
                              <p>${post.description}</p>
                           </div>
                        </div>
                        <div>
                           <div class="apply-button">
                              <c:if test="${post.user.id == loggedUser.id}">
                                 <div class="btn-group">
                                    <button type="button"
                                       class="btn btn-info btn-xs dropdown-toggle"
                                       data-toggle="dropdown" aria-haspopup="true"
                                       aria-expanded="false">
                                       <spring:message code="Action" />
                                       <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right">
                                       <li>
                                          <a href="<c:url value='/posts/${post.id}/edit'/>">
                                             <spring:message code="Edit" />
                                          </a>
                                       </li>
                                       <li>
                                          <a href="" class="remove-button"
                                             data-href="<c:url value='/posts/${post.id}'/>">
                                             <spring:message
                                                code="Delete" />
                                          </a>
                                       </li>
                                    </ul>
                                 </div>
                              </c:if>
                           </div>
                        </div>
                     </c:forEach>
                  </div>
               </div>
            </c:if>
            <c:if test="${not empty offers}">
               <div class="biography-into praesent">
                  <div class="dapibus">
                     <h3>
                        <spring:message code="JobOffersCreated" />
                     </h3>
                     <c:forEach items="${offers}" var="offer">
                        <hr>
                        <div class="media">
                           <div class="media-body">
                              <h4>
                                 <c:choose>
                                    <c:when test="${offer.closedAt != null}">
                                       <span data-toggle="tooltip" data-placement="top" title="<spring:message code="OfferClosed" />
                                          <fmt:formatDate pattern="dd/MM/yyyy" value="${offer.closedAt}" />
                                          ">
                                          <img src="<c:url value='/img/off.png'/>"/>
                                       </span>
                                    </c:when>
                                    <c:otherwise>
                                       <span data-toggle="tooltip" data-placement="top" title="<spring:message code="OfferActive" />">
                                       <img src="<c:url value='/img/on.png'/>"/>
                                       </span>
                                    </c:otherwise>
                                 </c:choose>
                                 <a href="<c:url value='/job_offers/${offer.id}'/>"> ${offer.title}</a>
                              </h4>
                              <p>${offer.description}</p>
                              <c:forEach items="${offer.skills}" var="skill">
                                 <a class="no-underline"
                                    href="<c:url value='/job_offers?skill_id=${skill.id}'/>"><span
                                    class="label label-info">${skill.name}</span></a>
                              </c:forEach>
                           </div>
                           <div>
                              <div class="apply-button">
                                 <c:if test="${offer.user.id == loggedUser.id}">
                                    <div class="btn-group">
                                       <button type="button"
                                          class="btn btn-info btn-xs dropdown-toggle"
                                          data-toggle="dropdown" aria-haspopup="true"
                                          aria-expanded="false">
                                          <spring:message code="Action" />
                                          <span class="caret"></span>
                                       </button>
                                       <ul class="dropdown-menu dropdown-menu-right">
                                          <c:choose>
                                             <c:when test="${offer.closedAt == null}">
                                                <li>
                                                   <a
                                                      href="<c:url value='/job_offers/${offer.id}/edit'/>">
                                                      <spring:message code="Edit" />
                                                   </a>
                                                </li>
                                                <li>
                                                   <a href="" class="remove-button"
                                                      data-href="<c:url value='/job_offers/${offer.id}'/>">
                                                      <spring:message code="Delete" />
                                                   </a>
                                                </li>
                                                <li>
                                                   <a href="" class="finish-button"
                                                      data-href="<c:url value='/job_offers/${offer.id}/finish'/>">
                                                      <spring:message code="Finish" />
                                                   </a>
                                                </li>
                                             </c:when>
                                             <c:otherwise>
                                                <li>
                                                   <a href="" class="reopen-button"
                                                      data-href="<c:url value='/job_offers/${offer.id}/reopen'/>">
                                                      <spring:message code="Reopen" />
                                                   </a>
                                                </li>
                                                <li>
                                                   <a href="" class="remove-button"
                                                      data-href="<c:url value='/job_offers/${offer.id}'/>">
                                                      <spring:message code="Delete" />
                                                   </a>
                                                </li>
                                             </c:otherwise>
                                          </c:choose>
                                       </ul>
                                    </div>
                                 </c:if>
                              </div>
                           </div>
                        </div>
                     </c:forEach>
                  </div>
               </div>
            </c:if>
            <c:if test="${not empty offersApplied}">
               <div class="biography-into praesent">
                  <div class="dapibus">
                     <h3>
                        <spring:message code="JobOffersApplied" />
                     </h3>
                     <c:forEach items="${offersApplied}" var="applied">
                        <hr>
                        <div class="media">
                           <div class="media-body">
                              <h4>
                                 <a href="<c:url value='/job_offers/${applied.jobOffer.id}'/>">${applied.jobOffer.title}</a>
                              </h4>
                              <p><spring:message code="PostedBy" />: ${applied.jobOffer.user.email}</p>
                              <p>${applied.description}</p>
                           </div>
                           <div>
                              <div class="apply-button">
                                 <div class="btn-group">
                                    <button type="button"
                                       class="btn btn-info btn-xs dropdown-toggle"
                                       data-toggle="dropdown" aria-haspopup="true"
                                       aria-expanded="false">
                                       <spring:message code="Action" />
                                       <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right">
                                       <li>
                                          <a href="" class="remove-button"
                                             data-href="<c:url value='/job_application/${applied.id}'/>">
                                             <spring:message code="Delete" />
                                          </a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </c:forEach>
                  </div>
               </div>
            </c:if>
         </div>
      </div>
   </div>
</div>
<jsp:include page="../footer.jsp" />
<script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/style/chosen.css'/>">
<script type="text/javascript" src="<c:url value='/script/chosen/chosen.jquery.js'/>"></script>
<script type="text/javascript">
   $('.edit-button').on("click", function() {
   	$('#info-user').css("display", "none");
   	$('#editing-info-user').css("display", "block");
   })
   
   $('.cancel-edit-button').on("click", function() {
   	$('#info-user').css("display", "block");
   	$('#editing-info-user').css("display", "none");
   })
   
   $('#skills-select').chosen({
   	no_results_text : "Oops, no skills found!",
   	max_selected_options : 5
   });
   
   function setSelected(item, index) {
   	if (item != "") {
   		document.getElementById("skills-select").options[item-1].selected = true;
   	}
   }
   
   var selectedSkills = "${userForm.selectedSkillIds}";
   if (selectedSkills != "") {
   	var skillsArr = selectedSkills.split(",");
   	skillsArr.forEach(setSelected);
   	$("#skills_select_chosen").css("width", "100%");
   	$('#skills-select').trigger("chosen:updated");
   }
</script>