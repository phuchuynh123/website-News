<%@page import="model.bean.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@include file="/templates/public/inc/header.jsp" %>
    <section id="contentbody">
      <div class="row">
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-12">
          <div class="row">
            <div class="left_bar">
              <div class="single_leftbar">
               <%@include file="/templates/public/inc/left_bar.jsp" %>
              </div>
              <div class="single_leftbar wow fadeInDown">
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-7 col-md-7 col-sm-8 col-xs-12">
          <div class="row">
            <div class="middle_bar">
            <%
               News objnews =null;
               if(request.getAttribute("objnews")!=null){
            	 objnews = (News)request.getAttribute("objnews");  
               
            %>
              <div class="single_post_area">
                <h2 class="post_title wow "><%=objnews.getName()%></h2>
                <a href="#" class="author_name"><i class="fa fa-user"></i></a> <a href="#" class="post_date"><i class="fa fa-clock-o"></i><%=objnews.getDate_create()%></a>
                <div class="single_post_content">
                  <img width="500px" height="500px" class="img-center" src="<%=request.getContextPath()%>/files/<%=objnews.getPicture()%>" alt="">
                  <p><%=objnews.getDetail()%></p>
                  </div>
                  
                <div class="strator">
          <%
  			if(request.getAttribute("listcmt")!=null){
  				ArrayList<Comment> listcmt = (ArrayList<Comment>)request.getAttribute("listcmt");
  				for(Comment item : listcmt){
  					if(item.getActive()==1){
  		%>
            <p style='color:#ff131d;font-size:17px;font-weight: bold'><%=item.getNameUser() %></p>
            <div class="strator-left">
                <img src="<%=request.getContextPath()%>/templates/public/images/demo/avatar.gif" class="img-responsive" alt="">
            </div>
            <div class="strator-right">
                <p class="sin"><%=item.getContent() %></p>
            </div>
            <div class="clearfix"></div>
            <div class="rep-<%=item.getId() %>">
                <a href="javascript:void(0)" class="reply" onclick="openform(<%=item.getId()%>,<%=objnews.getId()%>,1)">Trả lời</a>
            </div>
            
            <%}}} %>
          </div>
                                                     
  <div class="comment">
      <h2>Viết bình luận</h2>
      <form action="javascript:void(0)" class="contact_form" method="post" id="comment">
          <input type="text" name="hoten" id="hoten" class="textbox" placeholder="Nhập họ tên"style='height: 40px;padding-top: 15px;'>
          <input type="text" name="email" id="email" class="textbox" placeholder="Nhập email"style='height: 40px;padding-top: 15px;'><br />
          <textarea name="cmm" id="cmm" rows="20" cols="47" placeholder="Nội dung bình luận"style='mamargin-right: 20px;'></textarea><br />
          <% if(request.getAttribute("objnews")!=null){
				objnews = (News)request.getAttribute("objnews");
			%>
          <input type="submit" value="Gửi" onclick="listcomment()">
          <%} %>
          <input type="reset" value="Nhập lại" style="background-color: #fc8010;color:white; padding: 7px;">
      </form>
  </div>
  
  
 <script type="text/javascript">
  function openform(id_cmt, idNews, tt){

    $.ajaxSetup({
      headers: {
        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
      }
    });
    $.ajax({
      url: "<%=request.getContextPath()%>/public/cmtcon",
      type: 'POST',
      cache: false,
      data: {
    	  aidCmm : id_cmt,
    	  aidNews: idNews,
    	  att: tt
      },
      success: function(data){
        $('.rep-'+id_cmt).html(data);
      },
      error: function (){
        alert('Có lỗi');
      }
    });  
  }
  
  function listcomment(){
	  var hoten =$('#hoten').val();
	  var email =$('#email').val();
	  var cmm =$('#cmm').val();
	  var nid =<%=objnews.getId()%>
	    $.ajaxSetup({
	      headers: {
	        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
	      }
	    });
	    $.ajax({
	      url: "<%=request.getContextPath()%>/comment",
	      type: 'POST',
	      cache: false,
	      data: {
	    	  hoten : hoten,
	    	  email: email,
	    	  cmm: cmm,
	    	  nid: nid,
	      },
	      success: function(data){
	    	
	        $('.strator').html(data);
	      },
	      error: function (){
	        alert('Có lỗi');
	      }
	    });  
	  }
</script>
                  <%} %>
                <div class="post_footer">
                  <ul class="post_pager">
                  </ul>
                </div>
                <div class="social_area wow fadeInLeft">
                  <ul>
                    
                  </ul>
                </div>
                <div class="related_post">
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
          <div class="row">
            <div class="right_bar">
              <%@include file="/templates/public/inc/right_bar.jsp" %>
           </div>
          </div>
        </div>
      </div>
    </section>
	<%@include file="/templates/public/inc/footer.jsp" %>
</body>
</html>