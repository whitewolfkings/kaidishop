package cn.mmk.bean.book;
/**
 * ֧����ʽ
 */
public enum PaymentWay {
	/** ����֧�� */
	NET{
		public String getName(){return "����֧��";}
	},
	/** �������� */
	COD{
		public String getName(){return "��������";}
	},
	/** ���е�� */
	BANKREMITTANCE{
		public String getName(){return "���е��";}
	},
	/** �ʾֻ�� */
	POSTOFFICEREMITTANCE{
		public String getName(){return "�ʾֻ��";}
	};
	public abstract String getName();
}
