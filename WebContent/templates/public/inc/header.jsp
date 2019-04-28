<%@page import="util.StringLibrary"%>
<%@page import="model.bean.News"%>
<%@page import="model.dao.CatDao"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SybarMagazine</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/assets/css/animate.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/assets/css/li-scroller.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/assets/css/slick.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/assets/css/theme.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/assets/css/style.css">
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/templates/public/assets/js/html5shiv.min.js"></script>
<script src="<%=request.getContextPath()%>/templates/public/assets/js/respond.min.js"></script>
<![endif]-->
<style>
	#con{
		position: absolute;
		display:none;
	}
	.a2{
		position: relative;
	}
	.a2:hover #con{
		display:block;
	}
	#con li:hover{
	background-color: #09c;
	
	}
	#con li:hover a{
	color:white;
	
	}
	#con li{
		height:40px;text-align: center;
		
	}
	#con li a{
		margin-left: 20px;
		margin-right: 20px;
		text-decoration: none;
	}
</style>
</head>
<body>
<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
<div class="container">
  <div class="box_wrapper">
    <header id="header">
      <div class="header_top">
        <nav class="navbar navbar-default" role="navigation">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
           
              <ul class="nav navbar-nav custom_nav">
                <li><a href="<%=request.getContextPath()%>/index">Home</a></li>
                 
            <%
            	CatDao catDao = new CatDao();
            	ArrayList<Category> listCat = catDao.getcatFather();
            	if(listCat.size()>0){
            		for(Category objc: listCat){
            			int id = objc.getId();
               			String slug = StringLibrary.makeSlug(objc.getName());
               			String url = request.getContextPath() + "/" + slug + "-" + id;

            	
            %>
                <li class="a2"><a href="<%=url%>"><%=objc.getName()%></a>
                <%
                ArrayList<Category> listSon = catDao.getcatParent(objc.getId());
    			if(listSon.size()>0){
                %>
                <ul id="con">
                		<%
                			for(Category objs: listSon){
                				
                		%>
                		<li><a href="<%=request.getContextPath()%>/cat?cid=<%=objs.getId()%>"><%=objs.getName()%></a></li>
                		<%} %>
                	</ul>
              
                <%}%>
                </li>
                <%}} %>
              </ul>
            </div>
          </div>
        </nav>
        <div class="header_search">
          <button id="searchIcon"><i class="fa fa-search"></i></button>
          <div id="shide">
            <div id="search-hide">
              <form action="<%=request.getContextPath()%>/search" method="post">
                <input type="text" name="name" size="40" placeholder="Search here ...">
                <input type="submit" value="Tìm kiếm" style="color:red; font-size:25px;font-weight:bold">
              </form>
              <button class="remove"><span><i class="fa fa-times"></i></span></button>
            </div>
          </div>
        </div>
      </div>
      <div class="header_bottom">
        <div class="logo_area"><a class="logo" href="#"><b>S</b>ybarMagazine <span>A Pro Magazine Theme</span></a></div>
        <div class="top_addarea"><a href="#"><img src="<%=request.getContextPath()%>/templates/public/images/addbanner_728x90_V1.jpg" alt=""></a></div>
      </div>
    </header>
    <div class="latest_newsarea"> <span>Latest News</span>
      <ul id="ticker01" class="news_sticker">
      <%
       if(request.getAttribute("list")!=null){
    	   ArrayList<News>ar =(ArrayList)request.getAttribute("list");
    	   for(News obj:ar){
       
      %>
        <li><a href="#"><%=obj.getName()%></a></li>
        <%}} %>
      </ul>
    </div>
