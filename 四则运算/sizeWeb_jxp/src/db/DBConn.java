package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConn {
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String url = "jdbc:sqlserver://localhost:1433; DatabaseName=Arithmetic";
	private static final String user= "ZH";
	private static final String password= "202862";

	

	private static Connection coon= null;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	static{

		try {
			Class.forName(driver);
			coon = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	static public Connection getConnection(){
		return coon;
	}
	
	
	
	public static void close() {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try{
					if(pstmt!=null)
						pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if(coon!=null)
							coon.close();
					}catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	public static void main(String[] args) {
		try {
			DBConn.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
