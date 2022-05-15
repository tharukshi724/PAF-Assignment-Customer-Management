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


	// If valid------------------------
	var type = ($("#hididSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "GeneratePwAPI",
		type : type,
		data : $("#formPw").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onPwComplete(response.responseText, status);
		}
	});

});

function onPwComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
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
	$("#hididSave").val("");
	$("#formPw")[0].reset();
}


function validateUserForm()
{

if ($("#id").val().trim() == "")
 {
 return "Insert id.";
 }


 }