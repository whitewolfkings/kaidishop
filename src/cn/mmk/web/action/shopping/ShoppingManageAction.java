package cn.mmk.web.action.shopping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.BuyCart;
import cn.mmk.bean.book.DeliverWay;
import cn.mmk.bean.book.Order;
import cn.mmk.bean.book.OrderContactInfo;
import cn.mmk.bean.book.OrderDeliverInfo;
import cn.mmk.service.book.OrderService;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.formbean.shopping.DeliverForm;

@Controller("/customer/shopping/manage")
public class ShoppingManageAction extends DispatchAction {
	@Resource OrderService orderService;
	/**
	 * 保存配送信息
	 */
	public ActionForward saveDeliverInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverForm formbean = (DeliverForm)form;		
		BuyCart cart = WebUtil.getBuyCart(request);
		if(cart.getDeliverInfo()==null) cart.setDeliverInfo(new OrderDeliverInfo());
		cart.getDeliverInfo().setRecipients(formbean.getRecipients());
		cart.getDeliverInfo().setGender(formbean.getGender());
		cart.getDeliverInfo().setAddress(formbean.getAddress());
		cart.getDeliverInfo().setPostalcode(formbean.getPostalcode());
		cart.getDeliverInfo().setEmail(formbean.getEmail());
		cart.getDeliverInfo().setTel(formbean.getTel());
		cart.getDeliverInfo().setMobile(formbean.getMobile());
		
		cart.setBuyerIsrecipients(formbean.getBuyerIsrecipients());
		
		if(cart.getContactInfo()==null) cart.setContactInfo(new OrderContactInfo());
		if(cart.getBuyerIsrecipients()){//判断收货人与订购者是否相同
			cart.getContactInfo().setBuyerName(formbean.getRecipients());
			cart.getContactInfo().setGender(formbean.getGender());
			cart.getContactInfo().setAddress(formbean.getAddress());
			cart.getContactInfo().setPostalcode(formbean.getPostalcode());
			cart.getContactInfo().setTel(formbean.getTel());
			cart.getContactInfo().setMobile(formbean.getMobile());
			cart.getContactInfo().setEmail(formbean.getEmail());
		}else{
			cart.getContactInfo().setBuyerName(formbean.getBuyer());
			cart.getContactInfo().setGender(formbean.getBuyer_gender());
			cart.getContactInfo().setAddress(formbean.getBuyer_address());
			cart.getContactInfo().setPostalcode(formbean.getBuyer_postalcode());
			cart.getContactInfo().setTel(formbean.getBuyer_tel());
			cart.getContactInfo().setMobile(formbean.getBuyer_mobile());
			cart.getContactInfo().setEmail(WebUtil.getBuyer(request).getEmail());
		}
		String url = "/customer/shopping/paymentway.do";
		if(formbean.getDirectUrl()!=null ) url = formbean.getDirectUrl();
		request.setAttribute("directUrl", url);
		return mapping.findForward("directUrl");
	}
	
	/**
	 * 保存用户选择的送货方式与支付方式
	 */
	public ActionForward savePaymentway(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverForm formbean = (DeliverForm)form;		
		BuyCart cart = WebUtil.getBuyCart(request);
		cart.getDeliverInfo().setDeliverWay(formbean.getDeliverway());
		cart.setPaymentWay(formbean.getPaymentway());
		if(DeliverWay.EXPRESSDELIVERY.equals(formbean.getDeliverway()) || DeliverWay.EXIGENCEEXPRESSDELIVERY.equals(formbean.getDeliverway())){
			if("other".equals(formbean.getRequirement())){
				cart.getDeliverInfo().setRequirement(formbean.getDelivernote());
			}else{
				cart.getDeliverInfo().setRequirement(formbean.getRequirement());
			}
		}else{
			cart.getDeliverInfo().setRequirement(null);
		}
		request.setAttribute("directUrl", "/customer/shopping/confirm.do");
		return mapping.findForward("directUrl");
	}
	/**
	 * 提交订单
	 */
	public ActionForward saveorder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverForm formbean = (DeliverForm)form;		
		BuyCart cart = WebUtil.getBuyCart(request);
		cart.setNote(formbean.getNote());
		Order order = orderService.createOrder(cart, WebUtil.getBuyer(request).getUsername());
		WebUtil.deleteBuyCart(request);
		
		request.setAttribute("directUrl", "/shopping/finish.do?orderid="+ order.getOrderid()+"&paymentway="+ order.getPaymentWay()+
				"&payablefee="+ order.getPayablefee());
		return mapping.findForward("directUrl");
	}
	
}
