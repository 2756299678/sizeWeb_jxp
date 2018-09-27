package func;

import java.util.ArrayList;
import java.util.List;

import model.MyException;
import model.Question;

//---����n�����ظ�������
public class Questions {

	// ������������ʽ�����������,typeΪ����ʽ���� 0��������ʽ��1���������ʽ
	public static List<Question> createYunSuanShi(int hasChengChu,int hasKuoHao, int maxNum, int n, int type) throws MyException {
		int i = 0;
		if(n <= 0)
		{
			throw new MyException("�������������ô���,ӦΪ����");
		}
		List<Question> list = new ArrayList<Question>();
		Question stb = null;
		// ShiTiDAO std = new ShiTiDAO();
		while (i < n) {
			stb = SingleQuestion.getExpress(maxNum, hasKuoHao,hasChengChu, type);

			// �����ظ�
			boolean chongFu = false;
			for (int j = 0; j < i; j++) {
				Question t = list.get(j);
				if (SingleQuestion.calculateOrderSame(stb, t)) {
					chongFu = true;
					System.out.println("�����ظ�������ʽһ��" + t.getTiMu() + " = " + t.getRightAnswer() + " ����˳��"
							+ t.getLogicOrder() + " ������������" + t.getLength());
					System.out.println("�����ظ�������ʽ����" + stb.getTiMu() + " = " + stb.getRightAnswer() + " ����˳��"
							+ stb.getLogicOrder() + " ������������" + stb.getLength());
					System.out.println("\n\n");
					break;
				}
			}
			if (chongFu == false) {
				list.add(stb);
				i++;
			}
		}
		return list;
	}
}
