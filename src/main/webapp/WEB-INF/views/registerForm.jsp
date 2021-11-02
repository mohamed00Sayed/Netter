<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<html>
<head>
<title>Netter</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1><s:message code="netter.register"/></h1>

	<sf:form method="POST" modelAttribute="netter"
		enctype="multipart/form-data">
		<sf:errors path="*" element="div" cssClass="errors" />
		<table>
			<tr>
				<td><sf:label path="firstName" cssErrorClass="error"><s:message code="netter.fn"/></sf:label>:
				</td>
				<td><sf:input path="firstName" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="lastName" cssErrorClass="error"><s:message code="netter.ln"/></sf:label>:
				</td>
				<td><sf:input path="lastName" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="email" cssErrorClass="error"><s:message code="netter.email"/></sf:label>:
				</td>
				<td><sf:input path="email" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="username" cssErrorClass="error"><s:message code="netter.un"/></sf:label>:
				</td>
				<td><sf:input path="username" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="password" cssErrorClass="error"><s:message code="netter.pswd"/></sf:label>:
				</td>
				<td><sf:password path="password" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><label><s:message code="netter.pp"/></label>:</td>
				<td><input type="file" name="profilePicture"
					accept="image/jpeg,image/png,image/gif" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value='<s:message code="netter.register"/>' /></td>
				<td></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>
