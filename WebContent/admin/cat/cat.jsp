<%@page import="model.dao.CatDao"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/admin/inc/header.jsp" %>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Danh sách danh mục</h4>
                                 <%
                                if(request.getParameter("msg")!=null){
                                	int msg = Integer.parseInt(request.getParameter("msg"));
                                	switch(msg){
                                	case 0: out.println("<p class='category success'>Thao tác thất bại</p>"); break;
                                	case 1: out.println("<p class='category success'>Thêm Thành công</p>");break;
                                	case 2: out.println("<p class='category success'>Sửa thành công</p>");break;
                                	case 3: out.println("<p class='category success'>Xóa thành công</p>"); break;
                               
                                	}
                                }
                                %>
                                <a href="<%=request.getContextPath()%>/admin/cats/add" class="addtop"><img src="<%=request.getContextPath()%>/templates/admin/assets/img/add.png" alt="" /> Thêm</a>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Tên danh mục</th>
                                    	<th>Chức năng</th>
                                    </thead>
                                    <tbody>
                                        <% 
                                        CatDao catDao =  new CatDao();
                                          if(request.getAttribute("listCat")!=null){
                                        	  ArrayList<Category>listCat =( ArrayList<Category>)request.getAttribute("listCat");
                                              if(listCat.size()>0){
                                            	    for(Category obj:listCat){
                                            	    	if(obj.getParent_id()==0){
                                        %>
                                        <tr>
                                        	<td><%=obj.getId()%></td>
                                        	<td><a href="<%=request.getContextPath()%>/admin/cats/edit?idc=<%=obj.getId()%>"><%=obj.getName() %></a></td>
                       
                                        	<td>
                                        		<a href="<%=request.getContextPath()%>/admin/cats/edit?idc=<%=obj.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                                        		<a href="<%=request.getContextPath()%>/admin/cats/del?idc=<%=obj.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a>
                                        	</td>
                                        </tr>
                                        <%}
                                            	    	ArrayList<Category>arParent =catDao.getcatParent(obj.getId());
                                            	    	for(Category objparent:arParent){
                                        %>
                                        <tr>
    
                                        	<td><a href=""><%=objparent.getId()%></a></td>
                                        	<td><a style="font-size:12px" href="<%=request.getContextPath()%>/admin/cats/edit?idc=<%=objparent.getId()%>">|---><%=objparent.getName()%></a></td>  
                                        	<td>
                                        		<a href="<%=request.getContextPath()%>/admin/cats/edit?idc=<%=objparent.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                                        		<a href="<%=request.getContextPath()%>/admin/cats/del?idc=<%=objparent.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a>
                                        	</td>
                                        </tr>
                                        <%}}}} %>
                                    </tbody>
 
                                </table>

								
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
  <%@include file="/templates/admin/inc/footer.jsp"%>
