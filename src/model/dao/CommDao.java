package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constant.Define;
import model.bean.Comment;
import util.DatabaseConnection;

public class CommDao {
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	public int countNews() {
		int cout=0;
		conn = DatabaseConnection.getConnection();
		String sql ="SELECT COUNT(*) AS sumNews FROM comment AS c INNER JOIN news AS n ON c.news_id=n.id";
		try {
			st =conn.createStatement();
			rs =st.executeQuery(sql);
			if(rs.next()){
				cout =rs.getInt("sumNews");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cout;
	}
	public ArrayList<Comment> getItemsPagination(int offset) {
		ArrayList<Comment> listCmt = new ArrayList<>();
		conn = DatabaseConnection.getConnection();
		String sql = "SELECT *, n.name AS nname FROM comment AS c INNER JOIN news AS n ON c.news_id = n.id ORDER BY c.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT_ADMIN);
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment obj = new Comment(rs.getInt("id"), rs.getString("nname"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("content"), rs.getInt("parent_id"), rs.getInt("news_id"), rs.getInt("active"));
				listCmt.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return listCmt;
		
	}
	public ArrayList<Comment> getListcmt(int did) {
		ArrayList<Comment> listcmt = new ArrayList<>();
		conn = DatabaseConnection.getConnection();
		String sql = "SELECT *, n.name AS nname FROM comment AS c INNER JOIN news AS n ON c.news_id = n.id WHERE c.news_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, did);
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment obj = new Comment(rs.getInt("id"), rs.getString("nname"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("content"), rs.getInt("parent_id"), rs.getInt("news_id"), rs.getInt("active"));
				listcmt.add(obj);
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
		return listcmt;
	}
	public int addItem(Comment obj) {
		int result = 0;
		conn = DatabaseConnection.getConnection();
		String sql = "INSERT INTO comment(id,name,email,website,content,parent_id,news_id,active) VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, obj.getId());
			pst.setString(2, obj.getNameNews());
			pst.setString(3, obj.getEmail());
			pst.setString(4, obj.getWebsite());
			pst.setString(5, obj.getContent());
			pst.setInt(6, obj.getParent_id());
			pst.setInt(7, obj.getNews_id());
			pst.setInt(8, obj.getActive());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	public int delcomment(int cmtid) {
		int result =0;
		conn =DatabaseConnection.getConnection();
		String sql="DELETE FROM comment WHERE id=?";
		try {
			pst =conn.prepareStatement(sql);
			pst.setInt(1, cmtid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	

}
