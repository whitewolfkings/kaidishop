package cn.mmk.bean.book;
/**
 * �ͻ���ʽ
 */
public enum DeliverWay {
	/** ƽ�� */
	GENERALPOST{
		public String getName(){return "ƽ��";}
	},
	/** ����ͻ����� */
	EXPRESSDELIVERY{
		public String getName(){return "����ͻ�����";}
	},
	/** �Ӽ�����ͻ����� */
	EXIGENCEEXPRESSDELIVERY{
		public String getName(){return "�Ӽ�����ͻ�����";}
	},
	/** �����ؿ�ר��EMS */
	EMS{
		public String getName(){return "�����ؿ�ר��EMS";}
	};
	public abstract String getName();
}
