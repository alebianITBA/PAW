<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>


<jsp:include page="../header.jsp" />

<!-- MAIN CONTENT -->
<div id="content-block">
<div class="container-fluid custom-container be-detail-container">
<div class="isotope-grid row">

<c:forEach items="${users}" var="user">

<div class="isotope-item col-ml-12 col-xs-6 col-sm-4 col-md-3 col-lg-3 col-xl-2">
<div class="be-post style-5">
<a href="/users/${user.id}" class="be-img-block">
<img src="/img/user_placeholder.jpg" alt="omg">
</a>
<div class="be-rowline">
<div class="rowline-text"> ${user.firstName} ${user.lastName} <span class="pull-right"><i class="fa fa-thumbs-o-up"></i> 225</span></div>
</div>
<div class="author-post">
<i class="fa fa-envelope"></i>
<span>${user.email}</span>
<span class="rowline-icon"><i class="fa fa-comment-o"></i></span>
</div>
</div>
</div>

</c:forEach>


</div>
</div>
</div>


<jsp:include page="../footer.jsp" />
