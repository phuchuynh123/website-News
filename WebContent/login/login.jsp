<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
	<!-- header -->
	
	<!-- Blog -->
	<div class="container"><div class="blog">
	    <div class="blog-content">
	    	<div class="blog-content-left login-right">
	     <%
	     	if(request.getParameter("msg")!=null){
	     		
	     			 out.print("<h3 style='color: white; font-weight:bolt;background-color:red;'>Tên đăng nhập hoặc mật khẩu không đúng</h3>");
	     	}
	     %>
	     
	<h1>Đăng nhập</h1>
	<form action="<%=request.getContextPath()%>/login" method="post" id="form-login">
	<input type="hidden" name="_token" value="Ms0M6s5U74PWyR480n07iWIHpdtVY3eTLJAyYQmc">
		<div>
			<label>Username*</label>
			<input type="text"  name="username" value="" placeholder="Tên đăng nhập"> 
		</div>
		<div>
			<label for="password">Password*</label>
			<input type="password" name="password" id="password" value="" placeholder="******"> 
		</div>
		<input type="submit" value="Đăng nhập">
	</form>
	        </div>
	 <br class="clear" />
	 


	