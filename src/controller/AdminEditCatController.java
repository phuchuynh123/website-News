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
public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatDao catDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditCatController() {
       catDao = new CatDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int idc=0;
		try{
			idc =Integer.parseInt(request.getParameter("idc"));
		}catch(Exception e){
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		if(catDao.getnameCatWithID(idc)==null){
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		request.setAttribute("namecat", catDao.getnameCatWithID(idc));
		//kiểm tra thư mục có con không co thi khong the sua
		if(catDao.checksonoffa(idc)==null){
			request.setAttribute("listcf", catDao.getcatFather());
		}
		System.out.println(idc);
		System.out.println(catDao.checksonoffa(idc));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int idc=Integer.parseInt(request.getParameter("idc"));
		String tendm =request.getParameter("tendm");
		int idparent = Integer.parseInt(request.getParameter("danhmuc"));
		if(catDao.updateCat(idc,tendm,idparent)>0){
			response.sendRedirect(request.getContextPath()+"/admin/cats?msg=2");
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/cats?msg=0");
		}
	}

}
