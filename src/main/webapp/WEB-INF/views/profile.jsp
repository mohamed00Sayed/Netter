<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Netter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1><s:message code="netter.profiletxt"/></h1>
    <c:out value="${netter.username}" /><br/>
    <c:out value="${netter.firstName}" /> <c:out value="${netter.lastName}" /><br/>
    <c:out value="${netter.email}" />
  </body>
</html>
