<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
  <title><c:out value="Register new monster"/></title>
  <style></style>
</head>

<body>

  <h1>Register a new monster:</h1>

  <form:form method="POST" action="/FeedTheMonster/creation" modelAttribute="monster">
    <p>Name:</p>
    <form:input path="name" required="required" />
    <form:errors path="name" cssClass="error" />

    <input type="submit" value="Submit" />
  </form:form>

  <a href="/FeedTheMonster">Back</a>

</body>
</html>
