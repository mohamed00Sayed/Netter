<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Netter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="nettleForm">
      <h1><s:message code="netter.slogan"/></h1>
      <form method="POST" name="NetttleForm">
        <input type="hidden" name="latitude">
        <input type="hidden" name="longitude">
        <textarea name="message" cols="80" rows="5"></textarea><br/>
        <input type="submit" value='<s:message code="netter.add"/>' />
      </form>
    </div>
    <div class="listTitle">
      <h1><s:message code="netter.recent"/></h1>
      <ul class="nettleList">
        <c:forEach items="${nettleList}" var="nettle" >
          <li id="nettle_<c:out value="nettle.id"/>">
            <div class="nettleMessage"><c:out value="${nettle.message}" /></div>
            <div>
              <span class="nettleTime"><c:out value="${nettle.time}" /></span>
              <span class="nettleLocation">(<c:out value="${nettle.latitude}" />, <c:out value="${nettle.longitude}" />)</span>
            </div>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${fn:length(nettleList) gt 20}">
        <hr />
        <s:url value="/nettles?count=${nextCount}" var="more_url" />
        <a href="${more_url}"><s:message code="netter.mor"/></a>
      </c:if>
    </div>
  </body>
</html>