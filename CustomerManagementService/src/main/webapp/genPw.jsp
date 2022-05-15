<%@page import="EGSS.CustomerManagementService.contoller.LoginController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
<link rel="stylesheet" href="Views/bootstrap.min.css.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/login.js"></script>
</head>
<body style="background-color:#B0C4DE;">
<div style="text-align: center;position:relative;left:250px;top:50px;" class="container">
		<div class="row">
			<div style="background-color:#DCDCDC;height:920px;border-radius:10px;" class="col-6">
				<h1>Enter ID</h1>
				<form id="formGenPW" name="formGenPW"  action= "post" action="<%=request.getContextPath()%>/GeneratePwAPI">
					ID: 
					<input id="id" name="id" type="text"class="form-control form-control-sm" placeholder="Enter email"> <br>
					
					
					 
					 <input id="btnSave" name="btnSave" type="button" value="Login" class="btn btn-primary"> 
					 <input type="hidden" id="hididSave" name="hididSave" value="">
				
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				
				
			</div>
		</div>
	</div>

</body> 
</html>