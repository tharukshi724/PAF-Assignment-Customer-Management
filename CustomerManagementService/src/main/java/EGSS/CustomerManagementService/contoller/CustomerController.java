package EGSS.CustomerManagementService.contoller;

import java.security.SecureRandom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import EGSS.CustomerManagementService.constants.CustomerConstants;
import EGSS.CustomerManagementService.modal.CustomerModal;
import EGSS.CustomerManagementService.utils.CustomerDBConnection;



public class CustomerController {
	
	  public static String viewListOfCustomers() throws SQLException{
			
	      List <CustomerModal> customers = new ArrayList<> ();
	      String query =CustomerConstants.VIEWCUSTOMER;
	       Connection connection = null;
	      try {
		      connection = CustomerDBConnection.getConnection();
	       } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		      e.printStackTrace();
	       }
	      PreparedStatement preparedStatement = connection.prepareStatement(query);
	      System.out.println(connection.prepareStatement(query));
	      ResultSet rs = preparedStatement.executeQuery();
	      
	      String output = "<h1>Customer Details</h1>";
	      output += "<table border='1'>";
          output += "<tr>\r\n"
        		+ "    <th>ID</th>\r\n"
        		+ "    <th>First Name</th>\r\n"
        		+ "    <th>Last Name</th>\r\n"
        		+ "    <th>NIC</th>\r\n"
        		+ "    <th>Email</th>\r\n"
        		+ "    <th>Street</th>\r\n"
        		+ "    <th>State</th>\r\n"
        		+ "    <th>Postal Code</th>\r\n"
        		+ "    <th>role</th>\r\n"
        		+ "  </tr>";
	  
	      while(rs.next()) {
		      output += "<tr>";
		      output += "<td>"+ rs.getString(1)+"</td>";
		      output += "<td>"+ rs.getString(2)+"</td>";
		      output += "<td>"+ rs.getString(3)+"</td>";
		      output += "<td>"+ rs.getString(4)+"</td>";
		      output += "<td>"+ rs.getString(5)+"</td>";
		      output += "<td>"+ rs.getString(6)+"</td>";
		      output += "<td>"+ rs.getString(7)+"</td>";
		      output += "<td>"+ rs.getString(8)+"</td>";
		      output += "<td>"+ rs.getString(9)+"</td>";
		      output += "</tr>";

	  }
	  
