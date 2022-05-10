package EGSS.CustomerManagementService.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EGSS.CustomerManagementService.contoller.CustomerController;
import EGSS.CustomerManagementService.modal.CustomerModal;


@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerController customerobj = new CustomerController();

    /**
     * Default constructor. 
     */
    public UserAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String output= "";
		try {
			output = customerobj.addCustomer( (Integer.parseInt(request.getParameter("id"))),
					   request.getParameter("firstName"), 
						request.getParameter("lastName"), 
						request.getParameter("nic"), 
						request.getParameter("email"),
						request.getParameter("Street"),
						request.getParameter("state"),
						request.getParameter("postalCode"),
						request.getParameter("role")
						
						
					 );
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

           response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Map paras = getParasMap(request); 
		 String output = null;
		try {
			output = customerobj.updateCustomer((int) paras.get("hididSave"), 
												 paras.get("firstName").toString(), 
												 paras.get("lastName").toString(), 
												 paras.get("nic").toString(), 
												 paras.get("email").toString(),
												 paras.get("Street").toString(),
												 paras.get("state").toString(),
												 paras.get("postalCode").toString(),
												 paras.get("role").toString()
					 );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = null;
		try {
			output = customerobj.deleteCustomer((int) paras.get("itemId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		response.getWriter().write(output);
	}
	
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
				 for (String param : params) 
				 { 
					 String[] p = param.split("=");
					 map.put(p[0], p[1]); 
				 } 
		} 
		catch (Exception e) 
		{ 
		 } 
			return map; 
	}
	 

}
