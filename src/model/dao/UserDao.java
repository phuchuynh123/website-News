package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.User;
import util.DatabaseConnection;

public class UserDao {
	    private Statement st;
	    private Connection conn;
	    private ResultSet rs;
	    private PreparedStatement pst;
	public ArrayList<User> getItems() {
		ArrayList<User>listUser = new ArrayList<>();
		conn =DatabaseConnection.getConnection();
		String sql="SELECT * FROM user ORDER BY id DESC";
		try {
			st=conn.createStatement();
			rs =st.executeQuery(sql);
			while(rs.next()){
				User obj = new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"), rs.getInt("active"), rs.getString("email"), rs.getString("capbac"));
				listUser.add(obj);
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
		return listUser;
	}
	//kiểm tra username đã tồn tại
	public User checkUserName(String username) {
		   User obj = null;
		   conn =DatabaseConnection.getConnection();
		   String sql ="SELECT * FROM user WHERE username=?";
		   try {
			pst =conn.prepareStatement(sql);
			pst.setString(1, username);
			rs =pst.executeQuery();
			if(rs.next()){
				obj = new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"), rs.getInt("active"), rs.getString("email"), rs.getString("capbac")); 
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		   
		return obj;
	}
	public int addItem(User obj) {
		  int result =0;
		  conn =DatabaseConnection.getConnection();
		  String sql="INSERT INTO user(username,password,fullname,picture,active,email,capbac) VALUES (?,?,?,?,?,?,?)";
		  try {
			pst =conn.prepareStatement(sql);
			pst.setString(1, obj.getUsername());
			pst.setString(2, obj.getPassword());
			pst.setString(3, obj.getFullname());
			pst.setString(4, obj.getPicture());
			pst.setInt(5, obj.getActive());
			pst.setString(6, obj.getEmail());
			pst.setString(7, obj.getCapbac());
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
	public User getItem(int uid) {
		User objUser = null;
		conn = DatabaseConnection.getConnection();
		String sql="SELECT * FROM user WHERE id=?";
		try {
			pst =conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			if(rs.next()){
				objUser=new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"), rs.getInt("active"), rs.getString("email"), rs.getString("capbac")); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return objUser;
	}
	public int eidtUser(User obj) {
		int result=0;
		conn=DatabaseConnection.getConnection();
		String sql="UPDATE user SET fullname=?,password=?,email=?,capbac=?,WHERE id=?";
		try {
			pst =conn.prepareStatement(sql);
			pst.setString(1, obj.getFullname());
			pst.setString(2, obj.getPassword());
			pst.setString(3, obj.getEmail());
			pst.setString(4, obj.getCapbac());
			pst.setInt(5, obj.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public User getItem(User user) {
		User objUser = null;
		conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			rs = pst.executeQuery();
			if(rs.next()) {
				objUser = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"), rs.getInt("active"), rs.getString("email"), rs.getString("capbac"));
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
		return objUser;
	

	}
	public int delUser(int uid) {
		int result =0;
		String sql="DELETE FROM user WHERE id=?";
		try {
			pst =conn.prepareStatement(sql);
			pst.setInt(1, uid);
			result =pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

}
