package cn.mmk.service.book.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cn.mmk.bean.BuyCart;
import cn.mmk.bean.BuyItem;
import cn.mmk.bean.book.DeliverWay;
import cn.mmk.bean.book.Order;
import cn.mmk.bean.book.OrderItem;
import cn.mmk.bean.book.OrderState;
import cn.mmk.bean.book.PaymentWay;
import cn.mmk.bean.product.ProductStyle;
import cn.mmk.bean.user.Buyer;
import cn.mmk.bean.user.ContactInfo;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.book.GeneratedOrderidService;
import cn.mmk.service.book.OrderService;

@Service
public class OrderServiceBean extends DaoSupport<Order> implements OrderService {
	@Resource GeneratedOrderidService generatedOrderidService;
	/*
	@PostConstruct public void init(){
		generatedOrderidService.init();
	}
	*/
	public void unlock(String... orderids){
		if(orderids!=null && orderids.length>0){
			StringBuilder sb = new StringBuilder();
			for(int i=0 ; i < orderids.length ; i++){
				sb.append('?').append(i+2).append(',');
			}
			sb.deleteCharAt(sb.length()-1);
			Query query = em.createQuery("update Order o set o.lockuser=?1 where o.orderid in("+ sb +")");
			query.setParameter(1, null);
			for(int i=0 ; i < orderids.length ; i++){
				query.setParameter(i+2, orderids[i]);
			}
			query.executeUpdate();
		}
		
	}
	
	public Order addLock(String orderid, String username){
		Query query = em.createQuery("update Order o set o.lockuser=?1 where o.orderid=?2 and o.lockuser is null and o.state=?3");
		query.setParameter(1, username);
		query.setParameter(2, orderid);
		query.setParameter(3, OrderState.WAITCONFIRM);
		query.executeUpdate();
		em.flush();
		return find(orderid);
	}
	
	public void turnReceived(String orderid){
		Order order = this.find(orderid);
		if(OrderState.DELIVERED.equals(order.getState())){
			order.setState(OrderState.RECEIVED);
		}
	}
	
	public void turnDelivered(String orderid){
		Order order = this.find(orderid);
		if(OrderState.WAITDELIVER.equals(order.getState())){
			order.setState(OrderState.DELIVERED);
		}
	}
	
	public void turnWaitdeliver(String orderid){
		Order order = this.find(orderid);
		if(OrderState.ADMEASUREPRODUCT.equals(order.getState())){
			order.setState(OrderState.WAITDELIVER);
		}
	}
	
	public void confirmPayment(String orderid){
		Order order = this.find(orderid);
		order.setPaymentstate(true);
		if(OrderState.WAITPAYMENT.equals(order.getState())){
			order.setState(OrderState.ADMEASUREPRODUCT);
		}else if(OrderState.DELIVERED.equals(order.getState()) && PaymentWay.COD.equals(order.getPaymentWay())){
			order.setState(OrderState.RECEIVED);
		}
	}
	
	public void confirmOrder(String orderid){
		Order order = this.find(orderid);
		if(OrderState.WAITCONFIRM.equals(order.getState())){
			if(!PaymentWay.COD.equals(order.getPaymentWay()) && !order.getPaymentstate()){
				order.setState(OrderState.WAITPAYMENT);
			}else{
				order.setState(OrderState.ADMEASUREPRODUCT);
			}
		}
		order.setLockuser(null);
	}
	public void cannelOrder(String orderid){
		Order order = this.find(orderid);
		if(!OrderState.RECEIVED.equals(order.getState())){
			order.setState(OrderState.CANCEL);
		}
		order.setLockuser(null);
	}
	
	public void updateDeliverFee(String orderid, float deliverFee){
		Order order = this.find(orderid);
		order.setDeliverFee(deliverFee);
		order.setTotalPrice(order.getProductTotalPrice()+ order.getDeliverFee());
		order.setPayablefee(order.getTotalPrice());
	}
	
	public void updatePaymentWay(String orderid, PaymentWay paymentWay){
		em.createQuery("update Order o set o.paymentWay=?1 where o.orderid=?2")
			.setParameter(1, paymentWay).setParameter(2, orderid).executeUpdate();
	}
	
