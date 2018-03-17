package cn.mmk.bean.book;
/**
 * ����״̬
 *
 */
public enum OrderState {
	
	/** ��ȡ�� */
    CANCEL {public String getName(){return "��ȡ��";}},
    /** ����� */
    WAITCONFIRM {public String getName(){return "�����";}},
    /** �ȴ����� */
    WAITPAYMENT {public String getName(){return "�ȴ�����";}},
    /** ������� */
    ADMEASUREPRODUCT {public String getName(){return "�������";}},
    /** �ȴ����� */
    WAITDELIVER {public String getName(){return "�ȴ�����";}},
    /** �ѷ��� */
    DELIVERED {public String getName(){return "�ѷ���";}},
    /** ���ջ� */
    RECEIVED {public String getName(){return "���ջ�";}};
    
    public abstract String getName();
}