	  output += "</table>";
	  return output;
	}
  
  
	
	     //add customer details to system
	
		public static String addCustomer(int id , String firstName,String lastName,String nic, String email, String Street,String state,String postalCode, String role) throws SQLException, ClassNotFoundException {
			  String result = "";
			  String query =CustomerConstants.ADDTOCUSTOMER;
			  Connection connection = CustomerDBConnection.getConnection();
			  PreparedStatement preparedStatement = connection.prepareStatement(query);
			  
				  preparedStatement.setInt(CustomerConstants.INDEX_ONE, id);
				  preparedStatement.setString(CustomerConstants.INDEX_TWO, firstName);
				  preparedStatement.setString(CustomerConstants.INDEX_TREE, lastName);
				  preparedStatement.setString(CustomerConstants.INDEX_FOUR,nic);
				  preparedStatement.setString(CustomerConstants.INDEX_FIVE,email);
				  preparedStatement.setString(CustomerConstants.INDEX_SIX,Street);
				  preparedStatement.setString(CustomerConstants.INDEX_SEVEN,state);
				  preparedStatement.setString(CustomerConstants.INDEX_EIGHT,postalCode);
				  preparedStatement.setString(CustomerConstants.INDEX_NINE,role);
			
			 
				
				   boolean successfullyAdded = preparedStatement.execute();
			       if(!successfullyAdded) {
				
				        return result = "Successfully added to the system";
				   }
			       else {
				       return result = " Error occur while inserting data";
			  }
		}
		
		
		
	     // Get id for authentication
		
	      public static CustomerModal getAuthData() throws ClassNotFoundException, SQLException {
		 
		        CustomerModal customers = new CustomerModal();
		        String query =CustomerConstants.GETAUTHDETAILS;
		        Connection connection = CustomerDBConnection.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		        ResultSet rs = preparedStatement.executeQuery();
		        while(rs.next()) {
			        int id = rs.getInt("id");
			       customers = new CustomerModal(id);
		         } 

		       return customers;
		 
	        }
	      
	      
	      // Generate Password 
	      
	       public static String insertAuthData(int Uid,int id) throws ClassNotFoundException, SQLException {
		         
	    	     String result = "";
		         String query =CustomerConstants.INSERTAUTHDATA;
		         Connection connection = CustomerDBConnection.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query);
		         
		         //generate random password
		         String password = generateRandomPassword(8);
	
			     preparedStatement.setInt(CustomerConstants.INDEX_ONE,Uid);
			     preparedStatement.setString(CustomerConstants.INDEX_TWO, password);
			     preparedStatement.setInt(CustomerConstants.INDEX_TREE,id);
			     boolean successfullyAdded = preparedStatement.execute();
			      if(!successfullyAdded) {
				
				     return result = "Password generate Successfully";
			       }
			     else {
				    return result =" Error occur while generating password";
			     }
          }
	 

		
	      // View all customers
		
		
		
		    // Get One Customer Details
		  public static CustomerModal selectCustomer( int id) throws SQLException {
			    CustomerModal customer = null;
		    	String query =CustomerConstants.GETONECUSTOMER;
				Connection connection = null;
				try {
					connection = CustomerDBConnection.getConnection();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,id);
		        ResultSet rs = preparedStatement.executeQuery();
		    		    
		         while(rs.next()) {
		    		    	
		    		  String firstName =  rs.getString("firstName");
		  	          String lastName = rs.getString("lastName");
		  			  String nic = rs.getString("nic");
		  			  String email = rs.getString("email");
		  			  String street = rs.getString("street");
		  			  String state = rs.getString("state");
		  			  String postalCode = rs.getString("postalCode");
		  			  int uid = rs.getInt("id");
		  				 
		    		  customer = new CustomerModal(id,firstName,lastName,nic,email,street,state,postalCode);
		    	  }
		    	  
		    	 return customer;
		    	 
		     }
		   
		   
		   
		   // Update Customer 
		  
		   public static  String updateCustomer(int id,String firstName,String lastName,String nic, String email, String Street,String state,String postalCode, String role) throws SQLException {
			     String result ="";
		         boolean rowUpdated = false;
		         String query =CustomerConstants.UPDATECUSTOMER;
				 Connection connection = null;
				 try {
					connection = CustomerDBConnection.getConnection();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				  PreparedStatement preparedStatement = connection.prepareStatement(query);
		        	
				  preparedStatement.setString(1,firstName);
				  preparedStatement.setString(2,lastName);
				  preparedStatement.setString(3, nic);
				  preparedStatement.setString(4,email);
				  preparedStatement.setString(5,Street);
				  preparedStatement.setString(6,state);
				  preparedStatement.setString(7,postalCode);
				  preparedStatement.setString(8, role);
		            
				  preparedStatement.setInt(9, id);

		             rowUpdated = preparedStatement.executeUpdate() > 0;//return number of rows updated
		             
		             if(rowUpdated) {
		            	 return result= "Update process success!";
		             }else {
		            	 return result =" Update error occur!";
		             }
		         
		     }
		   
		   
		   //Delete customer
		   
		   public static String deleteCustomer(int id) throws SQLException {
			     String result = "";
			     int rowDeleted;
		          String query =CustomerConstants.DELETECUSTOMER;
				  Connection connection = null;
				try {
					connection = CustomerDBConnection.getConnection();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				  PreparedStatement preparedStatement = connection.prepareStatement(query);
		        	
				  preparedStatement.setInt(1,id);
					//use to update the query
		          rowDeleted = preparedStatement.executeUpdate();//return number of rows deleted
		          System.out.println(rowDeleted);
		          if(rowDeleted == 1) {
		        	  return result = " Row deleted Successfully";
		          }else {
		        	  return result = " Error occur while deleting customer";
		          }
		              
		         
		     }
		   
		
		
		   
		   public static String generateRandomPassword(int len)
		    {
		        // ASCII range – alphanumeric (0-9, a-z, A-Z)
		        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		 
		        SecureRandom random = new SecureRandom();
		        StringBuilder sb = new StringBuilder();
		 
		        // each iteration of the loop randomly chooses a character from the given
		        // ASCII range and appends it to the `StringBuilder` instance
		 
		        for (int i = 0; i < len; i++)
		        {
		            int randomIndex = random.nextInt(chars.length());
		            sb.append(chars.charAt(randomIndex));
		        }
		 
		        return sb.toString();
		    }
		   
		   
		   
		 

	private static void printSQLException(SQLException e) {
		// TODO Auto-generated method stub
		CustomerModal customer = new CustomerModal();
		customer.setStatus(true);
		customer.setCreateBy(1);
		customer.setCreatedDate("2021-9-10");
		customer.setModifiedDate("2021-6-5");
		customer.setModifiedBy(1);
		
	}
		
		
		

}
