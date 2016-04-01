<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<body>
<h1>Users</h1>
<c:forEach items="${users}" var="user">
    ${user.id}, ${user.email}, ${user.firstName}, ${user.lastName}<br>
</c:forEach>
</body>
</html>
