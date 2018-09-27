package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import dao.QuestionsDao;
import dao.UserDao1;
import model.Exam;
import model.Question;
import model.User;

@WebServlet("/Submitac")
public class Submitac extends HttpServlet{

	private static final long serialVersionUID=1L;
	
	public Submitac(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("交卷servlet开始执行");
		response.setContentType("text/html;charSet=UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		
		
		//获取session对象
		HttpSession session = request.getSession();
		//从session中获取试题总数
		int n = (int)session.getAttribute("num");
		//将试题总数从session中删除
		session.removeAttribute("num");
		//从session中获取试题list
		List<Question> shiTiList = (List<Question>) session.getAttribute("shiTiList");
		//将shitiList从session中删除
		session.removeAttribute("shiTiList");
		//从session中获取用户信息
		User u = (User)session.getAttribute("user");
		//从session中获取试卷对象
		Exam sj = (Exam)session.getAttribute("shiJuan"); 
		//将试卷对象从session中删除
		session.removeAttribute("shiJuan");
		
		String result = "";
		List<Question> shiTiList2 = new ArrayList<Question>();
		
		int rightSum = 0;
		int wrongSum = 0;
		String rightTiHao = "";
		String wrongTiHao = "";
		//从request对象中获取用户答案与正确答案比较，并将结果添加到result字符串
		for(int i = 0;i < n;i++)
		{
			Question st = new Question();
			st = shiTiList.get(i);
			String userAnswer = request.getParameter((i + 1) + "");
			System.out.println("正确答案：" + st.getRightAnswer());
			System.out.println("获取到的用户答案,题号：" + (i + 1) + ", 答案：" + userAnswer);
			if(userAnswer.equals(st.getRightAnswer()))
			{
				//正确
				rightSum++;
				st.setUserScore(1);
				result += "正确#";
				rightTiHao += (i + 1) + ",";
			}
			else
			{
				wrongSum++;
				st.setUserScore(0);
				result += "错误,正确答案为：" + st.getRightAnswer() + "#"; 
				wrongTiHao += (i + 1) + ",";
			}
			st.setUserAnswer(userAnswer);
			shiTiList2.add(st);
		}
		if(!rightTiHao.equals(""))
		{
			rightTiHao = rightTiHao.substring(0, rightTiHao.length() - 1);
		}
		if(!wrongTiHao.equals(""))
		{
			wrongTiHao = wrongTiHao.substring(0, wrongTiHao.length() - 1);
		}
		result += "正确个数：" + rightSum + ";  题号： ( " + rightTiHao + " )       错误个数：" + wrongSum + ";  题号： ( " + wrongTiHao + " )";
	//	result += "正确个数：" + rightSum + ",错误个数：" + wrongSum;
		//获取out对象
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		System.out.println("返回结果，结果为：" + result);
		//设置试卷分数
		sj.setGrade(rightSum);
		
		//更新用户信息
		u.setEnum(u.getEnum() + 1);
		u.setQnum(u.getQnum()+ n);
		u.setRnum(u.getRnum() + rightSum);
		
		//更新数据库
		//更新试题数据库
		QuestionDao stdao = new QuestionDao();
		try {
			stdao.update(shiTiList2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//更新试卷数据库
		QuestionsDao sjdao = new QuestionsDao();
		try {
			sjdao.update(sj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//更新用户数据库
		UserDao1 uidao = new UserDao1();
		try {
			uidao.update(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("交卷servlet执行结束");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
}
}
