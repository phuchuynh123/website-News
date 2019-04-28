package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDao;
import util.CheckLoginLibrary;
import util.StringLibrary;

/**
 * Servlet implementation class AdminIndexUserController
 */
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLoginLibrary.CheckLogin(request, response)!=true) {//nếu đăng nhập rồi mới đc sử dụng trang.
			return;
		}
		request.setAttribute("id", 4);
		HttpSession sesion =request.getSession();
		UserDao userDao = new UserDao();
		int uid = Integer.parseInt(request.getParameter("uid"));
		User userInfo = (User)sesion.getAttribute("userInfo");
		if("admin".equals(userInfo.getUsername())||uid==userInfo.getId()){
			User objUser = userDao.getItem(uid);
			request.setAttribute("objUser", objUser);
			RequestDispatcher rd =request.getRequestDispatcher("/admin/user/edituser.jsp");
			rd.forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=6");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDao userDao = new UserDao();
		int uid=Integer.parseInt(request.getParameter("uid"));
		String fullname =request.getParameter("fullname");
		//lay value the select cap bac
		int temp = Integer.parseInt(request.getParameter("capbac"));
		String capbac="";
		switch (temp) {
		case 0:
			capbac="Admin";
			break;
		case 1:
			capbac="Mod";
			break;
		case 2:
			capbac="Trial Mod";
			break;
		}
		//lay password
		String password =request.getParameter("password");
		if("".equals(password)){
			//lay password cu
			password =userDao.getItem(uid).getPassword();
			
		}else{
			//cap nhap mat khau moi
			password =StringLibrary.md5(password);
		}
		//lay email
		String email =request.getParameter("email");
		User obj = new User(uid, "", password, fullname, "", 0, email, capbac);
		if(userDao.eidtUser(obj)>0){
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=2");
			return;
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=0");
			return;
		}
	}

}
