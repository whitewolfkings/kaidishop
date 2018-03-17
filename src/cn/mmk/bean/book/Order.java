package cn.mmk.bean.book;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.mmk.bean.user.Buyer;
/**
 * 订单
 */
@Entity @Table(name="t_order")
public class Order {
	/* 订单号 */
	private String orderid;
	/* 所属用户 */
	private Buyer buyer;
	/* 订单创建时间 */
	private Date createDate = new Date();
	/* 订单状态 */
	private OrderState state;
	/* 商品总金额 */
	private Float productTotalPrice = 0f;
	/* 配送费 */
	private Float deliverFee = 0f;
	/* 订单总金额 */
	private Float totalPrice= 0f;
	/* 应付款(实际需要支付的费用) */
	private Float payablefee = 0f;
	/* 顾客附言 */
	private String note;
	/* 支付方式 */
	private PaymentWay paymentWay;
	/* 支付状态 */
    private Boolean paymentstate = false;
    /* 订单配送信息 */
	private OrderDeliverInfo orderDeliverInfo;
	/* 订单购买者联系信息 */
	private OrderContactInfo orderContactInfo;
	/* 订单项 */
	private Set<OrderItem> items = new HashSet<OrderItem>();
	/* 对订单进行加锁的用户,如果值为null,代表订单未被加锁,否则,订单被加锁 */
	private String lockuser;
	/* 客服留言 */
	private Set<Message> msgs = new HashSet<Message>();
	
	public Order(){}
	
	public Order(String orderid) {
		this.orderid = orderid;
	}
	@OneToMany(mappedBy="order",cascade=CascadeType.REMOVE)
	public Set<Message> getMsgs() {
		return msgs;
	}
	public void setMsgs(Set<Message> msgs) {
		this.msgs = msgs;
	}
	@Column(length=20)
	public String getLockuser() {
		return lockuser;
	}
	public void setLockuser(String lockuser) {
		this.lockuser = lockuser;
	}
	@Id @Column(length=14)
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="username")
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	@Temporal(TemporalType.TIMESTAMP) @Column(nullable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Enumerated(EnumType.STRING) @Column(length=16, nullable=false)
	public OrderState getState() {
		return state;
	}
	public void setState(OrderState state) {
		this.state = state;
	}
	@Column(nullable=false)
	public Float getProductTotalPrice() {
		return productTotalPrice;
	}
	public void setProductTotalPrice(Float productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}
	@Column(nullable=false)
	public Float getDeliverFee() {
		return deliverFee;
	}
	public void setDeliverFee(Float deliverFee) {
		this.deliverFee = deliverFee;
	}
	@Column(nullable=false)
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Column(nullable=false)
	public Float getPayablefee() {
		return payablefee;
	}
	public void setPayablefee(Float payablefee) {
		this.payablefee = payablefee;
	}
	@Column(length=100)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Enumerated(EnumType.STRING) @Column(length=20,nullable=false)
	public PaymentWay getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(PaymentWay paymentWay) {
		this.paymentWay = paymentWay;
	}
	@Column(nullable=false)
	public Boolean getPaymentstate() {
		return paymentstate;
	}
	public void setPaymentstate(Boolean paymentstate) {
		this.paymentstate = paymentstate;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="deliver_id")
	public OrderDeliverInfo getOrderDeliverInfo() {
		return orderDeliverInfo;
	}
	public void setOrderDeliverInfo(OrderDeliverInfo orderDeliverInfo) {
		this.orderDeliverInfo = orderDeliverInfo;
	}
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="contact_id")
	public OrderContactInfo getOrderContactInfo() {
		return orderContactInfo;
	}
	public void setOrderContactInfo(OrderContactInfo orderContactInfo) {
		this.orderContactInfo = orderContactInfo;
	}
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	/**
	 * 添加订单项
	 * @param item
	 */
	public void addOrderItem(OrderItem item){
		this.items.add(item);
		item.setOrder(this);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderid == null) ? 0 : orderid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Order other = (Order) obj;
		if (orderid == null) {
			if (other.orderid != null)
				return false;
		} else if (!orderid.equals(other.orderid))
			return false;
		return true;
	}
	
}
