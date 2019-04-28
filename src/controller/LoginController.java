package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringLibrary;
import model.bean.User;
import model.dao.CatDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getServletContext().getRealPath(""));
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDao userDao = new UserDao();
		String username=request.getParameter("username");
		String password = util.StringLibrary.md5(request.getParameter("password"));
		
		User objUser = userDao.getItem(new User(0, username, password, "", "", 0, "", ""));
		
		if(objUser!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", objUser);
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return ; 
		}else{
			response.sendRedirect(request.getContextPath()+"/login?msg=coloi");
		}
		 
	}

}
