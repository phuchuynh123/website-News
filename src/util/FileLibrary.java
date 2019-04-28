package util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FileLibrary {
	public static String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	public static boolean checkexistsFile(HttpServletRequest request,HttpServletResponse response,String filename){
		String dirPath= request.getServletContext().getRealPath("/files");
		String filePath =dirPath+ File.separator +filename;
		File check = new File(filePath);
		if(!check.exists()){//nếu file không tồn tại 
			return false;
		}
		return true;
	}
}
