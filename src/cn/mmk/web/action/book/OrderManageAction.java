package cn.mmk.web.action.book;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.book.Message;
import cn.mmk.bean.book.Order;
import cn.mmk.bean.book.OrderContactInfo;
import cn.mmk.bean.book.OrderDeliverInfo;
import cn.mmk.bean.book.OrderItem;
import cn.mmk.service.book.MessageService;
import cn.mmk.service.book.OrderContactInfoService;
import cn.mmk.service.book.OrderDeliverInfoService;
import cn.mmk.service.book.OrderItemService;
import cn.mmk.service.book.OrderService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.book.OrderForm;
/**
 * 订单管理
 *
 */
@Controller("/control/order/manage")
public class OrderManageAction extends DispatchAction {
	@Resource OrderContactInfoService contactInfoService;
	@Resource OrderDeliverInfoService deliverInfoService;
	@Resource OrderService orderService;
	@Resource OrderItemService itemService;
	@Resource MessageService messageService;
	
	/**
	 * 客服留言添加界面
	 */
	@Permission(module="order", privilege="view")
	public ActionForward addMessageUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addmessage");
	}
	
	/**
	 * 添加客服留言
	 */
	@Permission(module="order", privilege="view")
	public ActionForward addMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		Message msg = new Message();
		msg.setContent(formbean.getMessage());
		msg.setUsername(WebUtil.getEmployee(request).getUsername());
		msg.setOrder(new Order(formbean.getOrderid()));
		
		messageService.save(msg);
		
		request.setAttribute("message", "留言保存成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.view")+"?orderid="+ formbean.getOrderid());
		return mapping.findForward("message");
	}
	/**
	 * 强行解锁订单
	 */
	@Permission(module="order", privilege="allUnLock")
	public ActionForward allUnLock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.unlock(formbean.getOrderids());
		
		request.setAttribute("message", "解锁成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("contorl.lockorder.list"));
		return mapping.findForward("message");
	}
	/**
	 * 解锁订单
	 */
	@Permission(module="order", privilege="view")
	public ActionForward employeeUnlockOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.unlock(formbean.getOrderid());
			
		request.setAttribute("directUrl", SiteUrl.readUrl("control.order.list"));
		return mapping.findForward("directUrl");
	}
	
	/**
	 * 把订单设置为已收货状态
	 */
	@Permission(module="order", privilege="turnReceived")
	public ActionForward turnReceived(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.turnReceived(formbean.getOrderid());
		
		request.setAttribute("message", "设置成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.list")+ "?state=DELIVERED");
		return mapping.findForward("message");
	}
	/**
	 * 把订单设置为已发货状态
	 */
	@Permission(module="order", privilege="turnDelivered")
	public ActionForward turnDelivered(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.turnDelivered(formbean.getOrderid());
		
		request.setAttribute("message", "设置成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.list")+ "?state=WAITDELIVER");
		return mapping.findForward("message");
	}
	/**
	 * 把订单设置为等待发货状态
	 */
	@Permission(module="order", privilege="turnWaitdeliver")
	public ActionForward turnWaitdeliver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.turnWaitdeliver(formbean.getOrderid());
		
		request.setAttribute("message", "设置成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.list")+ "?state=ADMEASUREPRODUCT");
		return mapping.findForward("message");
	}
	/**
	 * 财务确认已付款
	 */
	@Permission(module="order", privilege="confirmPayment")
	public ActionForward confirmPayment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.confirmPayment(formbean.getOrderid());
		
		request.setAttribute("message", "订单已设置为已支付");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.list")+ "?state=WAITPAYMENT");
		return mapping.findForward("message");
	}
	/**
	 * 审核通过订单
	 */
	@Permission(module="order", privilege="confirmOrder")
	public ActionForward confirmOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.confirmOrder(formbean.getOrderid());
		
		request.setAttribute("message", "订单审核成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.list"));
		return mapping.findForward("message");
	}
	/**
	 * 取消订单
	 */
	@Permission(module="order", privilege="cancelOrder")
	public ActionForward cancelOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.cannelOrder(formbean.getOrderid());
		
		request.setAttribute("message", "订单取消成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.list"));
		return mapping.findForward("message");
	}
	/**
	 * 打印发货单
	 */
	@Permission(module="order", privilege="view")
	public ActionForward printOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		request.setAttribute("order", orderService.find(formbean.getOrderid()));
		return mapping.findForward("print");
	}
	
	
	/**
	 * 订购者信息修改界面
	 */
	@Permission(module="order", privilege="modifyContactInfo")
	public ActionForward modifyContactInfoUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		OrderContactInfo contact = contactInfoService.find(formbean.getContactid());
		formbean.setBuyer(contact.getBuyerName());
		formbean.setBuyer_gender(contact.getGender());
		formbean.setBuyer_address(contact.getAddress());
		formbean.setBuyer_mobile(contact.getMobile());
		formbean.setBuyer_postalcode(contact.getPostalcode());
		formbean.setBuyer_tel(contact.getTel());
		return mapping.findForward("modifyContact");
	}
	
	/**
	 * 修改订购者信息
	 */
	@Permission(module="order", privilege="modifyContactInfo")
	public ActionForward modifyContactInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		OrderContactInfo contact = contactInfoService.find(formbean.getContactid());
		contact.setBuyerName(formbean.getBuyer());
		contact.setGender(formbean.getBuyer_gender());
		contact.setAddress(formbean.getBuyer_address());
		contact.setMobile(formbean.getBuyer_mobile());
		contact.setPostalcode(formbean.getBuyer_postalcode());
		contact.setTel(formbean.getBuyer_tel());
		contactInfoService.update(contact);
		request.setAttribute("message", "订购者信息修改成功");
		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.view")+"?orderid="+ contact.getOrder().getOrderid());
		return mapping.findForward("message");
	}
	
	/**
	 * 配送信息修改界面
	 */
	@Permission(module="order", privilege="modifyDeliverInfo")
	public ActionForward modifyDeliverInfoUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		OrderDeliverInfo deliverInfo = deliverInfoService.find(formbean.getDeliverid());
		formbean.setEmail(deliverInfo.getEmail());
		formbean.setRecipients(deliverInfo.getRecipients());
		formbean.setGender(deliverInfo.getGender());
		formbean.setAddress(deliverInfo.getAddress());
		formbean.setPostalcode(deliverInfo.getPostalcode());
		formbean.setTel(deliverInfo.getTel());
		formbean.setMobile(deliverInfo.getMobile());
		//BeanUtils.copyProperties(formbean, deliverInfo);//对性能有所影响
		return mapping.findForward("modifyDeliver");
	}
	/**
	 * 修改配送信息
	 */
	@Permission(module="order", privilege="modifyDeliverInfo")
	public ActionForward modifyDeliverInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		OrderDeliverInfo deliverInfo = deliverInfoService.find(formbean.getDeliverid());
		deliverInfo.setEmail(formbean.getEmail());
		deliverInfo.setRecipients(formbean.getRecipients());
		deliverInfo.setGender(formbean.getGender());
		deliverInfo.setAddress(formbean.getAddress());
		deliverInfo.setPostalcode(formbean.getPostalcode());
		deliverInfo.setTel(formbean.getTel());
		deliverInfo.setMobile(formbean.getMobile());
		deliverInfoService.update(deliverInfo);
		
		request.setAttribute("message", "配送信息修改成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.view")+"?orderid="+ deliverInfo.getOrder().getOrderid());
		return mapping.findForward("message");
	}
	
	
	/**
	 * 支付修改界面
	 */
	@Permission(module="order", privilege="modifyPaymentWay")
	public ActionForward modifyPaymentWayUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		Order order = orderService.find(formbean.getOrderid());
		formbean.setPaymentWay(order.getPaymentWay());
		request.setAttribute("deliverWay", order.getOrderDeliverInfo().getDeliverWay());
		return mapping.findForward("modifyPaymentWay");
	}
	/**
	 * 修改支付方式
	 */
	@Permission(module="order", privilege="modifyPaymentWay")
	public ActionForward modifyPaymentWay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.updatePaymentWay(formbean.getOrderid(),formbean.getPaymentWay());		
		request.setAttribute("message", "支付方式修改成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.view")+"?orderid="+ formbean.getOrderid());
		return mapping.findForward("message");
	}
	/**
	 * 送货方式修改界面
	 */
	@Permission(module="order", privilege="modifyDeliverWay")
	public ActionForward modifyDeliverWayUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		Order order = orderService.find(formbean.getOrderid());
		formbean.setDeliverWay(order.getOrderDeliverInfo().getDeliverWay());
		formbean.setPaymentWay(order.getPaymentWay());
		return mapping.findForward("modifyDeliverWay");
	}
	/**
	 * 修改配送方式
	 */
	@Permission(module="order", privilege="modifyDeliverWay")
	public ActionForward modifyDeliverWay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.updateDeliverWay(formbean.getOrderid(), formbean.getDeliverWay());		
		request.setAttribute("message", "配送方式修改成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.view")+"?orderid="+ formbean.getOrderid());
		return mapping.findForward("message");
	}
	/**
	 * 商品购买数量修改界面
	 */
	@Permission(module="order", privilege="modifyProductAmount")
	public ActionForward modifyProductAmountUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		OrderItem item = itemService.find(formbean.getOrderitemid());
		formbean.setAmount(item.getAmount());
		formbean.setOrderid(item.getOrder().getOrderid());
		return mapping.findForward("modifyProductAmount");
	}
	/**
	 * 修改商品购买数量
	 */
	@Permission(module="order", privilege="modifyProductAmount")
	public ActionForward modifyProductAmount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		itemService.updateAmount(formbean.getOrderitemid(), formbean.getAmount());
		
		request.setAttribute("message", "商品购买数量修改成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.view")+"?orderid="+ formbean.getOrderid());
		return mapping.findForward("message");
	}
	/**
	 * 删除订单项
	 */
	@Permission(module="order", privilege="deleteOrderItem")
	public ActionForward deleteOrderItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		itemService.delete((Serializable)formbean.getOrderitemid());
		
		request.setAttribute("message", "订单项删除成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.view")+"?orderid="+ formbean.getOrderid());
		return mapping.findForward("message");
	}
	/**
	 * 配送费修改界面
	 */
	@Permission(module="order", privilege="modifyDeliverFee")
	public ActionForward modifyDeliverFeeUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		Order order = orderService.find(formbean.getOrderid());
		formbean.setFee(order.getDeliverFee());
		
		return mapping.findForward("modifydeliverFee");
	}
	
	/**
	 * 修改配送费
	 */
	@Permission(module="order", privilege="modifyDeliverFee")
	public ActionForward modifyDeliverFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		orderService.updateDeliverFee(formbean.getOrderid(), formbean.getFee());
		
		request.setAttribute("message", "配送费修改成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.order.view")+"?orderid="+ formbean.getOrderid());
		return mapping.findForward("message");
	}
}
