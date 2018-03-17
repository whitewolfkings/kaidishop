package cn.mmk.web.action.privilege;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.PageView;
import cn.mmk.bean.privilege.PrivilegeGroup;
import cn.mmk.service.privilege.PrivilegeGroupService;
import cn.mmk.web.formbean.privilege.PrivilegeGroupForm;
/**
 * 权限组分页列表
 */
@Controller("/control/privilegegroup/list")
public class PrivilegeGroupListAction extends Action {
	@Resource PrivilegeGroupService groupService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrivilegeGroupForm formbean = (PrivilegeGroupForm)form;
		PageView<PrivilegeGroup> pageView = new PageView<PrivilegeGroup>(12, formbean.getPage());
		pageView.setQueryResult(groupService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult()));
		request.setAttribute("pageView", pageView);
		return mapping.findForward("list");
	}

}
