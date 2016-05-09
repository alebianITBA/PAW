<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<div class="content">
<div class="container">
   <div class="col-md-3 praesent">
      <div class="l_g_r">
         <div class="dapibus biography-into center">
            <a href="/users/${job.user.id}">
            <img class="user-placeholder" src="/img/user-placeholder.png">
            </a>
            <h4>${job.user.firstName} ${job.user.lastName}</h4>
            <p class="center">${job.user.email}</p>
            <c:if test="${job.user.id == loggedUser.id}">
               <h3><span class="label label-info">${quantityApplications} applications</span></h3>
            </c:if>
         </div>
      </div>
   </div>
   <div class="col-md-9 praesent">
      <div class="l_g_r b-margin" >
         <div class="dapibus biography-into">
            <h2 class="media-heading">${job.title} </h2>
            <p>${job.description}</p>
            <h3>
               <c:forEach items="${job.skills}" var="skill">
                  <span class="label label-info">${skill.name}</span>
               </c:forEach>
            </h3>
         </div>
      </div>
      <c:if test="${job.user.id == loggedUser.id}">
            <div class="l_g_r">
               <div class="dapibus biography-into">
                  <h2 class="media-heading">APPLICATIONS</h2>
                  <c:forEach items="${applications}" var="application">
                     <div class="media">
                        <div class="media-left">
                           <a href="/users/${job.userId}">
                           <img class="user-placeholder" src="/img/user-placeholder.png">
                           </a>
                        </div>
                        <div class="media-body">
                           <h3 class="media-heading">${application.user.firstName} ${application.user.lastName}</h3>
                           <h4 class="media-heading">${application.user.email}</h4>
                           <c:forEach items="${application.user.skills}" var="skill">
                              <span class="label label-info">${skill.name}</span>
                           </c:forEach>
                        </div>
                        <hr>
                     </div>
                  </c:forEach>
               </div>
            </div>
      </c:if>
      <c:if test="${job.user.id != loggedUser.id}">
            <div class="l_g_r">
               <div class="dapibus biography-into">
                    <h4>APPLY NOW!</h4>
                     <button type="submit" class="btn btn-info btn-block btn-lg">APPLY</button>
               </div>
            </div>
      </c:if>
</div>
</div>
</div>
<jsp:include page="../footer.jsp" />