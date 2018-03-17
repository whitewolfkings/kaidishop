package cn.mmk.web.formbean.shopping;

import cn.mmk.bean.book.PaymentWay;
import cn.mmk.web.formbean.BaseForm;

public class ShoppingFinishForm extends BaseForm {
	private PaymentWay paymentway;
	private String orderid;
	private Float payablefee;
	public PaymentWay getPaymentway() {
		return paymentway;
	}
	public void setPaymentway(PaymentWay paymentway) {
		this.paymentway = paymentway;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Float getPayablefee() {
		return payablefee;
	}
	public void setPayablefee(Float payablefee) {
		this.payablefee = payablefee;
	}
	
}
