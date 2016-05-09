<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="java.util.*" %>

<jsp:include page="../header.jsp" />
<div class="content">
<div class="container">
   <c:forEach items="${users}" var="user">
      <div class="col-md-3 praesent center biography-into">
         <div class="l_g_r">
            <div class="dapibus">
               <a href="<c:url value='/users/${user.id}'/>">
               <img class="user-image" src="/img/user-placeholder.png">
               </a>
               <div class="caption">
                  <h3><a href="<c:url value='/users/${user.id}'/>">${user.firstName} ${user.lastName}</a></h3>
                  <p class="center"><i>${user.email}</i></p>
                  <p class="skill-container">
                     <c:forEach items="${user.skills}" var="skill">
                        <span class="label label-info">${skill.name}</span>
                     </c:forEach>
                  </p>
               </div>
            </div>
         </div>
      </div>
   </c:forEach>
</div>
</div>
<jsp:include page="../footer.jsp" />
