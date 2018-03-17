package cn.mmk.service.book;

import cn.mmk.bean.book.OrderItem;
import cn.mmk.service.base.DAO;

public interface OrderItemService extends DAO<OrderItem> {
	/**
	 * 更新商品购买数量
	 * @param itemid 订单项
	 * @param amount 购买数量
	 */
	public void updateAmount(Integer itemid, int amount);
}
