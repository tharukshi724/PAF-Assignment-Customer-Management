package EGSS.CustomerManagementService.servlet;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EGSS.CustomerManagementService.contoller.CustomerController;

/**
 * Servlet implementation class GeneratePwAPI
 */
@WebServlet("/GeneratePwAPI")
public class GeneratePwAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerController customerobj = new CustomerController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratePwAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String output= "";
			
			
				try {
					output = customerobj.insertAuthData( Integer.parseInt(request.getParameter("Uid")) ,
							  Integer.parseInt(request.getParameter("id")) );
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

}
