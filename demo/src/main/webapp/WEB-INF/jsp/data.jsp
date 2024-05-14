<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<head>
<title>Data based on criteria</title>
</head>
<body>
	<p>
		Users who live in the state of ${stateName}
	</p>
	<ul>
	<ul>
    <c:forEach items="${userbystate}" var="user">
        <li>${user.username}</li>
    </c:forEach>
</ul>

	
	</ul>

</body>
</html>
