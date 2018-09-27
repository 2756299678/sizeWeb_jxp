package dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConn;
import func.Questions;
import model.Exam;
import model.Question;

public class QuestionsDao {


	
	public void insert(Exam sj) throws SQLException
	{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBConn.getConnection();
	//	System.out.println(sj.getShiTiNum() + " " + sj.getUsername() + " " + sj.getDate() + " " + sj.getUserCurrectSum());
		pstmt = conn.prepareStatement("insert into Exam(shiTiNum,username,date,shiJuanID) values(?,?,?,?)");
		pstmt.setInt(1, sj.getShiTiNum());
		pstmt.setString(2, sj.getUsername());
		pstmt.setString(3, sj.getDate());
		pstmt.setInt(4, sj.getShiJuanID());
		pstmt.executeUpdate();
		
		DBConn.close();
		
	}
	
	public List<Exam> selectByUsername(String username) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Exam> list = new ArrayList<Exam>();
		
		conn = DBConn.getConnection();
		pstmt = conn.prepareStatement("select * from Exam where username=?");
		pstmt.setString(1, username);
		
		rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Exam sj = new Exam();
			sj.setId(rs.getInt("id"));
			sj.setShiTiNum(rs.getInt("shiTiNum"));
			sj.setUsername(rs.getString("username"));
			sj.setGrade(rs.getInt("grade"));
			sj.setDate(rs.getString("date"));
			sj.setShiJuanID(rs.getInt("shiJuanID"));
			list.add(sj);
		}
		
		DBConn.close();
		return list;

	}
	
	public void update(Exam sj) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBConn.getConnection();
		pstmt = conn.prepareStatement("update Exam set grade=? where username=? and shiJuanID=?");
		pstmt.setInt(1,sj.getGrade());
		pstmt.setString(2, sj.getUsername());
		pstmt.setInt(3, sj.getShiJuanID());
		pstmt.executeUpdate();
		
		DBConn.close();
		
		
	}
}
