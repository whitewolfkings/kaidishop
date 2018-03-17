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
 * 员工登录
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
				request.setAttribute("message", "用户名或密码有误");
			}
		}
		return mapping.findForward("logon");
	}//1>显示登录界面(当用户没有提供用户名及密码的时候) 2>实现用户名及密码的校验
}
