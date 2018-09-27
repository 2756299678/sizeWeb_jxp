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
		
		System.out.println("��ȡ�Ծ��б�servlet��ʼִ��");
		
		//��ȡsession����
		HttpSession session = request.getSession();
		//��session�����ȡ�û���Ϣ
		User u = (User) session.getAttribute("user");
		//System.out.println("������username=" + username);
		List<Exam> shiJuanList = null;
		
		//�����Ծ����ݿ��������
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
				result += "���ڣ�" + sj.getDate() + "  ��������" + sj.getShiTiNum() + "  ��ȷ������" + sj.getGrade() + "," + sj.getShiJuanID() + "#";
			}
		}
		
		//��ȡout�������
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		
		System.out.println("��ȡ�Ծ���Ϣservletִ�н��������ص���Ϣ:" + result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
