<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@include file="/templates/admin/inc/header.jsp" %>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Sửa thông tin</h4>
                            </div>
                            <div class="content">
                            <%
                               Category objc =(Category) request.getAttribute("namecat");
                            %>
                                <form class="form" action="<%=request.getContextPath()%>/admin/cats/edit?idc=<%=objc.getId()%>" method="post">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Tên danh mục </label>
                                                <input type="text" name="tendm" class="form-control border-input" value="<%=objc.getName()%>">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Thuộc danh mục cha</label>
                                                <select name="danhmuc" class="form-control border-input">
                                                	<option value="0">không thuộc</option>
                                                	<%
                                                	if(request.getAttribute("listcf")!=null){
                                                		ArrayList<Category>ar =(ArrayList<Category>) request.getAttribute("listcf");
                                                		if(ar.size()>0){
                                                			for(Category obj:ar){
                                                              if(objc.getId()!=obj.getId()){
                                                	             
                                                	%>
                                                	<option value=<%=obj.getId()%>><%=obj.getName()%></option>
                                                	<%}}} }%>
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