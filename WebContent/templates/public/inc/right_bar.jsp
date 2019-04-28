<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="single_leftbar wow fadeInDown">
                <h2><span>Danh sach tin</span></h2>
                <div class="singleleft_inner">
                  <ul class="catg3_snav ppost_nav wow fadeInDown">
                  <%
                     if(request.getAttribute("list")!=null){
                     ArrayList<News>listnew =(ArrayList<News>)request.getAttribute("list");
                      for(News obj:listnew){
                  %>
                    <li>
                      <div class="media"> <a href="<%=request.getContextPath()%>/cat?cid=<%=obj.getId()%>" class="media-left"> 
                      <img alt="" src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>"> </a>
                        <div class="media-body"> <a href="<%=request.getContextPath()%>/cat?cid=<%=obj.getId()%>" class="catg_title"><%=obj.getName()%></a></div>
                      </div>
                   </li>
                   <%} }%>
                  </ul>
                </div>
            </div>
              <div class="single_leftbar wow fadeInDown">
              </div>
              <div class="single_leftbar wow fadeInDown">
               
                </div>
              
              <div class="single_leftbar wow fadeInDown">
                <h2><span>Links</span></h2>
                <div class="singleleft_inner">
                  <ul class="link_nav">
                    <li><a href="http://vinaenter.edu.vn/">Vinaenter</a></li>
                    <li><a href="https://www.facebook.com/">Facebook</a></li>
                  </ul>
                </div>
              </div>