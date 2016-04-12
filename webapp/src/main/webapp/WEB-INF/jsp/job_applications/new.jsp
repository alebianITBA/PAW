<jsp:include page="../header.jsp" />

<div id="content-block" class="gallery-block">
  <div class="container custom-container be-detail-container">
    <div class="row">
      <form action="create" method="post">
        User ID:<br>
        <input type="text" name="userId"><br>
        Description:<br>
        <input type="text" name="description"><br><br>

        <input type="hidden" name="jobOfferId" value="${jobOfferId}">

        <input type="submit" value="Submit">
      </form>
    </div>
  </div>
</div>

<jsp:include page="../footer.jsp" />
