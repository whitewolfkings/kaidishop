package cn.mmk.web.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.user.Buyer;
import cn.mmk.service.user.BuyerService;
import cn.mmk.web.formbean.user.BuyerForm;


@Controller("/user/reg")
public class RegActon extends DispatchAction {
	@Resource(name="buyerServiceBean") BuyerService buyerService;
	/**
	 * 用户注册界面
	 */
	public ActionForward regUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("reg");
	}
	/**
	 * 用户注册
	 */
	public ActionForward reg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyerForm formbean = (BuyerForm) form;
		if(!buyerService.exsit(formbean.getUsername())){
			Buyer buyer = new Buyer(formbean.getUsername(),formbean.getPassword(), formbean.getEmail());
			buyerService.save(buyer);
			request.getSession().setAttribute("user", buyer);		
			request.setAttribute("message", "用户注册成功");
			String url = "/customer/shopping/deliver.do";
			if(formbean.getDirectUrl()!=null) url = formbean.getDirectUrl();
			request.setAttribute("urladdress", url);
			return mapping.findForward("message");
		}else{
			request.setAttribute("message", "用户已经存在");
			return mapping.findForward("reg");
		}
	}
	/**
	 * 判断用户名是否存在
	 */
	public ActionForward isUserExsit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyerForm formbean = (BuyerForm) form;
		request.setAttribute("exsit", buyerService.exsit(formbean.getUsername()));
		return mapping.findForward("checkuser");
	}
}