	public void updateDeliverWay(String orderid, DeliverWay deliverWay){
		/*
		 * 下面语句在hibernate中执行出错,这是hibernate的bug导致的,如果用在TopLink上,下面语句能成功执行
		 * em.createQuery("update OrderDeliverInfo o set o.deliverWay=?1 where o.order.orderid=?2")
			.setParameter(1, deliverWay).setParameter(2, orderid).executeUpdate();*/
		Order order = this.find(orderid);
		order.getOrderDeliverInfo().setDeliverWay(deliverWay);
	}
	
	public Order createOrder(BuyCart buyCart, String username){
		Order order = new Order();
		Buyer buyer = em.find(Buyer.class, username);
		order.setBuyer(buyer);
		order.setDeliverFee(buyCart.getDeliveFee());
		order.setNote(buyCart.getNote());
		order.setOrderContactInfo(buyCart.getContactInfo());
		order.setOrderDeliverInfo(buyCart.getDeliverInfo());
		order.setState(OrderState.WAITCONFIRM);
		order.setPaymentWay(buyCart.getPaymentWay());		
		order.setProductTotalPrice(buyCart.getTotalSellPrice());
		order.setTotalPrice(buyCart.getOrderTotalPrice());
		order.setPayablefee(buyCart.getOrderTotalPrice());
		for(BuyItem item : buyCart.getItems()){
			ProductStyle style = item.getProduct().getStyles().iterator().next();
			OrderItem oitem = new OrderItem(item.getProduct().getName(), item.getProduct().getId(),
					item.getProduct().getSellprice(), item.getAmount(), style.getName(), style.getId());
			order.addOrderItem(oitem);
		}
		if(buyer.getContactInfo()==null){
			buyer.setContactInfo(new ContactInfo());
			buyer.getContactInfo().setAddress(order.getOrderContactInfo().getAddress());
			buyer.getContactInfo().setPostalcode(order.getOrderContactInfo().getPostalcode());
			buyer.getContactInfo().setPhone(order.getOrderContactInfo().getTel());
			buyer.getContactInfo().setMobile(order.getOrderContactInfo().getMobile());
			if(buyer.getRealname()==null) buyer.setRealname(order.getOrderContactInfo().getBuyerName());
			if(buyer.getGender()==null) buyer.setGender(order.getOrderContactInfo().getGender());
		}
		order.setOrderid(buildOrderid2(order.getCreateDate()));
		this.save(order);
		return order;		
	}
	
	/**
	 * 生成订单号,订单号的组成:两位年份两位月份两位日期+(流水号,不够8位前面补零),如:09120200000001
	 * @return
	 */
	private String buildOrderid2(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		StringBuilder sb = new StringBuilder(dateFormat.format(date));
		sb.append(fillZero(8, String.valueOf(generatedOrderidService.buildOrderid())));
		return sb.toString();
	}
	/**
	 * 生成订单号,订单号的组成:两位年份两位月份两位日期+(当天订单总数+1),如果订单总数的长度不够8位,前面补零,如:09120200000001
	 * @return
	 */
	private String buildOrderid(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		StringBuilder sb = new StringBuilder(dateFormat.format(date));
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
		    Date now = dateFormat.parse(dateFormat.format(date));// 2009-12-02 00:00
		    Query query = em.createQuery("select count(o) from Order o where o.createDate>=?1");
			query.setParameter(1, now);
			long count = (Long)query.getSingleResult();
			sb.append(fillZero(8, String.valueOf(count+1)));
		} catch (ParseException e) {
			throw new RuntimeException("生成订单号失败");
		}		
		return sb.toString();
	}
	/**
	 * 补零
	 * @param length 补零后的长度
	 * @param source 需要补零的长符串
	 * @return
	 */
	private String fillZero(int length, String source) {//7
		StringBuilder result = new StringBuilder(source);
		for(int i=result.length(); i<length ; i++){
			result.insert(0, '0');
		}
		return result.toString();
	}
}
