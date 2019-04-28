package model.bean;


public class Comment {
  private int id;
  private String nameNews;
  private String nameUser;
  private String email;
  private String website;
  private String content;
  private int parent_id;
  private int news_id;
  private int active;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNameNews() {
	return nameNews;
}
public void setNameNews(String nameNews) {
	this.nameNews = nameNews;
}
public String getNameUser() {
	return nameUser;
}
public void setNameUser(String nameUser) {
	this.nameUser = nameUser;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getWebsite() {
	return website;
}
public void setWebsite(String website) {
	this.website = website;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getParent_id() {
	return parent_id;
}
public void setParent_id(int parent_id) {
	this.parent_id = parent_id;
}
public int getNews_id() {
	return news_id;
}
public void setNews_id(int news_id) {
	this.news_id = news_id;
}
public int getActive() {
	return active;
}
public void setActive(int active) {
	this.active = active;
}
public Comment(int id, String nameNews, String nameUser, String email, String website, String content, int parent_id,
		int news_id, int active) {
	super();
	this.id = id;
	this.nameNews = nameNews;
	this.nameUser = nameUser;
	this.email = email;
	this.website = website;
	this.content = content;
	this.parent_id = parent_id;
	this.news_id = news_id;
	this.active = active;
}
public Comment() {
	super();
}
  
}
