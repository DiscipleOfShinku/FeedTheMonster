<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
<title><c:out value="Welcome to monster zoo."/></title>
<style></style>
</head>
<body>

Choose monster to feed:
	<form:form method="POST" action="/FeedTheMonster/feeding" modelAttribute="choosenMonster">
		<form:errors path="*" cssClass="errorblock" element="div" />
		Monster:
            <form:select path="id" required="required">
                <c:forEach items="${monstersList}" var="monster">
                    <form:option value="${monster.getId()}" label="${monster.getName()}" />
                </c:forEach>
            </form:select>
		<input type="submit" value="Submit"/>
		<form:errors path="id" cssClass="error" />
	</form:form>

<a href="/FeedTheMonster/creation">Register a new monster</a>

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
