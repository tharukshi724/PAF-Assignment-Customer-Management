package EGSS.CustomerManagementService.contoller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import EGSS.CustomerManagementService.constants.CustomerConstants;
import EGSS.CustomerManagementService.modal.CustomerModal;
//import EGSS.CustomerManagementService.modal.UserModal;
import EGSS.CustomerManagementService.utils.CustomerDBConnection;



public class LoginController {
	
	public static String loginUser(String email,String password) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException {
		
		  CustomerModal customersLogin = null;
		  String query =CustomerConstants.LOGUSER;
		  System.out.println(query);
		  Connection connection = CustomerDBConnection.getConnection();
		  PreparedStatement preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(1, email);
		  preparedStatement.setString(2, password);
		  ResultSet rs = preparedStatement.executeQuery();
		  String role = "";
		  int id = 0;
		  String email1 = "";
		  String password1 = "";
		  while(rs.next()) {
			  
			  email1 = rs.getString("email");
			   password1 = rs.getString("password");
			  role = rs.getString("role");
			  id = rs.getInt("id");
			  
			  customersLogin = new CustomerModal(id,role);
		  }
		  System.out.println(customersLogin);
		
		  if(email.equals(email1) && password.equals(password1)) {
			   
			  return JwtTokenService.getJWTToken(id, role);
		  }else {
			  return "Login failed please check your email and password";
		  }
		
		   
		
		     
	}

}
