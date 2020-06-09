package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dao.Dao;
import models.Livre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private Dao Dao;
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		//jdbcURL = jdbcURL; //?characterEncoding=utf8
		Dao = new Dao(jdbcURL, jdbcUsername, jdbcPassword);
		//System.out.println("db connected");

	}
	public ControllerServlet() {
		super();

	}

	protected void getMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("in the getMainPage");
		List<Livre> LivresList = Dao.getAllLivres();
		request.setAttribute("LivresList", LivresList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	@SuppressWarnings("deprecation")
	
	protected void getCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("in the getCartPage");
	    String CodeLivre =request.getParameter("CodeLivre");
	    HttpSession session =request.getSession();
	    session.setAttribute(CodeLivre,CodeLivre);
	    List<Livre> Livres_list=new ArrayList<Livre>();
	    Enumeration keys = session.getAttributeNames();
	    while (keys.hasMoreElements())
	    {
	      String key = (String)keys.nextElement();
	      //System.out.println(key + ": " + session.getValue(key) + "<br>");
	      Livre livre = Dao.getLivrebyID((String) session.getValue(key));
	      Livres_list.add(livre);
	      
	    }

	    request.setAttribute("LivresList", Livres_list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
		dispatcher.forward(request, response);
	}

	//Supprimer livre mn session
	protected void SupprimerLivreSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("in the SupprimerLivreSession");
		 String CodeLivre =request.getParameter("supprimerLivreId");
		 HttpSession session =request.getSession();
		 session.removeAttribute(CodeLivre);
		
	}
	
	protected void getCartChario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cart_chario.jsp");
		dispatcher.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String action = request.getServletPath();
		System.out.println(action); 
		try {
			switch(action) {
			case "/" :
				getMainPage(request, response);
				break;
			case "/chario" :
				SupprimerLivreSession(request, response);
				break;
			case "/cart" :
				getCartChario(request, response);
				break;
			default:
				getMainPage(request, response);
				break;

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String action = request.getServletPath();
		System.out.println(action); 
		switch(action) {
		case "/cart" :
			getCartPage(request, response);
			break;
		default:
			getMainPage(request, response);
			break;

		}
	}



}
