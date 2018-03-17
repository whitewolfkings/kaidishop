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
 * ���Ź���
 */
@Controller("/control/department/manage")
public class DepartmentManageAction extends DispatchAction {
	@Resource DepartmentService departmentService;
	/**
	 * ������ӽ���
	 */
	@Permission(module="department", privilege="insert")
	public ActionForward addDepartmentUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		return mapping.findForward("add");
	}
	/**
	 * ��Ӳ���
	 */
	@Permission(module="department", privilege="insert")
	public ActionForward addDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		Department department = new Department();
		department.setName(formbean.getName());
		departmentService.save(department);
		
		request.setAttribute("message", "������ӳɹ�");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.department.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * �����޸Ľ���
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
	 * �޸Ĳ�����Ϣ
	 */
	@Permission(module="department", privilege="update")
	public ActionForward editDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		Department department = departmentService.find(formbean.getDepartmentid());
		department.setName(formbean.getName());
		departmentService.update(department);
		
		request.setAttribute("message", "�����޸ĳɹ�");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.department.list"));
		return mapping.findForward("message");
	}
	/**
	 * ɾ������
	 */
	@Permission(module="department", privilege="delete")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		departmentService.delete((Serializable)formbean.getDepartmentid());
		
		request.setAttribute("message", "����ɾ���ɹ�");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.department.list"));
		return mapping.findForward("message");
	}
	
}
