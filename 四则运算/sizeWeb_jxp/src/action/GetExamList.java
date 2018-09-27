package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionsDao;
import model.Exam;
import model.User;

@WebServlet("/GetExamList")
public class GetExamList extends HttpServlet{

	public GetExamList(){
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charSet=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("获取试卷列表servlet开始执行");
		
		//获取session对象
		HttpSession session = request.getSession();
		//从session对象获取用户信息
		User u = (User) session.getAttribute("user");
		//System.out.println("参数：username=" + username);
		List<Exam> shiJuanList = null;
		
		//构建试卷数据库操作对象
		QuestionsDao sjdao = new QuestionsDao();
		try {
			shiJuanList = sjdao.selectByUsername(u.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = "";
		
		if(shiJuanList != null)
		{
			for(int i = 0;i < shiJuanList.size();i++)
			{
				Exam sj = shiJuanList.get(i);
				result += "日期：" + sj.getDate() + "  试题数：" + sj.getShiTiNum() + "  正确个数：" + sj.getGrade() + "," + sj.getShiJuanID() + "#";
			}
		}
		
		//获取out输出对象
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		
		System.out.println("获取试卷信息servlet执行结束，返回的信息:" + result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
