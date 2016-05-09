<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<footer class="footer">
      <div class="container" style="margin-top:20px;">
      	<div class="f-left">
        <p><spring:message code="Group"/> 5</p>
    </div>
</footer>

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  <script>
     $( "span.menu" ).click(function() {
       $( ".top-menu" ).slideToggle( "slow", function() {
         // Animation complete.
       });
     });
  </script>
  </body>
</html>
