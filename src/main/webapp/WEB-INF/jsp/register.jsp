<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Sabong Pro</title>
	</head>
	<body>
		<c:url var="register" value="/sabongpro/user/register"/>
		<form:form modelAttribute="user" action="${register}" method="post">
			<c:out value="${notificationMessage}"/><br/>
			<c:out value="${successMessage}"/><br/>
			<c:out value="${errorMessage}"/><br/>
			<form:errors path="lastname" cssClass="error"/><br/>
			<form:errors path="firstname" cssClass="error"/><br/>
			<form:errors path="middlename" cssClass="error"/><br/>
			<form:errors path="email" cssClass="error"/><br/>
			<form:errors path="username" cssClass="error"/><br/>
			<form:errors path="password" cssClass="error"/><br/>
			<form:errors path="confirmPassword" cssClass="error"/><br/><br/>
			
			<label for="lastname">Lastname:</label><form:input path="lastname"/><br/>
			<label for="firstname">Firstname:</label><form:input path="firstname"/><br/>
			<label for="middlename">Middlename:</label><form:input path="middlename"/><br/>
			<label for="email">Email:</label><form:input path="email"/><br/>
			<label for="username">Username:</label><form:input path="username"/><br/>
			<label for="password">Password:</label><form:input path="password"/><br/>
			<label for="confirmPassword">Confirm Password:</label><form:input path="confirmPassword"/><br/>
			<input type="submit" value="Register"/>
		</form:form>
	</body>
</html>