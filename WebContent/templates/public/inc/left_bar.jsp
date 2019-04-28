<%@page import="util.StringLibrary"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.NewsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <h2><span>Recent Post</span></h2>
                <div class="singleleft_inner">
                <%
                  NewsDao newsDao = new NewsDao();
                   ArrayList<News>listnews = newsDao.getListNews();
                   int i=1;
                %>
                <h3 style="font-size: 28px;font-weight: bold;" class="tieude1">Tin Hot</h3>
                  <ul class="recentpost_nav wow fadeInDown">
                    <%
                      for(News item:listnews){
                    	  if(item.getActive()==1){
                    		int id = item.getId();
                   			String slug = StringLibrary.makeSlug(item.getName());
                   			String url = request.getContextPath() + "/" + slug + "-" + id;
                    	if(i==4)
                    	   break;
                      
                    %>
                    <li><a href="<%=url%>">
                    <img src="<%=request.getContextPath()%>/files/<%=item.getPicture()%>" alt=""></a> <a class="recent_title" href="<%=url%>"><%=item.getName() %></a></li>
                    <%
                     i++;
                    } }%>
                  </ul>
                </div>