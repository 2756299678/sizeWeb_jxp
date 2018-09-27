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

		System.out.println("����servlet��ʼִ��");
		response.setContentType("text/html;charSet=UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		
		
		//��ȡsession����
		HttpSession session = request.getSession();
		//��session�л�ȡ��������
		int n = (int)session.getAttribute("num");
		//������������session��ɾ��
		session.removeAttribute("num");
		//��session�л�ȡ����list
		List<Question> shiTiList = (List<Question>) session.getAttribute("shiTiList");
		//��shitiList��session��ɾ��
		session.removeAttribute("shiTiList");
		//��session�л�ȡ�û���Ϣ
		User u = (User)session.getAttribute("user");
		//��session�л�ȡ�Ծ����
		Exam sj = (Exam)session.getAttribute("shiJuan"); 
		//���Ծ�����session��ɾ��
		session.removeAttribute("shiJuan");
		
		String result = "";
		List<Question> shiTiList2 = new ArrayList<Question>();
		
		int rightSum = 0;
		int wrongSum = 0;
		String rightTiHao = "";
		String wrongTiHao = "";
		//��request�����л�ȡ�û�������ȷ�𰸱Ƚϣ����������ӵ�result�ַ���
		for(int i = 0;i < n;i++)
		{
			Question st = new Question();
			st = shiTiList.get(i);
			String userAnswer = request.getParameter((i + 1) + "");
			System.out.println("��ȷ�𰸣�" + st.getRightAnswer());
			System.out.println("��ȡ�����û���,��ţ�" + (i + 1) + ", �𰸣�" + userAnswer);
			if(userAnswer.equals(st.getRightAnswer()))
			{
				//��ȷ
				rightSum++;
				st.setUserScore(1);
				result += "��ȷ#";
				rightTiHao += (i + 1) + ",";
			}
			else
			{
				wrongSum++;
				st.setUserScore(0);
				result += "����,��ȷ��Ϊ��" + st.getRightAnswer() + "#"; 
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
		result += "��ȷ������" + rightSum + ";  ��ţ� ( " + rightTiHao + " )       ���������" + wrongSum + ";  ��ţ� ( " + wrongTiHao + " )";
	//	result += "��ȷ������" + rightSum + ",���������" + wrongSum;
		//��ȡout����
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		System.out.println("���ؽ�������Ϊ��" + result);
		//�����Ծ����
		sj.setGrade(rightSum);
		
		//�����û���Ϣ
		u.setEnum(u.getEnum() + 1);
		u.setQnum(u.getQnum()+ n);
		u.setRnum(u.getRnum() + rightSum);
		
		//�������ݿ�
		//�����������ݿ�
		QuestionDao stdao = new QuestionDao();
		try {
			stdao.update(shiTiList2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�����Ծ����ݿ�
		QuestionsDao sjdao = new QuestionsDao();
		try {
			sjdao.update(sj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�����û����ݿ�
		UserDao1 uidao = new UserDao1();
		try {
			uidao.update(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("����servletִ�н���");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
}
}
