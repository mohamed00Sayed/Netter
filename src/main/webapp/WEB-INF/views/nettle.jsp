<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Netter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="nettleView">
      <div class="nettleMessage"><c:out value="${nettle.message}" /></div>
      <div>
        <span class="nettleTime"><c:out value="${nettle.time}" /></span>
      </div>
    </div>
  </body>
</html>