package cn.mmk.web.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
/**
 * 订单信息确认界面
 */
@Controller("/customer/shopping/confirm")
public class OrderConfirmAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String url = "/customer/shopping/confirm.do";
		request.setAttribute("directUrl", new String(Base64.encodeBase64(url.getBytes())));
		return mapping.findForward("confirm");
	}

}
