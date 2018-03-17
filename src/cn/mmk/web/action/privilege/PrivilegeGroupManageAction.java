package cn.mmk.web.action.privilege;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.privilege.PrivilegeGroup;
import cn.mmk.bean.privilege.SystemPrivilege;
import cn.mmk.bean.privilege.SystemPrivilegePK;
import cn.mmk.service.privilege.PrivilegeGroupService;
import cn.mmk.service.privilege.SystemPrivilegeService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.formbean.privilege.PrivilegeGroupForm;

@Controller("/control/privilegegroup/manage")
public class PrivilegeGroupManageAction extends DispatchAction {
	@Resource SystemPrivilegeService privilegeService;
	@Resource PrivilegeGroupService groupService;
	/**
	 * 权限组添加界面
	 */
	public ActionForward addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		request.setAttribute("privileges", privilegeService.getScrollData().getResultlist());
		return mapping.findForward("add");
	}
	/**
	 * 添加权限组
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		PrivilegeGroupForm formbean = (PrivilegeGroupForm)form;
		PrivilegeGroup group = new PrivilegeGroup();
		group.setName(formbean.getName());
		if(formbean.getPrivileges()!=null && formbean.getPrivileges().length>0){
			for(SystemPrivilegePK id : formbean.getPrivileges()){
				group.getPrivileges().add(new SystemPrivilege(id));
			}
		}
		groupService.save(group);
		request.setAttribute("message", "权限组添加成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.privilegegroup.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * 权限组修改界面
	 */
	public ActionForward editUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		PrivilegeGroupForm formbean = (PrivilegeGroupForm)form;
		PrivilegeGroup group = groupService.find(formbean.getGroupid());
		formbean.setName(group.getName());
		
		request.setAttribute("selectprivileges", group.getPrivileges());
		request.setAttribute("privileges", privilegeService.getScrollData().getResultlist());
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改权限组
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		PrivilegeGroupForm formbean = (PrivilegeGroupForm)form;
		PrivilegeGroup group = groupService.find(formbean.getGroupid());
		group.setName(formbean.getName());
		group.getPrivileges().clear();
		if(formbean.getPrivileges()!=null && formbean.getPrivileges().length>0){
			for(SystemPrivilegePK id : formbean.getPrivileges()){
				group.getPrivileges().add(new SystemPrivilege(id));
			}
		}
		groupService.update(group);
		request.setAttribute("message", "权限组修改成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.privilegegroup.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * 删除权限组
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		PrivilegeGroupForm formbean = (PrivilegeGroupForm)form;
		groupService.delete(formbean.getGroupid());
		request.setAttribute("message", "权限组删除成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.privilegegroup.list"));
		return mapping.findForward("message");
	}
	
}
