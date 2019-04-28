package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelCatController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
		   int idc=Integer.parseInt(request.getParameter("idc"));
	       CatDao catDao = new CatDao();
	       if(catDao.delCatByID(idc)>0){
	    	   response.sendRedirect(request.getContextPath()+"/admin/cats?msg=3");
	       }else{
	    	   response.sendRedirect(request.getContextPath()+"/admin/cats?msg=0");
	       }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
