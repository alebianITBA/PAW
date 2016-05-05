<div class="panel panel-default">
  <div class="panel-body">
    <div class="row">
      <div class="col-md-offset-1">
        <div class="media">
          <div class="media-left">
            <a href="/users/${offers[0].userId}">
              <img class="media-object post-user-image" alt="64x64" src="/img/user-placeholder.png" data-holder-rendered="true">
            </a>
          </div>
          <div class="media-body">
            <h4 class="media-heading">${offers[0].title}</h4>
            <p>${offers[0].description}</p>
            <c:forEach items="${offers[0].skills}" var="skill">
              <span class="label label-info">${skill.name}</span>
            </c:forEach>
          </div>
          <br>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4 col-md-offset-5">
        <button class="btn btn-primary">Apply</button>
      </div>
    </div>
  </div>
</div>
