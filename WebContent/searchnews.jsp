<%@page import="util.StringLibrary"%>
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
               if(request.getAttribute("listnews")!=null){
            	ArrayList<News>listnewscat=(ArrayList<News>)request.getAttribute("listnews");
            	for(News item:listnewscat){
            		int id = item.getId();
        			String name = item.getName();
        			String slug = StringLibrary.makeSlug(name);
        			String url = request.getContextPath() + "/" + slug + "-"+ id + ".html";
            %>
              <div class="category_archive_area">
                <div class="single_archive wow fadeInDown"> <a href="<%=url%>">
                <img src="<%=request.getContextPath()%>/files/<%=item.getPicture()%>" alt=""></a> <a href="<%=request.getContextPath()%>/detail?did=<%=item.getId()%>" class="read_more">Read More <i class="fa fa-angle-double-right"></i></a>
                  <div class="singlearcive_article">
                    <h2><a href="<%=url%>"><%=item.getName() %></a></h2>
                    <a class="author_name" href="#"><i class="fa fa-user"></i></a> <a class="post_date" href="#"><i class="fa  fa-clock-o"></i><%=item.getDate_create() %></a>
                    <p><%=item.getPreview()%></p>
                  </div>
                </div>
              </div>
              <%} }%>
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