<%@page import="model.bean.Category"%>
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
                                <h4 class="title">Thêm danh mục</h4>
                            </div>
                            <div class="content">
                                <form class="form" action="<%=request.getContextPath()%>/admin/cats/add" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Tên danh mục</label>
                                                <input type="text" name="tendm" class="form-control border-input" placeholder="Tên danh mục" value="">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Thuộc danh mục cha</label>
                                                <select name="danhmuc" class="form-control border-input">
                                                	<option value="0">Không thuộc</option>
                                                	<%
                                                	  if(request.getAttribute("listcat")!=null){
                                                		  ArrayList<Category>listcat =(ArrayList<Category>)request.getAttribute("listcat");
                                                		  if(listcat.size()>0){
                                                	      for(Category obj:listcat){
                                                	%>
                                                	<option value="<%=obj.getId()%>"><%=obj.getName()%></option>
                                                	<%}} }%>
                                                </select>
                                            </div>
                                        </div>
                                    </div>                                    
                                    <div class="text-center">
                                        <input type="submit" class="btn btn-info btn-fill btn-wd" value="Thực hiện" />
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>


       <%@include file="/templates/admin/inc/footer.jsp"%>