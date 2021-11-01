<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<html>
<head>
<title>Netter</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1>Register</h1>

	<sf:form method="POST" modelAttribute="netter"
		enctype="multipart/form-data">
		<sf:errors path="*" element="div" cssClass="errors" />
		<table>
			<tr>
				<td><sf:label path="firstName" cssErrorClass="error">First Name</sf:label>:
				</td>
				<td><sf:input path="firstName" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="lastName" cssErrorClass="error">Last Name</sf:label>:
				</td>
				<td><sf:input path="lastName" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="email" cssErrorClass="error">Email</sf:label>:
				</td>
				<td><sf:input path="email" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="username" cssErrorClass="error">Username</sf:label>:
				</td>
				<td><sf:input path="username" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="password" cssErrorClass="error">Password</sf:label>:
				</td>
				<td><sf:password path="password" cssErrorClass="error" /></td>
			</tr>
			<tr>
				<td><label>Profile Picture</label>:</td>
				<td><input type="file" name="profilePicture"
					accept="image/jpeg,image/png,image/gif" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
				<td></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>
