<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
	<title>Unauthorized</title>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">First Project</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/">Home</a></li>
					<li><a href="/products">Products</a></li>
					<c:if test="${loggedInUser.getRole() == '1'}">
						<li><a href="/manageproducts">Manage Products</a></li>
						<li><a href="/manageusers">Manage Users</a></li>
					</c:if>
					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
					    <c:when test="${loggedInUser == null}">
					         <li><a href="/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			      			<li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
					    </c:when>    
					    <c:otherwise>
					        	<li><span class="glyphicon glyphicon-user"></span>${loggedInUser.getUsername()}</li>
			      				<li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
					    </c:otherwise>
					</c:choose>
			     
			    </ul>
			</div>
		</div>
	</nav>
	<div class="container">
	<h1>401</h1>
		<p>Unauthorized</p>
		<p>Please <a href="/login">login</a></p>
	</div>
</body>
</html>