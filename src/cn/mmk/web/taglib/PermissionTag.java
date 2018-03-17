package cn.mmk.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.mmk.bean.privilege.Employee;
import cn.mmk.bean.privilege.PrivilegeGroup;
import cn.mmk.bean.privilege.SystemPrivilege;
import cn.mmk.bean.privilege.SystemPrivilegePK;
import cn.mmk.utils.WebUtil;
/**
 * Ȩ��У���ǩ
 *
 */
public class PermissionTag extends TagSupport {
	private String module;
	private String privilege;
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public int doStartTag() throws JspException {
		boolean result = false;
		Employee employee = WebUtil.getEmployee((HttpServletRequest)pageContext.getRequest());//��ȡ��¼��ϵͳ��Ա��
		SystemPrivilege privilege = new SystemPrivilege(new SystemPrivilegePK(this.module, this.privilege));
		for(PrivilegeGroup group : employee.getGroups()){
			if(group.getPrivileges().contains(privilege)){
				result = true;
				break;
			}
		}
		return result? EVAL_BODY_INCLUDE : SKIP_BODY;

	}
	
}
