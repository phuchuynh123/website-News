package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CatDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
//@WebServlet("/AdminIndexCatController")
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CatDao catDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCatController() {
        super();
        catDao = new CatDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("listcat", catDao.getcatFather());
		RequestDispatcher rd =request.getRequestDispatcher("/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  response.setCharacterEncoding("UTF-8");
	  String tendanhmuc = request.getParameter("tendm");
	  int idparent = Integer.parseInt(request.getParameter("danhmuc"));
	  if(catDao.addcat(tendanhmuc,idparent)>0){
		  response.sendRedirect(request.getContextPath()+"/admin/cats?msg=1");
	  }else{
		  response.sendRedirect(request.getContextPath()+"/admin/cats?msg=0");
	  }
	}

}
