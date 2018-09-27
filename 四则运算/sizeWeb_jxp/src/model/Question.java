package model;

import java.util.Stack;

//---�洢���⣬���������
public class Question {

	private int id,userScore,length,shiJuanID;
	private String tiMu,rightAnswer,userAnswer,userName,logicOrder;
	
	
	public Question(){}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShiJuanID() {
		return shiJuanID;
	}

	public void setShiJuanID(int shiJuanID) {
		this.shiJuanID = shiJuanID;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getTiMu() {
		return tiMu;
	}

	public void setTiMu(String tiMu) {
		this.tiMu = tiMu;
		try {
			expressCalculate();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// �����
		this.length = (tiMu.split(" ").length + 1) / 2;
	}
	
	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getLogicOrder() {
		return logicOrder;
	}

	public void setLogicOrder(String logicOrder) {
		this.logicOrder = logicOrder;
	}

	// ���ʽ����,����Ϊ�ַ������͵�����ʽ
		private void expressCalculate() throws MyException {
			if(this.tiMu == null)
			{
				throw new MyException("������Ч");
			}
			String express = this.tiMu;
			
			
			Stack<String> num = new Stack<String>();
			Stack<String> symbolS = new Stack<String>();
			symbolS.push("#");
			express += "#";
			String order = "";
			char ch;
			int i = 0;
			ch = express.charAt(i);
			while ((!symbolS.peek().equals("#")) || (ch != '#')) {// whileѭ����ʼ
				if (isNumber(ch)) {// �����Ĳ��ǿո�˵����ʼ��������
					String readNumStr = "";
					while (true) {
						readNumStr += ch;
						ch = express.charAt(++i);
						if (ch == ' ' || ch == '#' || ch == ')') {// �������ǿո�,��˵������������
							break;
						}
					}
					num.push(readNumStr);
				} else if (ch == ' ') {
					if ((i + 1) < express.length()) {// δ���ַ���ĩβ
						ch = express.charAt(++i);
					}
				}else {// �������������
					char compare = priorityCompare(symbolS.peek(), ch + "");

					if (compare == '=') {// �����ȼ���ȣ���˵��ch�������ţ�ջ��Ϊ�����ţ���ʱ��ջ����������ȡ��һ���ַ�
						symbolS.pop();
						ch = express.charAt(++i);
					} else if (compare == '>') {// ch�����ȼ�С��ջ�������ȼ���Ҫ˵��ջ���������Ӧ���ȼ��㣬����Ӧ��ջ����
												// ��������������������һ�������
						String bStr = num.pop();
						String aStr = num.pop();
						String symbolT = symbolS.pop();
						// ������ֱ��ʽ
						String c = yunSuan(aStr, bStr, symbolT);
						if (c.equals("ERROR")) {// ������㺯������error��˵��������̳����˸�����˵��������ʽ������Ҫ��ֹͣ���㣬������Ϊerror�����أ�
							this.rightAnswer = "ERROR";
							return;
						} else {// ��������������򽫼�����ѹջ
							order += aStr + "," + symbolT + "," + bStr + ",";// ��������ӱ��ʽ�ӽ�����˳���ַ����У��������Ͳ������ö��Ÿ���
							num.push(c);
						}
					} else if(compare == 'E')
					{
						this.rightAnswer = "ERROR";
						return;
					} else {// ˵��ch���ȼ�����ջ��Ԫ�ص����ȼ�����Ӧ��chѹջ����ȡ��һ�������
						symbolS.push(ch + "");
						if ((i + 1) < express.length()) {
							ch = express.charAt(++i);
						}
					}

				}
			}
			this.rightAnswer = num.pop();
			this.logicOrder = order;
		}

		// �ж�ch�Ƿ�Ϊ����
		private boolean isNumber(char ch) {
			if (ch >= '0' && ch <= '9') {
				return true;
			}
			return false;
		}

		/*
		 * �ӱ��ʽ���㣬����Ϊ�������������ַ�����ʽ����һ���������ҲΪ�ַ������� ���ؼ��������ַ�����ʽ
		 * �������������ָ����������Ϊ0��������ķ�ĸΪ0�򷵻�ERROR
		 * 
		 */
		private String yunSuan(String aStr, String bStr, String symbol) throws MyException {
			if(aStr == null || bStr == null || symbol == null)
			{
				throw new MyException("�ӱ��ʽ���ִ���");
			}
			int adivIndex = aStr.indexOf("/");
			int bdivIndex = bStr.indexOf("/");
			if ((adivIndex == -1) && (bdivIndex == -1)) {// a.b��������
				int a = Integer.parseInt(aStr);
				int b = Integer.parseInt(bStr);
				switch (symbol.charAt(0)) {
				case '+':
					return a + b + "";
				case '-': {
					if (a < b) {
						return "ERROR";
					}
					return a - b + "";
				}
				case '*': {
					return a * b + "";
				}
				case '/': {
					if (b == 0) {
						return "ERROR";
					} else if (a % b == 0) {
						return a / b + "";
					}
					return new FenShu(a, b).toString();
				}
				default:
					return "ERROR";
				}
			} else {// a,b�д��ڷ�������a,b������������������
				FenShu a = new FenShu(aStr);
				FenShu b = new FenShu(bStr);
				switch (symbol.charAt(0)) {
				case '+':
					return a.add(b).toString();
				case '-':
				{
					FenShu c = a.subtract(b);
					if(c.getNumerator() < 0)
					{
						return "ERROR";
					}
					return c.toString();
				}
				case '*':
					return a.multiply(b).toString();
				case '/':
					return a.divide(b).toString();
				default:
					return "ERROR";
				}
			}
		}

		// �ж���������ȼ�
		private char priorityCompare(String a, String b) {
			char[][] priority = { { '>', '>', '<', '<', '<', '>', '>' }, { '>', '>', '<', '<', '<', '>', '>' },
					{ '>', '>', '>', '>', '<', '>', '>' }, { '>', '>', '>', '>', '<', '>', '>' },
					{ '<', '<', '<', '<', '<', '=', '>' }, { '>', '>', '>', '>', ' ', '>', '>' },
					{ '<', '<', '<', '<', '<', ' ', '=' } };
			int a_index = index_symbol(a);
			int b_index = index_symbol(b);
			if(a_index == -1 || b_index == -1)
			{
				return 'E';
			}
			return priority[a_index][b_index];
		}

		// ��ȡ�������Ӧ���±�
		private int index_symbol(String a) {
			String p = "+-*/()#";
			// System.out.println("�ж��������Ӧ���±꣺" + a);
			return p.indexOf(a);
		}
		
	
}
