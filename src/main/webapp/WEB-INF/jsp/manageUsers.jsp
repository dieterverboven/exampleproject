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
	<title>Manage Users</title>
	
	
	
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
						<li class="active"><a href="/manageusers">Manage Users</a></li>
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
	

	<div class="container table-responsive">

	<h1>Manage Users</h1>

		<table class="table">
			<tr>
				<th>
					Username
				</th>
				<th>
					Role
				</th>
				<th>
					Actions
				</th>
			</tr>
			
			<c:forEach items="${list}" var="item">
			<tr>
				<td>
				${item.getUsername()}
				</td>
				<td>
				<c:choose>
			    <c:when test="${item.getRole() == 1}">
			        <p>Admin</p>
			    </c:when>    
			    <c:otherwise>
			        <p>User</p>
			    </c:otherwise>
			</c:choose>
				</td>
				<td>
				
				<c:choose>
					    <c:when test="${loggedInUser.getId() == item.getId()}">
					        <button type="submit" class="btn btn-danger" disabled title="You cannot delete your own account">Delete</button>
					    </c:when>    
					    <c:otherwise>
					        	<form action="deleteuser" method="post">
						
									<input type="hidden" name="id" value="${item.getId()}"/>
									<button type="submit" class="btn btn-danger">Delete</button>
								</form>	
					    </c:otherwise>
				</c:choose>
					
					
				</td>
				</tr>
			</c:forEach>
			</tr>
		</table>
	
	<button id="addButton" type="button" class="btn btn-success" onclick="return addUser()">Add user</button>
	</div>
	
	<div class="container" id="addForm">
	
		<h2 id="test">Add user</h2>

		<form action="/addProduct" method="post">
		<div class="form-group">
			<label> Username: <input type="text" name="Username"/> </label>
		</div>
            <div class="form-group"><label> Password: <input type="number" step="0.01" name="Username"/> </label></div>
        <button type="submit" class="btn btn-primary">Submit</button>
        
        <button id="btnCancel" onclick="return cancelForm()" class="btn">Cancel</button>
        </form>
	</div>
	
	
	
	<!-- <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

	<script type="text/javascript">
	window.onload = load();
	
		function load(){
			document.getElementById("addForm").style.display = "none";
		};    
		
		
		function addUser() {
			document.getElementById("addForm").style.display = "block";
			document.getElementById("addButton").style.display = "none";
		}
		
		function cancelForm() {
			document.getElementById("addForm").style.display = "none";
			document.getElementById("addButton").style.display = "block";
		}
	</script>
	

</body>

</html>
