package cn.mmk.web.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.BuyCart;
import cn.mmk.bean.user.Buyer;
import cn.mmk.bean.user.Gender;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.formbean.shopping.DeliverForm;
/**
 * 显示配送信息填写界面
 */
@Controller("/customer/shopping/deliver")
public class DeliverInfoAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverForm formbean = (DeliverForm) form;
		formbean.setGender(Gender.MAN);
		formbean.setBuyer_gender(Gender.MAN);
		formbean.setEmail(WebUtil.getBuyer(request).getEmail());
		formbean.setBuyerIsrecipients(true);
		BuyCart cart = WebUtil.getBuyCart(request);
		Buyer buyer = WebUtil.getBuyer(request);
		if(cart.getDeliverInfo()!=null){
			formbean.setRecipients(cart.getDeliverInfo().getRecipients());
			formbean.setGender(cart.getDeliverInfo().getGender());
			formbean.setAddress(cart.getDeliverInfo().getAddress());
			formbean.setPostalcode(cart.getDeliverInfo().getPostalcode());
			formbean.setTel(cart.getDeliverInfo().getTel());
			formbean.setMobile(cart.getDeliverInfo().getMobile());
			formbean.setEmail(cart.getDeliverInfo().getEmail());
		}else{
			if(buyer.getContactInfo()!=null){
				formbean.setRecipients(buyer.getRealname());
				formbean.setGender(buyer.getGender());
				formbean.setAddress(buyer.getContactInfo().getAddress());
				formbean.setPostalcode(buyer.getContactInfo().getPostalcode());
				formbean.setTel(buyer.getContactInfo().getPhone());
				formbean.setMobile(buyer.getContactInfo().getMobile());
			}
		}
		if(cart.getBuyerIsrecipients()!=null) formbean.setBuyerIsrecipients(cart.getBuyerIsrecipients());
		
		if(cart.getContactInfo()!=null){
			formbean.setBuyer(cart.getContactInfo().getBuyerName());
			formbean.setBuyer_gender(cart.getContactInfo().getGender());
			formbean.setBuyer_address(cart.getContactInfo().getAddress());
			formbean.setBuyer_postalcode(cart.getContactInfo().getPostalcode());
			formbean.setBuyer_tel(cart.getContactInfo().getTel());
			formbean.setBuyer_mobile(cart.getContactInfo().getMobile());
		}else{
			if(buyer.getContactInfo()!=null){
				formbean.setBuyer(buyer.getRealname());
				formbean.setBuyer_gender(buyer.getGender());
				formbean.setBuyer_address(buyer.getContactInfo().getAddress());
				formbean.setBuyer_postalcode(buyer.getContactInfo().getPostalcode());
				formbean.setBuyer_tel(buyer.getContactInfo().getPhone());
				formbean.setBuyer_mobile(buyer.getContactInfo().getMobile());
			}
		}
		return mapping.findForward("deliverInfo");
	}

}
