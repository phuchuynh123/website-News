package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constant.Define;
import model.bean.News;
import util.DatabaseConnection;

public class NewsDao {
	    private Statement st;
	    private Connection conn;
	    private ResultSet rs;
	    private PreparedStatement pst;
	public int countNews() {
		int result =0;
		conn =DatabaseConnection.getConnection();
		String sql ="SELECT COUNT(*) AS sumNews FROM news AS n INNER JOIN cat_list AS c ON n.cat_id = c.id  ";
		try {
			st =conn.createStatement();
			rs =st.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("sumNews");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public ArrayList<News> getItemsPagination(int offset) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = DatabaseConnection.getConnection();
		String sql = "SELECT *,c.name AS cname FROM news AS n INNER JOIN cat_list AS c ON n.cat_id = c.id ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT_ADMIN);
			rs = pst.executeQuery();
			while (rs.next()) {
				News obj = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("created_by"), rs.getString("picture"), rs.getInt("cat_id"), rs.getInt("is_slide"), rs.getInt("active"), rs.getString("cname"));
				listNews.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return listNews;
	}

	public int addItem(News obj) {
		int result = 0;
		conn = DatabaseConnection.getConnection();
		String sql = "INSERT INTO news(name, preview, detail, date_create, created_by, picture, cat_id, is_slide, active) VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, obj.getName());
			pst.setString(2, obj.getPreview());
			pst.setString(3, obj.getDetail());
			pst.setTimestamp(4,obj.getDate_create());
			pst.setInt(5, obj.getCreated_by());
			pst.setString(6, obj.getPicture());
			pst.setInt(7, obj.getCat_id());
			pst.setInt(8, obj.getIs_slide());
			pst.setInt(9, obj.getActive());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}

		return result;
	}

	public News getItem(int nid) {
		News objnews = null;
		conn =DatabaseConnection.getConnection();
		String sql = "SELECT *,c.name AS cname FROM news AS n INNER JOIN cat_list AS c ON n.cat_id = c.id WHERE n.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, nid);
			rs = pst.executeQuery();
			if (rs.next()) {
				objnews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("created_by"), rs.getString("picture"), rs.getInt("cat_id"), rs.getInt("is_slide"), rs.getInt("active"), rs.getString("cname"));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return objnews;
	}

	public int editItem(News obj) {
		int result = 0;
		conn = DatabaseConnection.getConnection();
		String sql = "UPDATE news SET name = ?,preview = ?, detail = ?, date_create = ?, picture = ?, cat_id = ?,is_slide = ? WHERE id= ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, obj.getName());
			pst.setString(2, obj.getPreview());
			pst.setString(3, obj.getDetail());
			pst.setTimestamp(4, obj.getDate_create());
			pst.setString(5, obj.getPicture());
			pst.setInt(6, obj.getCat_id());
			pst.setInt(7, obj.getIs_slide());
			pst.setInt(8, obj.getId());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	
	}

	public int delNews(int nid) {
		int result =0;
		String sql="DELETE FROM news WHERE id=? ";
		conn=DatabaseConnection.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, nid);
			result=pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
   //list tin hot
	public ArrayList<News> getListNews() {
		 ArrayList<News>listnews = new ArrayList<>();
		 conn =DatabaseConnection.getConnection();
		 String sql ="SELECT *,c.name AS cname FROM news AS n INNER JOIN cat_list AS c ON n.id = c.id ORDER BY n.date_create ASC";
		 try {
			st =conn.createStatement();
			rs =st.executeQuery(sql);
			while (rs.next()) {
				News obj = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("created_by"), rs.getString("picture"), rs.getInt("cat_id"), rs.getInt("is_slide"), rs.getInt("active"), rs.getString("cname"));
				listnews.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return listnews;
	}
//listnews
	public ArrayList<News> getList() {
		ArrayList<News> listnews = new ArrayList<>();
		String sql = "SELECT *, c.name as cname FROM news as n INNER JOIN cat_list as c ON n.id = c.id ORDER BY n.id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News obj = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("created_by"), rs.getString("picture"), rs.getInt("cat_id"), rs.getInt("is_slide"), rs.getInt("active"), rs.getString("cname"));
				listnews.add(obj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listnews;
	}

	public ArrayList<News> getListNewsofCat(int cid) {
		ArrayList<News> listnews = new ArrayList<>();
		conn =DatabaseConnection.getConnection();
		String sql = "SELECT *, c.name as cname FROM news as n INNER JOIN cat_list as c ON n.id = c.id WHERE n.id = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News objnews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("created_by"), rs.getString("picture"), rs.getInt("cat_id"), rs.getInt("is_slide"), rs.getInt("active"), rs.getString("cname"));
				listnews.add(objnews);

			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listnews;

		
	}

	public ArrayList<News> getListSearch(String name) {
		ArrayList<News> listnews = new ArrayList<>();
		conn = DatabaseConnection.getConnection();
		String sql = "SELECT *, c.name as cname FROM news as n INNER JOIN cat_list as c ON n.cat_id = c.id WHERE n.name LIKE "
				+ "'%" + name + "%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objnews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("created_by"), rs.getString("picture"), rs.getInt("cat_id"), rs.getInt("is_slide"), rs.getInt("active"), rs.getString("cname"));
				listnews.add(objnews);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return listnews;
		
	}

	public int countNewsbyCID(int cid) {
		int count=0;
		conn=DatabaseConnection.getConnection();
		String sql="SELECT COUNT(*) AS count FROM news WHERE cat_id="+cid;
		try {
			st=conn.createStatement();
			rs =st.executeQuery(sql);
			if(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<News> getItembyCIDPagination(int offset, int cid) {
		 conn =DatabaseConnection.getConnection();
		 ArrayList<News>ar= new ArrayList<>();
		 String sql ="SELECT * FROM news WHERE cat_id ="+cid+" ORDER BY id DESC LIMIT "+offset+","+Define.ROW_COUNT_ADMIN;
		 try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				News obj = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("created_by"), rs.getString("picture"), rs.getInt("cat_id"), rs.getInt("is_slide"), rs.getInt("active"),"");
				ar.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
		return ar;
	}
    
	
 
}
