package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
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
import func.Questions;
import model.Exam;
import model.MyException;
import model.Question;
import model.User;

@WebServlet("/GetQuestion")
public class GetQuestion extends HttpServlet{

	public GetQuestion(){
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		int hasChengChu = Integer.parseInt(request.getParameter("hasChengChu"));
		int hasKuoHao = Integer.parseInt(request.getParameter("hasKuoHao"));
		int maxNum = Integer.parseInt(request.getParameter("maxNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		List<Question> shiTiList = null;
		try {
			shiTiList = Questions.createYunSuanShi(hasChengChu,hasKuoHao, maxNum, num, type);
//			shiTiList = ShiTiFuncForm.createYunSuanShi(1,1, 10, 5, 0);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(int i = 0;i < num;i++)
		{
			System.out.println(shiTiList.get(i).getTiMu());
		}
		
		HttpSession session = request.getSession();
		
		User u = (User)session.getAttribute("user");
		String shiTiResult = "";
		int shiJuanID = u.getEnum() + 1;
		String username = u.getUsername();
		List<Question> shiTiList2 = new ArrayList<Question>();
		for(int i = 0;i < shiTiList.size();i++)
		{
			Question st = shiTiList.get(i);
			shiTiResult += st.getTiMu() + "#";
			st.setShiJuanID(shiJuanID);
			st.setUsername(username);
			shiTiList2.add(st);
			
		}
		shiTiResult += shiJuanID + "";
		PrintWriter out = response.getWriter();
		out.print(shiTiResult);
		out.flush();
		out.close();
		
		session.setAttribute("shiTiList", shiTiList2);
		session.setAttribute("num", num);
		
		
		Exam sj = new Exam();
		sj.setShiTiNum(num);
		sj.setUsername(username);
		sj.setShiJuanID(shiJuanID);
		Date date = new Date();
		sj.setDate((date.getYear() + 1900) + "Äê"+ date.getMonth() + "ÔÂ" + date.getDate() + "ÈÕ" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
		
		session.setAttribute("shiJuan", sj);
		QuestionsDao sjdao = new QuestionsDao();
		try {
			sjdao.insert(sj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QuestionDao stdao = new QuestionDao();
		
		try {
			stdao.insert(shiTiList2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
