package cn.mmk.web.action.shopping;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.BuyCart;
import cn.mmk.bean.book.DeliverWay;
import cn.mmk.bean.book.PaymentWay;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.formbean.shopping.DeliverForm;
/**
 * 送货方式与支付方式显示界面
 */
@Controller("/customer/shopping/paymentway")
public class PaymentWayAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverForm formbean = (DeliverForm)form;
		BuyCart cart = WebUtil.getBuyCart(request);
		if(cart.getDeliverInfo()==null){
			request.setAttribute("directUrl", "/customer/shopping/deliver.do");
			return mapping.findForward("directUrl");
		}
		formbean.setDeliverway(DeliverWay.EXPRESSDELIVERY);
		formbean.setPaymentway(PaymentWay.NET);
		
		if(cart.getPaymentWay()!=null){
			formbean.setPaymentway(cart.getPaymentWay());
		}
		if(cart.getDeliverInfo().getDeliverWay()!=null){
			formbean.setDeliverway(cart.getDeliverInfo().getDeliverWay());
		}
		if(cart.getDeliverInfo().getRequirement()!=null){//为了实现时间要求数据回显
			List<String> contents = Arrays.asList("工作日、双休日与假日均可送货","只双休日、假日送货",
					"只工作日送货(双休日、假日不用送)","学校地址/地址白天没人，请尽量安排其他时间送货");
			if(contents.contains(cart.getDeliverInfo().getRequirement())){
				formbean.setRequirement(cart.getDeliverInfo().getRequirement());
			}else{
				formbean.setRequirement("other");
				formbean.setDelivernote(cart.getDeliverInfo().getRequirement());
			}
		}
		return mapping.findForward("paymentway");
	}

}
