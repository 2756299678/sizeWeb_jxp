package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConn;
import model.Question;

public class QuestionDao {

	//��list�е�������뵽���ݿ�,
		public void insert(List<Question> list) throws SQLException
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("insert into Question(tiMu,rightAnswer,username,logicOrder,length,shiJuanID) values(?,?,?,?,?,?)");
			
			for(Question st : list)
			{
				pstmt.setString(1, st.getTiMu());
				pstmt.setString(2, st.getRightAnswer());
				pstmt.setString(3, st.getUsername());
				pstmt.setString(4, st.getLogicOrder());
				pstmt.setInt(5, st.getLength());
				pstmt.setInt(6, st.getShiJuanID());
				pstmt.executeUpdate();
			}
			
			DBConn.close();
		}
		
		//�����û������û�������ID��ѯ���⣬���ؽ��Ϊlist
		public List<Question> selectByUserNameANDShiJuanID(String username,int shiJuanID) throws SQLException
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<Question> list = new ArrayList<Question>();
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("select * from Question where username=? and shiJuanID=?");
			pstmt.setString(1, username);
			pstmt.setInt(2, shiJuanID);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Question st = new Question();
				st.setId(rs.getInt("id"));
				st.setTiMu(rs.getString("tiMu"));
				st.setRightAnswer(rs.getString("rightAnswer"));
				st.setUserAnswer(rs.getString("userAnswer"));
				st.setUserScore(rs.getInt("userScore"));
				list.add(st);
			}
			
			DBConn.close();
			return list;


		}
		
		
		//�����û���������Դ��ѯ���⣬���ؽ��Ϊlist
			public List<Question> selectByUserNameANDScore(String username,int score) throws SQLException
			{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				List<Question> list = new ArrayList<Question>();
				
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement("select * from Question where username=? and userScore=?");
				pstmt.setString(1, username);
				pstmt.setInt(2, score);
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					Question st = new Question();
					st.setId(rs.getInt("id"));
					st.setTiMu(rs.getString("tiMu"));
					st.setRightAnswer(rs.getString("rightAnswer"));
					st.setUserAnswer(rs.getString("userAnswer"));
					st.setUserScore(rs.getInt("userScore"));
					list.add(st);
				}
				
				DBConn.close();
				return list;


			}

		
		//�����û�����ѯ����
		public List<Question> selectByUsername(String username) throws SQLException
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<Question> list = new ArrayList<Question>();
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("select * from Question where username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Question st = new Question();
				st.setId(rs.getInt("id"));
				st.setTiMu(rs.getString("tiMu"));
				st.setRightAnswer(rs.getString("rightAnswer"));
				st.setUserAnswer(rs.getString("userAnswer"));
				st.setUserScore(rs.getInt("userScore"));
				list.add(st);
			}
			
			DBConn.close();
			return list;


		}
		
		//�����û���/�Ծ�id������÷ֲ�ѯ����
		public List<Question> selectByUsernameAndShiJuanIDAndScore(String username,int shiJuanID,int score) throws SQLException
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<Question> list = new ArrayList<Question>();
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("select * from Question where username=? and shiJuanID=? and userScore=?");
			pstmt.setString(1, username);
			pstmt.setInt(2, shiJuanID);
			pstmt.setInt(3, score);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Question st = new Question();
				st.setId(rs.getInt("id"));
				st.setTiMu(rs.getString("tiMu"));
				st.setRightAnswer(rs.getString("rightAnswer"));
				st.setUserAnswer(rs.getString("userAnswer"));
				st.setUserScore(rs.getInt("userScore"));
				list.add(st);
			}
			
			DBConn.close();
			return list;
		}

		//�������⣬������Ŀ���û������Ծ�ID 
		public void update(List<Question> list) throws SQLException
		{

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("update Question set userAnswer=?,userScore=? where tiMu=? and username=? and shiJuanID=?");
			
			
			
			for(Question st : list)
			{
				pstmt.setString(1, st.getUserAnswer());
				pstmt.setInt(2, st.getUserScore()); 
				pstmt.setString(3, st.getTiMu());
				pstmt.setString(4, st.getUsername());
				pstmt.setInt(5, st.getShiJuanID());
				pstmt.executeUpdate();
			}
			
			DBConn.close();
			
		}
}
