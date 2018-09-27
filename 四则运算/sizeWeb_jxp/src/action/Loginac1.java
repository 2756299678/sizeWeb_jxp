package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao1;
import db.DBConn;
import model.User;

@WebServlet("/Loginac")
public class Loginac1 extends HttpServlet{

	private static final long serislVersionUID=1L;
	private  Connection coon= null;
	private  PreparedStatement pstmt;
	private  ResultSet rs;
 	public Loginac1()
	{
		super();
	}
 	
 	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
 		System.out.println("登录验证开始执行");
 		response.setContentType("text/html;charSet=UTF-8");
 		request.setCharacterEncoding("UTF-8");
 		String username=request.getParameter("username");
 		String pwd=request.getParameter("pwd");
 		
 		User u=new User();
 		u.setUsername(username);
 		User uu=null;
 		UserDao1 ud=new UserDao1();
 		
 	
 		try {
			uu=ud.selectByUsername(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		PrintWriter out=response.getWriter();//
 		
 		if(uu==null)
 		{
 			out.println("用户名不存在");
 			System.out.println("用户名不存在");
 		}
 		else if(uu.getPwd().equals(pwd))
 		{
 			//登录成功
 			//将用户信息存入session
 			//获取session对象
 			HttpSession session=request.getSession();
 			
 			session.setAttribute("user", uu);
 			out.print(username);
 			System.out.println("用户："+username+"登录成功");
 			try {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 		else
 		{
 			out.print("密码错误");
 			System.out.println("密码错误");
 		}

 		out.flush();
 		out.close();
 		System.out.println("登录servlet结束");
 	}


    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	
    	doGet(request,response);
    }

}
