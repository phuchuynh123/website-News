<%@page import="model.bean.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="card">
    <div class="header">
        <h4 class="title">Bình luận</h4>
    </div>
    <%
    	if(request.getParameter("msg")!=null){
    		int msg = Integer.parseInt(request.getParameter("msg"));
    		switch(msg){
    			case 0: { out.print("<h3 style='color: white; font-weight:bolt;background-color:red;'>Có lỗi xảy ra, vui lòng kiểm tra lại!</h3>");break;}
    			case 1: { out.print("<h3 style='color: white; font-weight:bolt;background-color:green;'>Xóa comment thành công!</h3>");break;}
    		}
    	}
    %>
    <div class="content table-responsive table-full-width">
        <table class="table table-striped">
            <thead>
                <th>ID</th>
                <th>Tên bài viết</th>
                <th>Họ và tên</th>
                <th>Email</th>
                <th>Website</th>
                <th>Bình luận</th>
                <th>Trạng thái</th>
                <th>Chức năng</th>
            </thead>
            <tbody>
            	<%
            	if(request.getAttribute("listCmt")!=null){
            		ArrayList<Comment> listCmt = (ArrayList<Comment>)request.getAttribute("listCmt");
            		for(Comment item:listCmt){
            	%>
                 <tr>
                    <td><%=item.getId() %></td>
                    <td>
                    	<a href="#" target="_blank"><%=item.getNameNews() %></a>
                    </td>
                    <td><%=item.getNameUser()%></td>
                    <td><%=item.getEmail()%></td>
                    <td><%=item.getWebsite()%></td>
                    <td><%=item.getContent()%></td>
                    <td class="change-<%=item.getId()%>"> 
                        <a class="img-<%=item.getId() %>" href="javascript:void(0)" onclick="return changeActive(<%=item.getId()%>, <%=item.getActive()%>);">
                         	<%if(item.getActive()==0){ %>
                          <img src="<%=request.getContextPath() %>/templates/admin/assets/img/disactive.png" width="20px">
                          <%}else{ %>
                          <img src="<%=request.getContextPath() %>/templates/admin/assets/img/active.png" width="20px">
                          <%} %>
                        </a>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath() %>/admin/comment/del?cmtid=<%=item.getId()%>"><img src="<%=request.getContextPath() %>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a>
                    </td>
                </tr>
                 <%}} %>
           </tbody>

        </table>

        <div class="text-center">
            <ul class="pagination">
        		<% 
        			int sumpage = (Integer) request.getAttribute("sumpage");
        			int curent_page = (Integer) request.getAttribute("curent_page");
        			String active="";
        		%>
                    <li <%if(curent_page==1){ %> class="disabled" <%} %>><a href="<%=request.getContextPath() %>/admin/comment?page=<%=curent_page-1%>"  rel="next">&laquo;</a></li>
                   <%
                      for(int i=1;i<=sumpage;i++){ 
                    	  if(curent_page == i){
                    		  active = "class='active'";
                    	  }else{
                    		  active = "";
                    	  }
                   %>
                     	<li <%=active %>><a href="<%=request.getContextPath()%>/admin/comment?page=<%=i%>"><%=i %></a></li>
                    <%} %>                                     
                    <li <%if(curent_page==sumpage){ %> class="disabled" <%} %>><a href="<%=request.getContextPath() %>/admin/comment?page=<%=curent_page+1%>"  rel="next">&raquo;</a></li>
            </ul>

        </div>
    </div>
</div>
<script type="text/javascript">
    function confirmAction(){
      return confirm('Bạn có chắc muốn xóa?');
    }
</script>

<script type="text/javascript">
  function changeActive(id, act){
	  
    $.ajaxSetup({
    headers: {
      'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
    }
  });
  
    $.ajax({
      url: "<%=request.getContextPath()%>/admin/ajaxcomment",
      type: 'POST',
      cache: false,
      data: {
        id: id,
        act : act,
      },

      success: function(data){
         
    	  $('.change-'+id).html(data);
      },
      error: function (){
        alert('Có lỗi');
      }
    }); 
  }

</script>
            </div>
        </div>
    </div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>