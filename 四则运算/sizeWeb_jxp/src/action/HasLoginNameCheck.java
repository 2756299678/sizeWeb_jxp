package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/HasLoginNameCheck")
public class HasLoginNameCheck extends HttpServlet{

	public HasLoginNameCheck()
	{
		super();
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
	
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("开始执行获取用户是否登录的servlet");
		response.setContentType("text/html;charset=UTF-8");
		//获取session
		HttpSession session = request.getSession();
		User u = null;
		u = (User)session.getAttribute("userInfo");
		//获取out对象，用于向响应中输出信息
		String msg;
		if(u == null)
		{
			//session中没有用户名信息
			msg = "no";
			System.out.println("用户未登录");
		}
		else
		{
			msg = u.getUsername() + " " + u.getPwd();
			System.out.println("用户已登录，登录用户名为： " + u.getUsername());
		}
		PrintWriter out = response.getWriter();
		out.print(msg);
		out.flush();
		out.close();
		System.out.println("获取用户是否登录的servlet执行结束,返回信息为：" + msg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
