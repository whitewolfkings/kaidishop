package cn.mmk.service.book;

import cn.mmk.bean.book.GeneratedOrderid;
import cn.mmk.service.base.DAO;

public interface GeneratedOrderidService extends DAO<GeneratedOrderid> {
	/**
	 * ���ɶ�����ˮ��
	 * @return
	 */
	public int buildOrderid();
	/**
	 * ��ʼ��
	 */
	public void init();
}
