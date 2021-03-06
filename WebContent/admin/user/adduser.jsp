<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/templates/admin/inc/header.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		$('#addUser').validate({
			rules:{
				username:{
					required: true
				},
				fullname:{
					required: true
				},
				password:{
					required: true,
					minlenght: 3
				},
				email:{
					required: true,
					email : true
				},
				repassword:{
					equalTo: "#password"
				},
				reemail:{
					equalTo: "#email"
				}
					
				
			},
			messages:{
				username:{
					required: "<h5 style='color:red'>username không được để trống!</h5>"
				},
				fullname:{
					required: "<h5 style='color:red'>fullname không được để trống!</h5>"
				},
				password:{
					required: "<h5 style='color:red'>password được để trống!</h5>",
					minlenght: "<h5 style='color:red'>Tối thiểu 3 kí tự!</h5>"
				},
				email:{
					required: "<h5 style='color:red'>email được để trống!</h5>",
					email : "<h5 style='color:red'>Phải đúng định dạng email!</h5>"
				},
				repassword:{
					equalTo: "<h5 style='color:red'>Không khớp mật khẩu!</h5>"
				},
				reemail:{
					equalTo: "<h5 style='color:red'>Không khớp email!</h5>"
				}
			}
			
		});
		
	});
</script>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="card">
    <div class="header">
        <h4 class="title">Thêm quản trị viên</h4>
        <%if(request.getParameter("msg")!=null){
        	out.print("<h4 style='color:red'>username đã tồn tại vui lòng chọn username khác!</h4>");
        }
        %>
    </div>
    <div class="content">
                <form action="<%=request.getContextPath() %>/admin/user/add" method="post" id="addUser">
        <input type="hidden" name="_token" value="RIGysKBe4fTBxOkghTveNXfQKKLPAF2KKM8VNXcO">
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Tên đăng nhập (*)</label>
                        <input type="text" name="username" value="" class="form-control border-input" placeholder="abcxyz">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="fullname">Nhập tên đầy đủ</label>
                        <input type="text" class="form-control border-input" name="fullname" id="fullname" placeholder="nhập fullname">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="capbac">Cấp bậc</label>
                          <select class="form-control border-input" name="capbac" id="capbac" style="width: 100%;">
                            <option value="1">Mod</option>
                            <option value="2">Trial Mod</option>
                          </select>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="password">Nhập mật khẩu</label>
                        <input type="password" class="form-control border-input" name="password" id="password" placeholder="******">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="password">Nhập lại mật khẩu</label>
                        <input type="password" class="form-control border-input" name="repassword" id="repassword" placeholder="******">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="email">Nhập email</label>
                        <input type="email" class="form-control border-input" name="email" id="email" placeholder="abcxyz@gmail.com">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="reemail">Nhập lại email</label>
                        <input type="email" class="form-control border-input" name="reemail" id="reemail" placeholder="abcxyz@gmail.com">
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

<%@include file="/templates/admin/inc/footer.jsp" %>