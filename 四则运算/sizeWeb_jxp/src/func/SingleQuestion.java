package func;

import java.util.Random;

import model.FenShu;
import model.MyException;
import model.Question;

//---����һ�����⣬���������
public class SingleQuestion {
	// ��������ʽ
		public static Question getExpress(int maxNum, int hasKuoHao, int hasChengChu,int type) throws MyException {

			if(maxNum <= 0)
			{
				throw new MyException("�����ֵӦΪ����");
			}
			
			Question stb = new Question();

			Random rd = new Random();
			char[] fuHao = { '+', '-', '*', '/' };
			int symbolNum = 2 + hasChengChu * 2;
			while (true) {
				int[] bracket = null;// �洢����λ��
				int expressLength = rd.nextInt(3) + 2;// �������һ��2~4֮���������Ϊ������ʽ���������ĸ���
				stb.setLength(expressLength);
				String[] number = new String[expressLength];// �洢������������
				String[] symbol = new String[expressLength - 1];// �洢�����������

				String express = "";
				number[0] = getOperatorNumber(type, maxNum);
				for (int i = 0; i < expressLength - 1; i++) {
					symbol[i] = fuHao[rd.nextInt(symbolNum)] + "";// ���������
					number[i + 1] = getOperatorNumber(type, maxNum);
				}

				if (hasKuoHao == 1) {
					// ��Ҫ������
					bracket = randomAddBracket(expressLength);
				}

				// �������ʽ
				for (int i = 0; i < expressLength; i++) {
					// ���������
					if (hasKuoHao == 1) {
						for (int j = 0; j < bracket[i]; j++) {
							express += "(";
						}
					}

					express += number[i];// ����������

					// ���������
					if (hasKuoHao == 1) {
						for (int j = 0; j > bracket[i]; j--) {
							express += ")";
						}
					}

					if (i != expressLength - 1) {
						express += " " + symbol[i] + " ";// �����������������ӿո������������ָ�
					}

				}
				stb.setTiMu(express);
				if (!(stb.getRightAnswer().equals("ERROR"))) {
					// System.out.println("���ɵ�����ʽΪ��" + express + "=" + result[0]);
					return stb;
				}
			}

		}

		// ����������ţ�����Ϊ����ʽ���������ĸ���
		private static int[] randomAddBracket(int length) throws MyException {
			if(length <= 1)
			{
				throw new MyException("����ʽ���Ȳ���С��2");
			}
			int[] brackets = new int[length];
			for (int i = 0; i < brackets.length; i++)
				brackets[i] = 0;
			Random rd = new Random();
			for (int i = 2; i < length; i++) {// ��ӵ����ų��ȣ����Ű�Χ���������ĸ�����
				for (int j = 0; j < length - i + 1; j++) {
					int t = rd.nextInt(2);// �������0��1��0���������ţ�1���������
					if (t == 1) {
						if (brackets[j] >= 0 && brackets[j + i - 1] <= 0) {// Ҫ�ӵ����ŵĵ�һ����������Χû�������ţ���
																			// ���һ����������Χû��������
							int counteract1 = 0,counteract2 = 0,counteract3 = 0;
							for (int k = j; k < j + i; k++) {// ��Ҫ�ӵ�����֮���������������Ӧ��brackets��ӣ�
																// �����Ϊ0˵���������֮���������ƥ��ģ�����������Ž�������
								counteract1 += brackets[k];
							}
							for (int k = 0; k < j - 1; k++) {// ��Ҫ�ӵ�����֮ǰ��������������Ӧ��brackets��ӣ�
								// �����Ϊ0˵���������֮���������ƥ��ģ�����������Ž�������
								counteract2 += brackets[k];
							}
							for (int k = j + i; k < length; k++) {// ��Ҫ�ӵ�����֮���������������Ӧ��brackets��ӣ�
								// �����Ϊ0˵���������֮���������ƥ��ģ�����������Ž�������
								counteract3 += brackets[k];
							}
							
							if (counteract1 == 0 && counteract2 == 0 && counteract3 == 0) {
								brackets[j]++;
								brackets[j + i - 1]--;
								j += i;
							}
						}
					}
				}
			}
			return brackets;
		}

		// �������һ���������� type==0��������������type==1���������������maxNum������ֵ��Χ 0~(maxNum-1) )
		private static String getOperatorNumber(int type, int maxNum) throws MyException {
			if(maxNum <= 0)
			{
				throw new MyException("�����ֵӦΪ����");
			}
			Random rd = new Random();
			int a;
			while (true) {
				a = rd.nextInt(maxNum);
				if (type == 0) {// �������һ������
					return "" + a;
				} else {// �������һ�������
					if (a == 0) {
						continue;
					}
					int b = rd.nextInt(a);
					FenShu c = new FenShu(b, a);
					return c.toString();
				}
			}
		}
		
	//-------------�ж��Ƿ��ظ������������
		
		public static boolean calculateOrderSame(Question a, Question b) throws MyException {

			if(a == null || b == null)
			{
				throw new MyException("������Ч��");
			}
			//�Ƚ���������ʽ������������
			if(a.getLength() != b.getLength())
			{
				return false;
			}
			
			//�Ƚ�������ʽ�Ĵ��Ƿ���ͬ
			if(!a.getRightAnswer().equals(b.getRightAnswer()))
			{
				return false;
			}
			
			// ȡ������ʽ������˳���ַ�����
			String aorder = a.getLogicOrder();
			String border = b.getLogicOrder();

			// ��a,b����ʽ������˳���ַ������зָ����ȡ��ÿһ���������������
			String[] asplit = aorder.split(",");
			String[] bsplit = border.split(",");

			int n = a.getLength() - 1;//����n���ӱ��ʽ
			
			for(int i = 0;i < n;i++)
			{
				//ȡa����ʽ���ӱ��ʽ������������a1,a2,�����af,������ar
				String a1 = asplit[0 + i * 3];
				String af = asplit[1 + i * 3];
				String a2 = asplit[2 + i * 3];
				//ȡb����ʽ���ӱ��ʽ������������b1,b2,�����bf,������br
				String b1 = bsplit[0 + i * 3];
				String bf = bsplit[1 + i * 3];
				String b2 = bsplit[2 + i * 3];

				if(af.equals(bf))
				{
					//���ӱ��ʽ������ͬ
					if(a1.equals(b1) && a2.equals(b2))
					{
						continue;//���ӱ��ʽ��ͬ�������ж���һ���ӱ��ʽ
					}
					else if(  (af.equals("+") || af.equals("*"))   &&   a1.equals(b2)  && a2.equals(b1)   )
					{
						continue;//���ӱ��ʽ��ͬ�������ж���һ���ӱ��ʽ
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		
	
}
