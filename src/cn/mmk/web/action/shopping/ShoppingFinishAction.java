package cn.mmk.web.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.book.PaymentWay;
import cn.mmk.web.formbean.shopping.ShoppingFinishForm;
/**
 * 订单提交结果界面
 */
@Controller("/shopping/finish")
public class ShoppingFinishAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShoppingFinishForm finishForm = (ShoppingFinishForm)form;
		String forwardName = "postofficeremittance";
		if(PaymentWay.COD.equals(finishForm.getPaymentway())){
			forwardName = "cod";
		}else if(PaymentWay.NET.equals(finishForm.getPaymentway())){
			forwardName = "net";
		}else if(PaymentWay.BANKREMITTANCE.equals(finishForm.getPaymentway())){
			forwardName = "bankremittance";
		}
		return mapping.findForward(forwardName);
	}

}
