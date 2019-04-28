package util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginLibrary {
	public static boolean CheckLogin(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo")==null) {
			try {
				response.sendRedirect(request.getContextPath()+"/login");
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static boolean CheckLogin1(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo")!=null) {
			try {
				response.sendRedirect(request.getContextPath()+"/login");
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	

}
