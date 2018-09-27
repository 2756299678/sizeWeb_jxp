package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConn;
import model.User;

public class UserDao1 {

	public static void main(String[] args) {
		
		User u = new User();
		u.setUsername("asdsfd");
		u.setPwd("1111");
		UserDao1 a = new UserDao1();
		try {
			a.insert(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		public void insert(User u) throws SQLException
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			conn = DBConn.getConnection();
			//pstmt = conn.prepareStatement("insert into User(username,pwd) values(?,?)");
			pstmt = conn.prepareStatement("insert into user1(username,pwd) values(?,?)");
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPwd());
			pstmt.executeUpdate();
			
			DBConn.close();
		}
		
		public User selectByUsername(User u) throws SQLException
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			User n = null;
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("select * from user1 where username=?");
			pstmt.setString(1, u.getUsername());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				n = new User();
				n.setUsername(rs.getString("username"));
				n.setPwd(rs.getString("pwd"));
				n.setQnum(rs.getInt("qNum"));
				n.setEnum(rs.getInt("eNum"));
				n.setRnum(rs.getInt("rNum"));
				
			}
			
			DBConn.close();
			return n;
		}
		
		public void update(User u) throws SQLException
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("update user1 set qNum=?,eNum=?,rNum=? where username=?");
			pstmt.setInt(1, u.getQnum());
			pstmt.setInt(2, u.getEnum());
			pstmt.setInt(3, u.getRnum());
			pstmt.setString(4, u.getUsername());
			pstmt.executeUpdate();
			
			DBConn.close();
		}
		
		
}
