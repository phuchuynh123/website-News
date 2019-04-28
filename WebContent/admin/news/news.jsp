<%@page import="util.FileLibrary"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="card">
    <div class="header">
        <h4 class="title">Bài viết</h4>
        <a href="<%=request.getContextPath()%>/admin/news/add" class="addtop"><img src="<%=request.getContextPath() %>/templates/admin/assets/img/add.png" alt="" /> Thêm</a>
    </div>
     <%
    	if(request.getParameter("msg")!=null){
    		int msg = Integer.parseInt(request.getParameter("msg"));
    		switch(msg){
    			case 0: { out.print("<h3 style='color: white; font-weight:bolt;background-color:red;'>Có lỗi xảy ra, vui lòng kiểm tra lại!</h3>");break;}
    			case 1: { out.print("<h3 style='color: white; font-weight:bolt;background-color:green;'>Thêm tin tức thành công!</h3>");break;}
    			case 2: { out.print("<h3 style='color: white; font-weight:bolt;background-color:green;'>Sửa tin tức thành công!</h3>");break;}
    			case 3: { out.print("<h3 style='color: white; font-weight:bolt;background-color:green;'>Xóa tin tức thành công!</h3>");break;}
    		}
    	}
    %>
    <div class="content table-responsive table-full-width">
        <table class="table table-striped">
            <thead>
                <th>ID</th>
                <th>Tên bài viết</th>
                <th>Hình ảnh</th>
                <th>Ngày tạo</th>
                <th>Danh mục</th>
                <th>Chức năng</th>
            </thead>
            <tbody>
            <%
            	if(request.getAttribute("listNews")!=null){
            		ArrayList<News> listNews = (ArrayList<News>)request.getAttribute("listNews");
            	    if(listNews.size()>0){
            	    	for(News item:listNews){
            %>
                  <tr>
                    <td><%=item.getId() %></td>
                    <td>
                    	<a href="<%=request.getContextPath() %>/admin/news/edit?nid=<%=item.getId()%>" title="Xem bài viết" target="_blank"><%=item.getName()%></a>
                    </td>
                    <td>
                    	<%
                    		String hinh = "";
                    		if(FileLibrary.checkexistsFile(request, response, item.getPicture())==true||item.getPicture().equals("")){
                    			hinh=request.getContextPath()+"/files/"+item.getPicture();
                    		}else{
                    			hinh=request.getContextPath()+"/templates/noimage/noimage.jpg";
                    		}
                    			
                    	%>
                    	<img src="<%=hinh%>" alt="" width="100px" />
                    </td>
                    <td><%=item.getDate_create()%></td>
                    <td><%=item.getCname()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/admin/news/edit?nid=<%=item.getId()%>"><img src="<%=request.getContextPath() %>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                        <a href="<%=request.getContextPath()%>/admin/news/del?nid=<%=item.getId()%>"><img src="<%=request.getContextPath() %>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a>
                    </td>
                </tr>
                <%}}} %>
            </tbody>

        </table>

        <div class="text-center">
           <ul class="pagination">
        			<%
        				int sumpage = (Integer)request.getAttribute("sumpage");
        				int current_page = (Integer)request.getAttribute("current_page");
        				String active = "";
        			%>
        			
                    <li <%if(current_page==1){ %> class="disabled" <%} %>><a href="<%=request.getContextPath() %>/admin/news?page=<%=current_page-1%>"  rel="next">&laquo;</a></li>
                    	<% 
                    		for(int i=1;i<=sumpage;i++){
                    			if(current_page==i){
                    				active = "class='active'";
                    			}else{
                    				active = "";
                    			}
                    	%>
                                 <li <%=active %>><a href="<%=request.getContextPath()%>/admin/news?page=<%=i%>"><%=i%></a></li>
                        <%} %>                               
                    <li <%if(current_page==sumpage){ %> class="disabled" <%} %>><a href="<%=request.getContextPath() %>/admin/news?page=<%=current_page+1%>"  rel="next">&raquo;</a></li>
            </ul>

        </div>
    </div>
</div>
 
            </div>
        </div>
    </div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>