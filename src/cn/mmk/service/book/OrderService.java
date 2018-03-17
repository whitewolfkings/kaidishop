package cn.mmk.service.book;

import cn.mmk.bean.BuyCart;
import cn.mmk.bean.book.DeliverWay;
import cn.mmk.bean.book.Order;
import cn.mmk.bean.book.PaymentWay;
import cn.mmk.service.base.DAO;

public interface OrderService extends DAO<Order> {
	/**
	 * 解锁订单
	 * @param orderids 订单号
	 */
	public void unlock(String... orderids);
	/**
	 * 加锁订单
	 * @param orderid 订单号
	 * @param username 加锁用户
	 * @return
	 */
	public Order addLock(String orderid, String username);
	/**
	 * 把订单转为已收货状态
	 * @param orderid 订单号
	 */
	public void turnReceived(String orderid);
	/**
	 * 把订单转为已发货状态
	 * @param orderid 订单号
	 */
	public void turnDelivered(String orderid);
	/**
	 * 把订单转为等待发货状态
	 * @param orderid 订单号
	 */
	public void turnWaitdeliver(String orderid);
	
	/**
	 * 财务确认已付款
	 * @param orderid 订单号
	 */
	public void confirmPayment(String orderid);
	/**
	 * 审核通过订单
	 * @param orderid 订单号
	 */
	public void confirmOrder(String orderid);
	/**
	 * 取消订单
	 * @param orderid 订单号
	 */
	public void cannelOrder(String orderid);
	/**
	 * 生成订单
	 * @param buyCart 购物车
	 * @param username 用户名
	 * @return
	 */
	public Order createOrder(BuyCart buyCart, String username);
	/**
	 * 更新支付方式
	 * @param orderid 订单号
	 * @param paymentWay 支付方式
	 */
	public void updatePaymentWay(String orderid, PaymentWay paymentWay);
	/**
	 * 更新配送方式
	 * @param orderid 订单号
	 * @param deliverWay 配送方式
	 */
	public void updateDeliverWay(String orderid, DeliverWay deliverWay);
	/**
	 * 更新配送费
	 * @param orderid 订单号
	 * @param deliverFee 配送费
	 */
	public void updateDeliverFee(String orderid, float deliverFee);
}
