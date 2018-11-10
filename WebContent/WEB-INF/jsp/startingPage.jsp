<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
<title><c:out value="Welcome to monster zoo."/></title>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>

Choose monster to feed:
	<form:form method="POST" action="/FeedTheMonster/feeding" modelAttribute="choosenMonster">
		<form:errors path="*" cssClass="errorblock" element="div" />
		Monster:
			<form:select path="name">
				<form:option value="NONE" label="--- Select ---" />
				<form:options items="${monsterNames}" />
			</form:select>
		<input type="submit" value="Submit"/>
		<form:errors path="name" cssClass="error" />
	</form:form>
<table>
	<tr>
		<th>Monster name:</th>
		<th>Monster birthday:</th>
		<th>Monster level:</th>
		<th>Portrait:</th>
	</tr>
	<c:forEach items="${monstersList}" var="monster">
		<tr>
			<td><c:out value="${monster.getName()}"/></td>
			<td><c:out value="${monster.getBirthday()}"/></td>
			<td><c:out value="${monster.getLevel()}"/></td>
			<td><img src="<c:url value="${monster.getPicture()}"/>"alt="Portrait" /></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
