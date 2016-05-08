<div class="l_g_r">
  <div class="dapibus">
    <h2><a href="/job_offers/${offers[0].id}">${offers[0].title}</a></h2>
    <p class="adm">Posted by <a href="/users/${offers[0].userId}">${offers[0].userId}</a></p>
    <p>${offers[0].description}</p>
    <c:forEach items="${offers[0].skills}" var="skill">
              <span class="label label-info">${skill.name}</span>
    </c:forEach>
<button class="btn btn-primary">Apply</button>

  </div>
</div>

            
          