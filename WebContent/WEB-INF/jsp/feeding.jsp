<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
<title><c:out value="Monster was pleasured."/></title>
</head>
<body>
	<h2>Monster was pleasured.</h2>

<table>
	<tr>
		<th>Monster name:</th>
		<th>Monster birthday:</th>
		<th>Monster level:</th>
	</tr>
	<tr>
		<td><c:out value="${monster.getName()}"/></td>
		<td><c:out value="${monster.getBirthday()}"/></td>
		<td><c:out value="${monster.getLevel()}"/></td>
	</tr>
</table>
<a href="/FeedTheMonster/">Feed another monster.</a>

</body>
</html>