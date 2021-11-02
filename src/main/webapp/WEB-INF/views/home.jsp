<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false" %>
<html>
  <head>
    <title>Netter</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1><s:message code="netter.welcome" /></h1>

    <a href="<c:url value="/nettles" />">Nettles</a> | 
    <a href="<c:url value="/netter/register" />"><s:message code="netter.register"/></a>
  </body>
</html>
