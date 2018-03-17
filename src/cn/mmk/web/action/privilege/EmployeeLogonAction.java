package cn.mmk.web.action.privilege;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.service.privilege.EmployeeService;
import cn.mmk.web.formbean.privilege.EmployeeForm;
/**
 * Ա����¼
 */
@Controller("/employee/logon")
public class EmployeeLogonAction extends Action {
	@Resource EmployeeService employeeService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmployeeForm formbean = (EmployeeForm)form;
		if(formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())
				&& formbean.getPassword()!=null && !"".equals(formbean.getPassword().trim())){
			if(employeeService.validate(formbean.getUsername().trim(), formbean.getPassword().trim())){
				request.getSession().setAttribute("employee", employeeService.find(formbean.getUsername().trim()));
				return mapping.findForward("main");
			}else{
				request.setAttribute("message", "�û�������������");
			}
		}
		return mapping.findForward("logon");
	}//1>��ʾ��¼����(���û�û���ṩ�û����������ʱ��) 2>ʵ���û����������У��
}
