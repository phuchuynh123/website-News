<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$('#form').validate({
			rules:{
				'name':{
					required:true,
				},
				'preview':{
					required:true,
				},
				'detail':{
					required:true,
				}
				
			},
			messages:{
				'name':{
					required:"Bạn phải nhập trường này!",
				},
				'preview':{
					required:"Bạn phải nhập trường này!",
				},
				'detail':{
					required:"Bạn phải nhập trường này!",
				}
				
			},
			ignore: []
			
		});		
	});
	 
	</script>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="card">
    <div class="header">
        <h4 class="title">Thêm bài viết</h4>
    </div>
    <div class="content">
         <form id="form" name="form" action="<%=request.getContextPath() %>/admin/news/add" method="post" enctype="multipart/form-data" >
        <input type="hidden" name="_token" value="pvMvrtK4NIly1HFWQnyVhVVTTizDEHZKusQRNho7">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Tên bài viết</label>
                        <input type="text" name="name" class="form-control border-input" placeholder="Nhập tên bài viết" value="">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Danh mục</label>
                        <select name="category" class="form-control border-input">
                        <%
                        	if(request.getAttribute("listCat")!=null){
                        		ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
                        		for(Category item1:listCat){
                        			if(item1.getParent_id()==0){
                        %>
                                 <option value="<%=item1.getId()%>"><%=item1.getName()%></option>
                                 <%
                                 	for(Category item2:listCat){
                                 		if(item2.getParent_id()==item1.getId()){
                                 %>
                                 <option value="<%=item2.getId()%>">|----<%=item2.getName()%></option>
                         <%}}}}} %>
                         </select>
                    </div>
                </div>
                <div class="col-md-2">
                  <label for="is_slide">Slide</label>
                  <p><input type="checkbox" id="is_slide" name="is_slide" value="1">Chọn</p>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>Hình ảnh</label>
                        <input type="file" name="picture"  class="form-control" placeholder="Chọn ảnh" />
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>Mô tả</label>
                        <textarea name="preview" rows="4" class="form-control border-input" placeholder="Mô tả bài viết"></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>Chi tiết</label>
                        <br>
                        <textarea name="detail" rows="6" class="form-control border-input ckeditor" placeholder="Nội dung bài viết"></textarea>
                     <script type="text/javascript">
						var editor = CKEDITOR.replace( 'detail');
						CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/library/ckfinder/');
					</script>
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

<!-- CK Editor -->
            </div>
        </div>
    </div>
</div>
<%@include file="/templates/admin/inc/footer.jsp" %>