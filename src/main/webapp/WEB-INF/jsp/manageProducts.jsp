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
	<title>Manage products</title>
	
	
	
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">First Project</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/products">Products</a></li>
					<c:if test="${loggedInUser.getRole() == '1'}">
						<li><a href="/manageproducts">Manage Products</a></li>
					</c:if>
					
				</ul>
			</div>
		</div>
	</nav>
	

	<div class="container table-responsive">

	<h1>Manage products</h1>

		<table class="table">
			<tr>
				<th>
					Name
				</th>
				<th>
					Price
				</th>
				<th>
					Actions
				</th>
			</tr>
			
			<c:forEach items="${list}" var="item">
			<tr>
				<td>
				${item.getName()}
				</td>
				<td>
				${item.getPrice()} leva
				</td>
				<td>
					<button type="button" class="btn" onClick="return hello();">Edit</button>
					<form action="deleteproduct" method="post">
						<input type="hidden" name="id" value="${item.getId()}"/>
						<button type="submit" class="btn btn-danger">Delete</button>
					</form>
					
				</td>
				</tr>
			</c:forEach>
			</tr>
		</table>
	
	<button id="addButton" type="button" class="btn btn-success" onclick="return addProduct()">Add product</button>
	</div>
	
	<div class="container" id="addForm">
	
		<h2 id="test">Add product</h2>

		<form action="/addProduct" method="post">
		<div class="form-group">
			<label> Name of Product: <input type="text" name="name"/> </label>
		</div>
            <div class="form-group"><label> Price: <input type="number" step="0.01" name="price"/> </label></div>
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
		
		
		function addProduct() {
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
