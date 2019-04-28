package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.News;
import model.dao.CatDao;
import model.dao.NewsDao;
import util.FileLibrary;

/**
 * Servlet implementation class AdminIndexNewsController
 */
@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public AdminAddNewsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		CatDao catDao =new CatDao();
		request.setAttribute("listCat", catDao.getItems());
		request.setAttribute("id", 2);
		RequestDispatcher rd =request.getRequestDispatcher("/admin/news/addnews.jsp");
		 rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		NewsDao newsDao = new NewsDao();
		String name = request.getParameter("name");
		int id_cat = Integer.parseInt(request.getParameter("category"));
		int is_slide = 0;
		try {
			is_slide = Integer.parseInt(request.getParameter("is_slide"));
		} catch (NumberFormatException e) {
			is_slide = 0 ;
		}
		String mota = request.getParameter("preview");
		String chitiet = request.getParameter("detail");
		
		Part part = request.getPart("picture");
		String fileName = FileLibrary.getFileName(part);
		
		if(!fileName.isEmpty()) {
			String dirPath = request.getServletContext().getRealPath("/files");
			File CreateDir = new File(dirPath);
			if(!CreateDir.exists()) {
				CreateDir.mkdir();
			}
			String filePath = dirPath+File.separator+fileName;
			part.write(filePath);
			System.out.println("Đường dẫn file:"+filePath);
		}
		Date date = new Date();
	    Timestamp create_at = new Timestamp(date.getTime());
		News obj = new News(0, name, mota, chitiet,create_at , 0, fileName, id_cat, is_slide, 0,"");
		if(newsDao.addItem(obj)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=1");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=0");
			return;
		}
	}
	

}
