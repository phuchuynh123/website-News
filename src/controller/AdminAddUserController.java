package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDao;
import util.StringLibrary;

/**
 * Servlet implementation class AdminIndexUserController
 */
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddUserController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("id", 4);
		RequestDispatcher rd =request.getRequestDispatcher("/admin/user/adduser.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDao userDao = new UserDao();
		String username = request.getParameter("username");
		String fullname =request.getParameter("fullname");
		int tempcapbac = Integer.parseInt(request.getParameter("capbac"));
		String capbac="";
		switch(tempcapbac){
		case 1:
			capbac ="Mod";break;
		case 2:
			capbac ="Trial Mod";break;
		}
		String password = StringLibrary.md5(request.getParameter("password"));
		String email = request.getParameter("email");
		
		User obj = new User(0, username, password, fullname, "", 0, email, capbac);
		//kiểm tra username cso tồn tại hay ko.
		
		if(userDao.checkUserName(username)!=null) {
			response.sendRedirect(request.getContextPath()+"/admin/user/add?msg=4");
			return;
		}else {
			//cho phép thêm người dùng
			if(userDao.addItem(obj)>0) {
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=0");
				return;
			}
		}
		
		
	}

}

