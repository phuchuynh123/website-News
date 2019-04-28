package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class PublicCommentController
 */
//@WebServlet("/PublicCommentController")
public class PublicCommentConController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicCommentConController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/chitiet.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		NewsDao newsDao = new NewsDao();
		int nid = Integer.parseInt(request.getParameter("nid"));
		CommDao commentDao = new CommDao();
		String name = request.getParameter("hoten");
		String email = request.getParameter("email");
		String cmm = request.getParameter("cmm");
		
		Comment obj =  new Comment(0, name, "", email, "", cmm, 0, nid, 1);
		News objnews = newsDao.getItem(nid);
		commentDao.addItem(obj);
		request.setAttribute("objnews", objnews);
		ArrayList<Comment> listcmt = commentDao.getListcmt(nid);
		String result="";
		if(listcmt.size()>0) {
			for(Comment item:listcmt) {
				result += "<p style='color:#ff131d;font-size:17px;font-weight: bold'>"+item.getNameUser()+"</p>\r\n" + 
						"            <div class=\"strator-left\">\r\n" + 
						"                <img src=\""+request.getContextPath()+"/templates/public/images/demo/avatar.gif\" class=\"img-responsive\" alt=\"\">\r\n" + 
						"            </div>\r\n" + 
						"            <div class=\"strator-right\">\r\n" + 
						"                <p class=\"sin\">"+item.getContent()+"</p>\r\n" + 
						"            </div>\r\n" + 
						"            <div class=\"clearfix\"></div>\r\n" + 
						"            <div class=\"rep-"+item.getNews_id()+"\">\r\n" + 
						"                <a href=\"javascript:void(0)\" class=\"reply\" onclick=\"openform("+item.getId()+","+item.getNews_id()+","+1+")\">Trả lời</a>\r\n" + 
						"            </div>";
			}
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		
		request.setAttribute("listcmt", listcmt);
		
		
	}

}
