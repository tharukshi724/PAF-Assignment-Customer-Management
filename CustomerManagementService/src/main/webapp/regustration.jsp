<%@page import="EGSS.CustomerManagementService.contoller.CustomerController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Registration</title>
<link rel="stylesheet" href="Views/bootstrap.min.css.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/user.js"></script>
</head>
<body style="background-color:#B0C4DE;">
<div style="text-align: center;position:relative;left:250px;top:50px;" class="container">
		<div class="row">
			<div style="background-color:#DCDCDC;height:920px;border-radius:10px;" class="col-6">
				<h1>User Details</h1>
				<form id="formUser" name="formUser" method="post" action="<%=request.getContextPath()%>/UserAPI">
					First Name: 
					<input id="firstName" name="firstName" type="text"class="form-control form-control-sm" placeholder="Enter First Name"> <br>
					Last Name:
					<input id="lastName" name="lastName" type="text" class="form-control form-control-sm" placeholder="Enter Last Name"> <br> 
					NIC:
					 <input id="nic" name="nic" type="text" class="form-control form-control-sm" placeholder="Enter NIC"> <br>
					 Email:
					 <input id="email" name="email" type="text" class="form-control form-control-sm" placeholder="Enter Email "> <br>
					 Street:
					 <input id="Street" name="Street" type="text" class="form-control form-control-sm" placeholder="Enter Street"> <br> 
					  state:
					 <input id="state" name="state" type="text" class="form-control form-control-sm" placeholder="Enter state"> <br> 
					  Postal Code:
					 <input id="postalCode" name="postalCode" type="text" class="form-control form-control-sm" placeholder="Enter Postal Code"> <br>
					  Role:
					 <input id="role" name="role" type="text" class="form-control form-control-sm" placeholder="Enter user role"> <br>  
					 
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					 <input type="hidden" id="hididSave" name="hididSave" value="">
				
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div style="position:relative;right:20px;" id="divUserGrid">
					<%
					CustomerController customerObj = new CustomerController();
					System.out.println(customerObj.viewListOfCustomers());
					%>
				</div>
				
				
			</div>
		</div>
	</div>

</body> 
</html>