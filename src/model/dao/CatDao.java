package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import model.bean.Category;
import util.DatabaseConnection;

public class CatDao {
	    private Statement st;
	    private Connection conn;
	    private ResultSet rs;
	    private PreparedStatement pst;
	public ArrayList<Category>getItem() {
		ArrayList<Category>listCat = new ArrayList<>();
		String sql="SELECT id,name,parent_id FROM cat_list ORDER BY id DESC";
		conn=DatabaseConnection.getConnection();
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				Category obj = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
				listCat.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listCat;
	}
	public ArrayList<Category> getcatFather() {
		ArrayList<Category>listcat = new ArrayList<>();
		String sql = "SELECT * FROM cat_list WHERE parent_id =0";
		conn =DatabaseConnection.getConnection();
		try {
			st = conn.createStatement();
			rs =st.executeQuery(sql);
			while(rs.next()){
				Category obj = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
				listcat.add(obj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listcat;
	}
	
	public int addcat(String tendanhmuc, int idparent) {
	    String sql="INSERT INTO cat_list(name,parent_id) VALUES(?,?)";
	    conn =DatabaseConnection.getConnection();
	    int result =0;
	    try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, tendanhmuc);
			pst.setInt(2, idparent);
			result =pst.executeUpdate();
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
	public Category getnameCatWithID(int idc) {
	    String sql ="SELECT *FROM cat_list WHERE id="+idc;
	    Category obj =null;
	    conn=DatabaseConnection.getConnection();
	    try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				obj = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	public Category checksonoffa(int idc) {
		Category obj =null;
		String sql = "SELECT * FROM cat_list WHERE parent_id="+idc;
		conn= DatabaseConnection.getConnection();
		try {
			st =conn.createStatement();
			rs =st.executeQuery(sql);
			if(rs.next()){
				obj = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	public ArrayList<Category> getcatParent(int id){
		ArrayList<Category> ar = new ArrayList<>();
		String sql="SELECT * FROM cat_list WHERE parent_id="+id;
		conn = DatabaseConnection.getConnection();
		
		try {
			st = conn.createStatement();
			rs =st.executeQuery(sql);
			while(rs.next()){
				Category obj = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
				ar.add(obj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return ar;
	}
	public int updateCat(int idc, String tendm, int idparent) {
		String sql="UPDATE cat_list SET name=?,parent_id=? WHERE id=?";
		conn= DatabaseConnection.getConnection();
		int result =0;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, tendm);
			pst.setInt(2, idparent);
			pst.setInt(3, idc);
			result= pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public int delCatByID(int idc) {
		 int result =0;
		 String sql="DELETE FROM cat_list WHERE id=? OR parent_id=?";
		 conn=DatabaseConnection.getConnection();
		 try {
			pst =conn.prepareStatement(sql);
			pst.setInt(1, idc);
			pst.setInt(2, idc);
			result =pst.executeUpdate();
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
		return result ;
	}
	public ArrayList<Category> getItems() {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM cat_list ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
			Category obj =  new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
				listCat.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listCat;
	}
	public Category getnamecat(int cid) {
		Category obj = new Category();
		String sql ="SELECT * FROM cat_list WHERE id=?";
		conn =DatabaseConnection.getConnection();
		try {
			pst =conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs =pst.executeQuery();
			if(rs.next()){
				obj = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	}


