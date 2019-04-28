<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/admin/inc/header.jsp" %>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Quản trị viên</h4>
                                <a href="<%=request.getContextPath() %>/admin/user/add" class="addtop"><img src="<%=request.getContextPath() %>/templates/admin/assets/img/add.png" alt="" /> Thêm</a>
       <%
		if(request.getParameter("msg")!=null){
			int msg = Integer.parseInt(request.getParameter("msg"));
			switch(msg){
					case 0: { out.print("<h3 style='color: white; font-weight:bold;background-color:red;'>Có lỗi xảy ra, vui lòng kiểm tra lại</h3>");break;}
					case 1: { out.print("<h3 style='color: white; font-weight:bold;background-color:blue;'>Thêm user thành công!</h3>");break;}
					case 2: { out.print("<h3 style='color: white; font-weight:bold;background-color:blue;'>Sửa user thành công!</h3>");break;}
					case 3: { out.print("<h3 style='color: white; font-weight:bold;background-color:blue;'>Xóa user thành công!</h3>");break;}
					case 4: { out.print("<h3 style='color: white; font-weight:bold;background-color:red;'>Không cho xóa tài khoản admin!</h3>");break;}
					case 5: { out.print("<h3 style='color: white; font-weight:bold;background-color:blue;'>Chỉ admin mới đc quyền xóa!</h3>");break;}
					case 6: { out.print("<h3 style='color: white; font-weight:bold;background-color:blue;'>Không có quyền sửa thông tin tài khoản khác!</h3>");break;}
				}	    		
			}
		%>
                   
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Họ tên</th>
                                    	<th>Hình ảnh</th>
                                    	<th>Ngày tạo</th>
                                    	<th>Thuộc danh sách</th>
                                    	<th>Chức năng</th>
                                    </thead>
                                    <tbody>
                                        <%
                                          if(request.getAttribute("listUser")!=null){
                                        	ArrayList<User>listUser =(ArrayList<User>)request.getAttribute("listUser");
                                        	if(listUser.size()>0){
                                                   for(User item:listUser){
                                        %>
                                    
                                        <tr>
                                        	<td><%=item.getId() %></td>
                                        	<td><a href="<%=request.getContextPath()%>/admin/user/edit?uid=<%=item.getId()%>"><%=item.getUsername() %></a></td>
                                        	<td><%=item.getFullname() %></td>
                                        	<td><%=item.getEmail() %></td>
                                        	<td><%=item.getCapbac() %></td>
                                        	<td>
                                        		<a href="<%=request.getContextPath()%>/admin/user/edit?uid=<%=item.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp;
                                        		<a href="<%=request.getContextPath()%>/admin/user/del?uid=<%=item.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a>
                                        	</td>
                                        </tr>
                                        <%}}} %>
                                    </tbody>
 
                                </table>

								
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
  <%@include file="/templates/admin/inc/footer.jsp"%>