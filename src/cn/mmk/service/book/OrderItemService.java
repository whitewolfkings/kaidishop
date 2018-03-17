package cn.mmk.service.book;

import cn.mmk.bean.book.OrderItem;
import cn.mmk.service.base.DAO;

public interface OrderItemService extends DAO<OrderItem> {
	/**
	 * ������Ʒ��������
	 * @param itemid ������
	 * @param amount ��������
	 */
	public void updateAmount(Integer itemid, int amount);
}
