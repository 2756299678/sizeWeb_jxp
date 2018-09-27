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
		System.out.println("��ʼִ�л�ȡ�û��Ƿ��¼��servlet");
		response.setContentType("text/html;charset=UTF-8");
		//��ȡsession
		HttpSession session = request.getSession();
		User u = null;
		u = (User)session.getAttribute("userInfo");
		//��ȡout������������Ӧ�������Ϣ
		String msg;
		if(u == null)
		{
			//session��û���û�����Ϣ
			msg = "no";
			System.out.println("�û�δ��¼");
		}
		else
		{
			msg = u.getUsername() + " " + u.getPwd();
			System.out.println("�û��ѵ�¼����¼�û���Ϊ�� " + u.getUsername());
		}
		PrintWriter out = response.getWriter();
		out.print(msg);
		out.flush();
		out.close();
		System.out.println("��ȡ�û��Ƿ��¼��servletִ�н���,������ϢΪ��" + msg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
