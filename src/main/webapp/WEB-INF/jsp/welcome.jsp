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
	<title>Welcome</title>

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">First Project</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/products">Products</a></li>
					<c:if test="${loggedInUser != null}">
						<li><a href="/orders?userId=${loggedInUser.getId()}">Your orders</a></li>
					</c:if>
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
					        	<li><a href="/settings?id=${loggedInUser.getId() }" title="Settings account"><span class="glyphicon glyphicon-user"></span> ${loggedInUser.getUsername()}</a></li>
			      				<li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
					    </c:otherwise>
					</c:choose>
			     
			    </ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Hello 
				<c:if test="${username != 'anonymousUser'}">
						${username}
				</c:if>
			</h1>
			<c:choose>
			    <c:when test="${username == 'anonymousUser'}">
			        <h2><a href="/login">Login</a></h2>
			    </c:when>    
			    <c:otherwise>
			        <h2><a href="/logout">Logout</a></h2>
			    </c:otherwise>
			</c:choose>
		</div>

	</div>
	
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
