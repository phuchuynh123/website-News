package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constant.Define;
import model.bean.Comment;
import model.dao.CommDao;
import util.CheckLoginLibrary;

/**
 * Servlet implementation class AdminIndexCommentController
 */
public class AdminIndexCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCommentController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		CommDao commDao = new CommDao();
		int sumNews =commDao.countNews();
		System.out.println(sumNews);
		int sumpage = (int)Math.ceil((float)sumNews/Define.ROW_COUNT_ADMIN);

		int curent_page = 1;
		if(request.getParameter("page")!=null) {
			//lấy trang hiện tại.
			curent_page = Integer.parseInt(request.getParameter("page"));
		}
		int offset = (curent_page - 1)*Define.ROW_COUNT_ADMIN;
		ArrayList<Comment> listCmt = commDao.getItemsPagination(offset);
		request.setAttribute("listCmt", listCmt);
		request.setAttribute("curent_page", curent_page);
		request.setAttribute("sumpage", sumpage);
		request.setAttribute("id", 3);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/Comment.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
