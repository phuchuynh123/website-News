<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="/templates/public/inc/header.jsp"%>
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
              <div class="featured_sliderarea">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                  <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                  </ol>
                  <div class="carousel-inner" role="listbox">
                  	<%
                  		if(request.getAttribute("list")!=null){
                  			ArrayList<News>ar =(ArrayList<News>)request.getAttribute("list");
                  		for(int j=0;j<ar.size();j++){
                  			if(j==4) break;
                  			String a = "";
           					if(j==0){
           						a = "class='item active'";
           					}else{
           						a = "class='item'";
           					}
                  	%>
                    <div <%=a%>> <img style='width:500px;height:500px' src="<%=request.getContextPath()%>/files/<%=ar.get(j).getPicture() %>" alt="">
                      <div class="carousel-caption">
                        <h1><a href="#"><%=ar.get(j).getPreview()%></a></h1>
                      </div>
                    </div>
                    <%}} %>
                  </div>
                  <a class="left left_slide" href="#myCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> </a> <a class="right right_slide" href="#myCarousel" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> </a></div>
              </div>
              <div class="single_category wow fadeInDown">
                <div class="category_title"> <a>Tin tức hôm nay</a></div>
                <div class="single_category_inner">
                  <ul class="catg_nav">
                  <%
                     if(request.getAttribute("list")!=null){
                    	 ArrayList<News>arr=(ArrayList<News>)request.getAttribute("list");
                    	 for(News obj:arr){
                    	 
                  %>
                    <li>
                      <div class="catgimg_container"> <a class="catg1_img" href="<%=request.getContextPath()%>/detail?did=<%=obj.getId()%>"> <img src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>" alt=""> </a></div>
                      <a class="catg_title" href="<%=request.getContextPath()%>/detail?did=<%=obj.getId()%>"><%=obj.getName()%></a>
                      <div class="sing_commentbox">
                        <p><i class="fa fa-calendar"></i><%=obj.getDate_create()%></p>
                        <a href="#"><i class="fa fa-comments"></i>20 Comments</a></div>
                   </li>
                   <%}}%>
                     
                  
                  </ul>
                </div>
              </div>
              <!-- ####################################################################################################### -->
              <div class="single_category  wow fadeInDown">
                <div class="category_title"> <a>TIN TỨC MỚI NHẤT</a></div>
                <div class="single_category_inner">
                  <ul class="catg_nav catg_nav2">
                  <%
                    int k=0;
                    if(request.getAttribute("listnews")!=null){
                    ArrayList<News>list =(ArrayList<News>)request.getAttribute("listnews");
                    for(News obj:listnews){
                    	if(obj.getActive()==1){
                    		if(k==4) break;
                    	
                  %>
                    <li>
                      <h2 style='background-color: #fff500;'><a style='color:#f300ff'href="<%=request.getContextPath()%>/detail?did=<%=obj.getId()%>"><%=obj.getCname()%> &raquo;</a></h2>
                      <div class="catgimg_container"> <a class="catg1_img" href="<%=request.getContextPath()%>/detail?did=<%=obj.getId()%>"> 
                      <img src="<%=request.getContextPath()%>/files/<%=obj.getPicture() %>" alt=""> </a></div>
                      <a class="catg_title" href="<%=request.getContextPath()%>/detail?did=<%=obj.getId()%>"> <%=obj.getName()%></a>
                      <div class="sing_commentbox">
                        <p><i class="fa fa-calendar"></i><%=obj.getDate_create() %></p>
                        <a href="#"><i class="fa fa-comments"></i>20 Comments</a></div>
                      <p class="post-summary"><%=obj.getPreview()%></p>
                   </li>
                   <%}}} %>
                  </ul>
                </div>
              </div>
              <div class="category_three_fourarea  wow fadeInDown">
                <div class="category_three">
                  <div class="single_category">
                    
                  </div>
                </div>
                <div class="category_four wow fadeInDown">
                  <div class="single_category">
                  </div>
                </div>
              </div>
              <div class="single_category wow fadeInDown">
                <div class="single_category_inner">
                  <ul class="catg3_snav catg5_nav">
                   
                    
                  </ul>
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