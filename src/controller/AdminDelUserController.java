package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDao;
import util.CheckLoginLibrary;

/**
 * Servlet implementation class AdminIndexUserController
 */
public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelUserController() {
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
		HttpSession session =request.getSession();
		User userInfo =(User)session.getAttribute("userInfo");
		UserDao userDao = new UserDao();
		int uid = Integer.parseInt(request.getParameter("uid"));
		User objUser =userDao.getItem(uid);
		if("admin".equals(objUser.getUsername())){
			//khong cho xoa tk admin
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=4");
			
		}else{
			if("admin".equals(userInfo.getUsername())){
				//admin moi dc quyen xoa
				if(userDao.delUser(uid)>0){
					response.sendRedirect(request.getContextPath()+"/admin/user?msg=3");
					return;
				}else{
					response.sendRedirect(request.getContextPath()+"/admin/user?msg=0");
					return;
				}
			}else{
				//admin mới đc quyền xóa
				response.sendRedirect(request.getContextPath()+"/admin/user?msg=5");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
