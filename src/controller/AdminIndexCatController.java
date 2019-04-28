package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDao;
import util.CheckLoginLibrary;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatDao catDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCatController() {
        super();
        catDao = new CatDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLoginLibrary.CheckLogin(request, response)!=true) {//nếu đăng nhập rồi mới đc sử dụng trang.
			return;
		}
	   request.setCharacterEncoding("UTF-8");
	   request.setCharacterEncoding("UTF-8");
	   request.setAttribute("listCat", catDao.getcatFather());
	   RequestDispatcher rd =request.getRequestDispatcher("/admin/cat/cat.jsp");
	   rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
