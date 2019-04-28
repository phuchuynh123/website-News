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
          <%
					   Category objCat = (Category)request.getAttribute("nameCat");
					
		  %>
						<h2 style='background-color: #fff500;'><a style='color:#f300ff; text-decoration: none'"><%=objCat.getName()%> &raquo;</a></h2>
            <div class="middle_bar">
            <%
              
               if(request.getAttribute("listCat")!=null){
            	ArrayList<News>listnewscat=(ArrayList<News>)request.getAttribute("listCat");
            	for(News item:listnewscat){
            		int id = item.getId();
        			String name = item.getName();
        			String slug = StringLibrary.makeSlug(name);
        			String url = request.getContextPath() + "/" + slug + "-"+ id + ".html";
            %>
              <div class="category_archive_area">
                <div class="single_archive wow fadeInDown"> <a href="<%=request.getContextPath()%>/detail?did=<%=item.getId()%>">
                <img style='width:600px;height:400px' src="<%=request.getContextPath()%>/files/<%=item.getPicture()%>" alt=""></a> 
                  <div class="singlearcive_article">
                    <h2><a href="<%=url%>"><%=item.getName()%></a></h2>
                    <a class="author_name" href="#"><i class="fa fa-user"></i></a> <a class="post_date" href="#"><i class="fa  fa-clock-o"></i><%=item.getDate_create() %></a>
                    <p><%=item.getPreview()%></p>
                  </div>
                </div>
              </div>
              <%} }%>
               <div class="paginator">
			<%
			   int sumPage=(Integer)request.getAttribute("sumPage");
			   int current_page=(Integer)request.getAttribute("currentPage");
			   String active ="";
			   for(int j=1;j<=sumPage;j++){
				   if(current_page == j){
					   active ="class='active'";
				   }else{
					   active = "";
				   }
			   
			%>
				<a href="<%=request.getContextPath()%>/cat?page=<%=j%>&cid=<%=objCat.getId()%>">Trang <%=j %></a> 
				<%
				  if(j !=sumPage){
				%>
				 ||
				<%} %>
				<%}%>
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