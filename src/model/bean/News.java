package model.bean;

import java.sql.Timestamp;

public class News {
     private int id;
     private String name;
     private String preview;
     private String detail;
     private Timestamp date_create;
     private int created_by;
     private String picture;
     private int cat_id;
     private int is_slide;
     private int active;
     private String cname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public int getIs_slide() {
		return is_slide;
	}
	public void setIs_slide(int is_slide) {
		this.is_slide = is_slide;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public News(int id, String name, String preview, String detail, Timestamp date_create, int created_by,
			String picture, int cat_id, int is_slide, int active, String cname) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.date_create = date_create;
		this.created_by = created_by;
		this.picture = picture;
		this.cat_id = cat_id;
		this.is_slide = is_slide;
		this.active = active;
		this.cname = cname;
	}
	public News() {
		super();
	}

	
	
}
