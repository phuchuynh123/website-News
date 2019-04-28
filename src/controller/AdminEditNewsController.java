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
import util.CheckLoginLibrary;
import util.FileLibrary;

/**
 * Servlet implementation class AdminIndexNewsController
 */
@MultipartConfig 
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditNewsController() {
        super();
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
		CatDao catDao = new CatDao();
		int nid = Integer.parseInt(request.getParameter("nid"));
		System.out.println("nid"+nid);
		News objNews = newsDao.getItem(nid);
		request.setAttribute("listCat", catDao.getItems());
		request.setAttribute("objNews", objNews);
		request.setAttribute("id", 2);
		RequestDispatcher rd =request.getRequestDispatcher("/admin/news/editnews.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int nid = Integer.parseInt(request.getParameter("nid"));
		NewsDao newsDao = new NewsDao();
		String name =request.getParameter("name");
		int id_cat = Integer.parseInt(request.getParameter("cat_id"));
		int is_slide = 0;
		try {
			is_slide = Integer.parseInt(request.getParameter("is_slide"));
		} catch (NumberFormatException e) {
			is_slide = 0 ;
		}
		String mota = request.getParameter("preview");
		String chitiet = request.getParameter("detail");
		
		int delete_picture = 0;
		try {
			delete_picture = Integer.parseInt(request.getParameter("delete_picture"));
		} catch (NumberFormatException e) {
			delete_picture = 0 ;
		}
		System.out.println(delete_picture);
		if(delete_picture==1) {
			//xóa ảnh cũ.
			String filePathDelete = request.getServletContext().getRealPath("/files")+File.separator+newsDao.getItem(nid).getPicture();
			File file = new File(filePathDelete);
			file.delete();
		}
		Part part = request.getPart("picture");
		String fileName = FileLibrary.getFileName(part);
		if(!fileName.isEmpty()) {
			String dirPath = request.getServletContext().getRealPath("/files");
			File CreateDir = new File(dirPath);
			if(!CreateDir.exists()) {
				CreateDir.mkdir();
			}
			if(!"".equals(newsDao.getItem(nid).getPicture())) {
				//xóa ảnh cũ.
				String filePathDelete = request.getServletContext().getRealPath("/files")+File.separator+newsDao.getItem(nid).getPicture();
				File file = new File(filePathDelete);
				file.delete();
			}
			String filePath = dirPath+File.separator+fileName;
			part.write(filePath);
			System.out.println("Đường dẫn file:"+filePath);
		}else {
			fileName = newsDao.getItem(nid).getPicture();
		}
		Date date = new Date();
		 Timestamp create_at = new Timestamp(date.getTime());
		News obj = new News(nid, name, mota, chitiet,create_at, 0, fileName, id_cat, is_slide, 0, "");
		if(newsDao.editItem(obj)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=2");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=0");
			return;
		}
	}

}
