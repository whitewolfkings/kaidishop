package cn.mmk.web.action.user;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.service.user.BuyerService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.user.BuyerForm;

/**
 * �˿͹���
 *
 */
@Controller("/control/user/manage")
public class BuyerManageAction extends DispatchAction {
	@Resource(name="buyerServiceBean") BuyerService buyerService;
	/**
	 * ����
	 */
	@Permission(module="buyer", privilege="enable")
	public ActionForward enable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyerForm formbean = (BuyerForm) form;
		buyerService.enable((Serializable[])formbean.getUsernames());
		request.setAttribute("message", "���óɹ�");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.user.list"));
		return mapping.findForward("message");
	}
	/**
	 * ����
	 */
	@Permission(module="buyer", privilege="delete")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		BuyerForm formbean = (BuyerForm) form;
		buyerService.delete((Serializable[])formbean.getUsernames());
		request.setAttribute("message", "���óɹ�");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.user.list"));
		return mapping.findForward("message");
	}
}
