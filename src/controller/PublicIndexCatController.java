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

/**
 * Servlet implementation class PublicIndexCatController
 */
public class PublicIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexCatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   NewsDao newsDao = new NewsDao();
	   CatDao catDao = new CatDao();
	   int cid =Integer.parseInt(request.getParameter("cid"));
	   int currentPage =1;
	   if(request.getParameter("page")!=null){
		   currentPage =Integer.parseInt(request.getParameter("page"));
	   }
	   int sumNews =newsDao.countNewsbyCID(cid);
	   int sumPage =(int)Math.ceil((float)sumNews/Define.ROW_COUNT_ADMIN);
	   request.setAttribute("sumPage", sumPage);
	   int offset =(currentPage-1)*Define.ROW_COUNT_ADMIN;
	   request.setAttribute("listCat", newsDao.getItembyCIDPagination(offset,cid));
	   request.setAttribute("nameCat",catDao.getnamecat(cid) );
	   request.setAttribute("currentPage", currentPage);
	   RequestDispatcher rd =request.getRequestDispatcher("/cat.jsp");
	   rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
