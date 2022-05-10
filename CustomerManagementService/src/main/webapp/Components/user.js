$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateUserForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "UserAPI",
		type : type,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onUserSaveComplete(response.responseText, status);
		}
	});

});

function onUserSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divUserGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidIDSave").val("");
	$("#formUser")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
			$("#hidIDSave").val( $(this).closest("tr").find('#hidIDUpdate').val());
			$("#firstName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#lastName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#nic").val($(this).closest("tr").find('td:eq(3)').text());
			$("#email").val($(this).closest("tr").find('td:eq(4)').text());
			$("#Street").val($(this).closest("tr").find('td:eq(5)').text());
			$("#state").val($(this).closest("tr").find('td:eq(6)').text());
			$("#postalCode").val($(this).closest("tr").find('td:eq(7)').text());
			$("#role").val($(this).closest("tr").find('td:eq(8)').text());
			$("#id").val($(this).closest("tr").find('td:eq(1)').text());
			
		});

// Delete============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "UserAPI",
		type : "DELETE",
		data : "id=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onUserDeleteComplete(response.responseText, status);
		}
	});
});

function onUserDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


//CLIENTMODEL=========================================================================
function validateUserForm()
{

if ($("#firstName").val().trim() == "")
 {
 return "Insert first Name.";
 }

if ($("#lastName").val().trim() == "")
 {
 return "Insert last Name.";
 } 

if ($("#nic").val().trim() == "")
 {
 return "Insert NIC.";
 }
//cvv-------------------------------
if ($("#email").val().trim() == "")
 {
 return "Insert email.";
 }

if ($("#Street").val().trim() == "")
 {
 return "Insert Street.";
 }
 if ($("#state").val().trim() == "")
 {
 return "Insert state.";
 }
  if ($("#postalCode").val().trim() == "")
 {
 return "Insert postal code.";
 }
  if ($("#role").val().trim() == "")
 {
 return "Insert role Admin or User.";
 }




return true;
}
