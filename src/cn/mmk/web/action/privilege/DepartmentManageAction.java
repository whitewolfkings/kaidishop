package cn.mmk.web.action.privilege;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.privilege.Department;
import cn.mmk.service.privilege.DepartmentService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.formbean.privilege.DepartmentForm;
/**
 * 部门管理
 */
@Controller("/control/department/manage")
public class DepartmentManageAction extends DispatchAction {
	@Resource DepartmentService departmentService;
	/**
	 * 部门添加界面
	 */
	@Permission(module="department", privilege="insert")
	public ActionForward addDepartmentUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		return mapping.findForward("add");
	}
	/**
	 * 添加部门
	 */
	@Permission(module="department", privilege="insert")
	public ActionForward addDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		Department department = new Department();
		department.setName(formbean.getName());
		departmentService.save(department);
		
		request.setAttribute("message", "部门添加成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.department.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * 部门修改界面
	 */
	@Permission(module="department", privilege="update")
	public ActionForward editDepartmentUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		DepartmentForm formbean = (DepartmentForm)form;
		Department department = departmentService.find(formbean.getDepartmentid());
		formbean.setName(department.getName());
		return mapping.findForward("edit");
	}
	/**
	 * 修改部门信息
	 */
	@Permission(module="department", privilege="update")
	public ActionForward editDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		Department department = departmentService.find(formbean.getDepartmentid());
		department.setName(formbean.getName());
		departmentService.update(department);
		
		request.setAttribute("message", "部门修改成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.department.list"));
		return mapping.findForward("message");
	}
	/**
	 * 删除部门
	 */
	@Permission(module="department", privilege="delete")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		departmentService.delete((Serializable)formbean.getDepartmentid());
		
		request.setAttribute("message", "部门删除成功");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.department.list"));
		return mapping.findForward("message");
	}
	
}
