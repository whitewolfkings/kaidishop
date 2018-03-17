package cn.mmk.web.action.privilege;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.privilege.Department;
import cn.mmk.bean.privilege.Employee;
import cn.mmk.bean.privilege.IDCard;
import cn.mmk.bean.privilege.PrivilegeGroup;
import cn.mmk.bean.user.Gender;
import cn.mmk.service.privilege.DepartmentService;
import cn.mmk.service.privilege.EmployeeService;
import cn.mmk.service.privilege.PrivilegeGroupService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.formbean.privilege.EmployeeForm;
/**
 * Ա������
 */
@Controller("/control/employee/manage")
public class EmployeeManageAction extends DispatchAction {
	@Resource EmployeeService employeeService;
	@Resource DepartmentService departmentService;
	@Resource PrivilegeGroupService groupService;
	
	/**
	 * Ա����ѯ����
	 */
	@Permission(module="employee", privilege="view")
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("departments", departmentService.getScrollData().getResultlist());
		return mapping.findForward("query");
	}
	
	/**
	 * Ա��Ȩ�����ý���
	 */
	@Permission(module="employee", privilege="privilegeGroupSet")
	public ActionForward privilegeGroupSetUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EmployeeForm formbean = (EmployeeForm)form;
		Employee employee = employeeService.find(formbean.getUsername());
		request.setAttribute("usergroups", employee.getGroups());
		request.setAttribute("groups", groupService.getScrollData().getResultlist());
		return mapping.findForward("privilegeSet");
	}
	
	
	/**
	 * ΪԱ������Ȩ��
	 */
	@Permission(module="employee", privilege="privilegeGroupSet")
	public ActionForward privilegeGroupSet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EmployeeForm formbean = (EmployeeForm)form;
		Employee employee = employeeService.find(formbean.getUsername());
		employee.getGroups().clear();
		for(String groupid : formbean.getGroupids()){
			employee.getGroups().add(new PrivilegeGroup(groupid));
		}
		employeeService.update(employee);
		
		request.setAttribute("message", "Ȩ�����óɹ�");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.employee.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * Ա���޸Ľ���
	 */
	@Permission(module="employee", privilege="update")
	public ActionForward editEmployeeUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EmployeeForm formbean = (EmployeeForm)form;
		Employee employee = employeeService.find(formbean.getUsername());
		formbean.setGender(employee.getGender());
		formbean.setEmail(employee.getEmail());
		formbean.setPhone(employee.getPhone());
		formbean.setRealname(employee.getRealname());
		formbean.setSchool(employee.getSchool());
		formbean.setCardno(employee.getIdCard().getCardno());
		formbean.setBirthday(employee.getIdCard().getBirthday());
		formbean.setDegree(employee.getDegree());
		formbean.setAddress(employee.getIdCard().getAddress());
		if(employee.getDepartment()!=null)
			request.setAttribute("selectdepartmentid", employee.getDepartment().getDepartmentid());
		request.setAttribute("imagePath", employee.getImagePath());
		request.setAttribute("departments", departmentService.getScrollData().getResultlist());
		return mapping.findForward("edit");
	}
	
	/**
	 * �޸�Ա����Ϣ
	 */
	@Permission(module="employee", privilege="update")
	public ActionForward editEmployee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EmployeeForm formbean = (EmployeeForm)form;
		Employee employee = employeeService.find(formbean.getUsername());
		employee.setDegree(formbean.getDegree());
		employee.setGender(formbean.getGender());
		employee.setEmail(formbean.getEmail());
		employee.setPhone(formbean.getPhone());
		employee.setRealname(formbean.getRealname());
		employee.setSchool(formbean.getSchool());
		employee.getIdCard().setCardno(formbean.getCardno());
		employee.getIdCard().setAddress(formbean.getAddress());
		employee.getIdCard().setBirthday(formbean.getBirthday());
		
		if(formbean.getPicture()!=null && formbean.getPicture().getFileSize()>0){
			String fileName = UUID.randomUUID()+ "."+ EmployeeForm.getExt(formbean.getPicture());
			String virpath = "/images/employee/"+ employee.getUsername();
			File saveDir = new File(request.getSession().getServletContext().getRealPath(virpath));
			EmployeeForm.saveFile(saveDir, fileName, formbean.getPicture().getFileData());
			employee.setImageName(fileName);
		}
		if(formbean.getDepartmentid()!=null && !"".equals(formbean.getDepartmentid()))
			employee.setDepartment(new Department(formbean.getDepartmentid()));
		
		employeeService.update(employee);
		request.setAttribute("message", "Ա���޸ĳɹ�");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.employee.list"));
		return mapping.findForward("message");
	}
	/**
	 * �ж�Ա�����û����Ƿ����
	 */
	public ActionForward exist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EmployeeForm formbean = (EmployeeForm)form;
		request.setAttribute("exist", employeeService.exist(formbean.getUsername()));
		return mapping.findForward("checkResult");
	}
	/**
	 * Ա����ӽ���
	 */
	@Permission(module="employee", privilege="insert")
	public ActionForward regEmployeeUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EmployeeForm formbean = (EmployeeForm)form;
		formbean.setGender(Gender.MAN);
		request.setAttribute("departments", departmentService.getScrollData().getResultlist());
		return mapping.findForward("add");
	}
	/**
	 * ���Ա��
	 */
	@Permission(module="employee", privilege="insert")
	public ActionForward regEmployee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EmployeeForm formbean = (EmployeeForm)form;
		Employee employee = new Employee();
		employee.setUsername(formbean.getUsername().trim());
		employee.setPassword(formbean.getPassword().trim());
		employee.setDegree(formbean.getDegree());
		employee.setGender(formbean.getGender());
		employee.setEmail(formbean.getEmail());
		employee.setPhone(formbean.getPhone());
		employee.setRealname(formbean.getRealname());
		employee.setSchool(formbean.getSchool());
		employee.setIdCard(new IDCard(formbean.getCardno(),formbean.getAddress(),formbean.getBirthday()));
		if(formbean.getPicture()!=null && formbean.getPicture().getFileSize()>0){
			String fileName = UUID.randomUUID()+ "."+ EmployeeForm.getExt(formbean.getPicture());
			String virpath = "/images/employee/"+ employee.getUsername();
			File saveDir = new File(request.getSession().getServletContext().getRealPath(virpath));
			EmployeeForm.saveFile(saveDir, fileName, formbean.getPicture().getFileData());
			employee.setImageName(fileName);
		}
		if(formbean.getDepartmentid()!=null && !"".equals(formbean.getDepartmentid()))
			employee.setDepartment(new Department(formbean.getDepartmentid()));
		
		employeeService.save(employee);
		request.setAttribute("message", "Ա����ӳɹ�");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.employee.list"));
		return mapping.findForward("message");
	}
	
	
	/**
	 * ����Ա����ְ
	 */
	@Permission(module="employee", privilege="leave")
	public ActionForward leave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		EmployeeForm formbean = (EmployeeForm)form;
		employeeService.delete((Serializable)formbean.getUsername());
		
		request.setAttribute("message", "���óɹ�");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.employee.list"));
		return mapping.findForward("message");
	}
}
