<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />
<div class="content">
   <div class="container">
      <c:if test="${skills[0] != null}">
         <div class="col-md-8 praesent">
            <div class="l_g_r biography-into">
               <div class="dapibus">
                  <c:forEach items="${job_offers}" var="offer">
                     <div class="media">
                        <div class="media-left">
                           <a href="/users/${offer.userId}">
                           <img class="user-placeholder" src="/img/user-placeholder.png">
                           </a>
                        </div>
                        <div class="media-body">
                           <h4><a href="/job_offers/${offer.id}">${offer.title}</a></h4>
                           <p>${offer.description}</p>
                           <c:forEach items="${offer.skills}" var="skill">
                              <span class="label label-info">${skill.name}</span>
                           </c:forEach>
                        </div>
                        <div>
                           <div class="apply-button">
                              <button class="btn btn-info"><spring:message code="Apply"/></button>
                           </div>
                        </div>
                     </div>
                     <hr>
                  </c:forEach>
               </div>
            </div>
         </div>
      </c:if>
      <div class="col-md-4 praesent">
         <div class="l_g_r b-margin">
            <div class="dapibus biography-into">
               <div class="input-group b-margin">
                  <select name="skills" id="skills-select" class="form-control input-lg">
                     <c:forEach items="${skills}" var="skill">
                        <option value="${skill.id}">${skill.name}</option>
                     </c:forEach>
                  </select>
               </div>
               <button id="filter-offers" class="btn btn-info btn-block btn-lg form-control "><spring:message code="Filter"/></button>
            </div>
         </div>

         <div class="l_g_r">
           <div class="dapibus biography-into">
             <h4><spring:message code="NeedToHire"/></h4>
             <spring:url value="job_offers/create_offer" var="offerUrl" />
             <form:form method="post" modelAttribute="jobOfferForm" action="${offerUrl}" class="form-header" role="form" id="#">
                <div class="form-group">
                   <spring:message code="JobOfferTitle" var="jobOfferTitle"/>
                   <form:input type="text" class="form-control input-lg" required="" placeholder="${jobOfferTitle}*" path="title" />
                   <c:set var="titleErrors"><form:errors path="title" /></c:set>
                   <c:if test="${not empty titleErrors}">
                     <tr><td>${titleErrors}</td></tr>
                   </c:if>
                </div>
                <div class="form-group">
                  <spring:message code="Content" var="content"/>
                  <form:textarea class="form-control input-lg post-textarea" required="" placeholder="${content}*" path="description" />
                  <c:set var="descriptionErrors"><form:errors path="description" /></c:set>
                  <c:if test="${not empty descriptionErrors}">
                    <tr><td>${descriptionErrors}</td></tr>
                  </c:if>
                </div>
                <spring:message code="Submit" var="submit"/>
                <input type="submit" class="btn btn-info btn-block btn-lg" value="${submit}" />
             </form:form>
           </div>
         </div>
      </div>
   </div>
</div>
<jsp:include page="../footer.jsp" />
<script type="text/javascript" src="/script/job_offers.js"></script>
