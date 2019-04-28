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
import model.bean.News;
import model.dao.CatDao;
import model.dao.NewsDao;
import util.CheckLoginLibrary;

/**
 * Servlet implementation class AdminIndexNewsController
 */
public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexNewsController() {
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
		NewsDao newsDao = new NewsDao();
		int sumNews = newsDao.countNews();
		int sumpage = (int)Math.ceil((float)sumNews/Define.ROW_COUNT_ADMIN);
		int current_page =1;
		if(request.getParameter("page")!=null){
			current_page =Integer.parseInt(request.getParameter("page"));
		}
		int offset =(current_page-1)*Define.ROW_COUNT_ADMIN;
		request.setAttribute("sumpage", sumpage);
		request.setAttribute("current_page", current_page);
		//gui list co phan trang 
		ArrayList<News>listNews =newsDao.getItemsPagination(offset);
		request.setAttribute("listNews", listNews);
		request.setAttribute("id", 2);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/news.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
