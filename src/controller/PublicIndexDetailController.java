package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.News;
import model.dao.CommDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexDetailController
 */
public class PublicIndexDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexDetailController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   NewsDao newsDao = new NewsDao();
	   CommDao commDao = new CommDao();
	   int did=Integer.parseInt(request.getParameter("did"));
	   News objnews = newsDao.getItem(did);
	   ArrayList<Comment>listcmt = commDao.getListcmt(did);
	   request.setAttribute("listcmt", listcmt);
	   request.setAttribute("objnews", objnews);
	   RequestDispatcher rd =request.getRequestDispatcher("/detail.jsp");
	   rd.forward(request, response);
	   
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
