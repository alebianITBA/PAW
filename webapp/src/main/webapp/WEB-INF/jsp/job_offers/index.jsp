<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<body>
<h1>Job Offers</h1>
<c:forEach items="${job_offers}" var="job">
    ${job.title}, ${job.description}, ${job.userId}<br>
</c:forEach>
</body>
</html>
