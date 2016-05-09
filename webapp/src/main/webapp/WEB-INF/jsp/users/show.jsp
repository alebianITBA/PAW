<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../header.jsp" />
<div class="content">
<div class="container">
   <div class="col-md-12 praesent">
      <div class="l_g_r biography-into">
         <div class="dapibus center">
            <img class="user-image" src="/img/user-placeholder.png">
            <div >
               <h1>${user.firstName} ${user.lastName}</h1>
               <h3>${user.email}</h3>
               <c:forEach items="${user.skills}" var="skill">
                  <span class="label label-info">${skill.name}</span>
               </c:forEach>
            </div>
         </div>
      </div>
   </div>
   <div class="col-md-6 praesent">
      <div class="l_g_r biography-into">
         <div class="dapibus">
            <c:if test="${posts[0] != null}">
               <h3><spring:message code="RecentPosts"/></h3>
               <c:forEach items="${posts}" var="post">
                  <hr>
                  <h4>${post.title}</h4>
                  <p>${post.description}</p>
               </c:forEach>
            </c:if>
         </div>
      </div>
   </div>
   <div class="col-md-6 praesent">
      <div class="l_g_r biography-into">
         <div class="dapibus">
            <c:if test="${posts[0] != null}">
               <h3><spring:message code="JobOffersCreated"/></h3>
               <c:forEach items="${offers}" var="offer">
                  <hr>
                  <div class="media">
                     <div class="media-body">
                        <h4><a href="/job_offers/${offer.id}">${offer.title}</a></h4>
                        <p>${offer.description}</p>
                        <c:forEach items="${offer.skills}" var="skill">
                           <span class="label label-info">${skill.name}</span>
                        </c:forEach>
                     </div>
                  </div>
               </c:forEach>
            </c:if>
         </div>
      </div>
   </div>
</div>
</div>
<jsp:include page="../footer.jsp" />