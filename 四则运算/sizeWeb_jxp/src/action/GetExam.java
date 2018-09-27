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

import dao.QuestionDao;
import model.Question;
import model.User;

@WebServlet("/GetExam")
public class GetExam extends HttpServlet{

	private static final long serialVersionUID=1L;
	
	  public GetExam() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
//			response.getWriter().append("Served at: ").append(request.getContextPath());
			System.out.println("��ȡ�Ծ��е�����servlet��ʼִ��");
			response.setContentType("text/html;charSet=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			//��ȡ����ֵ
			String shiJuanID = request.getParameter("shiJuanID");
			String shiTiType = request.getParameter("shiTiType");
			System.out.println("��ȡ���Ĳ�����������Ϣ" + shiJuanID + "   �������ͣ� " + shiTiType);
			
			
			//��ȡ�û���
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("user");
			String username = u.getUsername();
			
			System.out.println("�û�����" + username);
			
			List<Question> shiTiList = null;
			QuestionDao stdao = new QuestionDao();
			try {
				if(shiJuanID.equals("all"))
				{
					if(shiTiType.equals("all"))
					{
						//��ѯ�û������Ծ����������
						shiTiList = stdao.selectByUsername(username); 
					}
					else
					{
						int type = 0;
						if(shiTiType.equals("right"))
						{
							type = 1;
						}
						shiTiList = stdao.selectByUserNameANDScore(username, type);
					}
				}
				else
				{
					int shijuanid = Integer.parseInt(shiJuanID);
					if(shiTiType.equals("all"))
					{
						//��ѯ�û������Ծ����������
						shiTiList = stdao.selectByUserNameANDShiJuanID(username, shijuanid); 
					}
					else
					{
						int type = 0;
						if(shiTiType.equals("right"))
						{
							type = 1;
						}
						shiTiList = stdao.selectByUsernameAndShiJuanIDAndScore(username, shijuanid, type);
					}

				}
			}catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(shiTiList == null)
			{
				System.out.println("����listΪ��");
			}
			else
			{
				System.out.println("����list��Ϊ�գ�����Ϊ��" + shiTiList.size());
				for(int i = 0;i < shiTiList.size();i++)
				{
					System.out.println(shiTiList.get(i).getTiMu());
				}
			}
			
				
			String result = "";
			for(int i = 0;i < shiTiList.size();i++)
			{
				Question st = shiTiList.get(i);
				result += st.getTiMu() + "," + st.getUserAnswer() + "," + st.getRightAnswer() + "," + st.getUserScore() + "#";
			}
			
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			System.out.println("��ȡ�Ծ��е�����servletִ�н��������ص���ϢΪ�� " + result);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
