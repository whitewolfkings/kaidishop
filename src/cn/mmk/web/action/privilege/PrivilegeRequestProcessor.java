package cn.mmk.web.action.privilege;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.struts.DelegatingRequestProcessor;

import cn.mmk.bean.privilege.Employee;
import cn.mmk.bean.privilege.PrivilegeGroup;
import cn.mmk.bean.privilege.SystemPrivilege;
import cn.mmk.bean.privilege.SystemPrivilegePK;
import cn.mmk.utils.SiteUrl;
import cn.mmk.utils.WebUtil;

public class PrivilegeRequestProcessor extends DelegatingRequestProcessor {

	@Override
	protected ActionForward processActionPerform(HttpServletRequest request,
			HttpServletResponse response, Action action, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {
		if(WebUtil.getRequestURI(request).startsWith("/control/")){//只对办公平台中的action进行校验
			if(!validate(request, action, mapping)){//没有权限的时候执行下面这段代码
				request.setAttribute("message", "您没有执行该操作的权限");
				request.setAttribute("urladdress", SiteUrl.readUrl("control.control.right"));
				return mapping.findForward("message");
			}
		}
		return super.processActionPerform(request, response, action, form, mapping);
	}
	/**
	 * 权限校验
	 * @return
	 */
	private boolean validate(HttpServletRequest request, Action action, ActionMapping mapping) {
		Method method = getCurrentMethod(request, action, mapping);
		if(method!=null && method.isAnnotationPresent(Permission.class)){
			Permission permission = method.getAnnotation(Permission.class);//得到方法上的注解
			//下面是得到执行方法需要的权限
			SystemPrivilege methodPrivilege = new SystemPrivilege(new SystemPrivilegePK(permission.module(),permission.privilege()));
			Employee employee = WebUtil.getEmployee(request);
			for(PrivilegeGroup group : employee.getGroups()){
				if(group.getPrivileges().contains(methodPrivilege)){
					return true;
				}
			}
			return false;
		}
		return true;
	}
	/**
	 * 获取用户当前执行的方法
	 * @return
	 */
	private Method getCurrentMethod(HttpServletRequest request, Action action,
			ActionMapping mapping) {
		String methodName = "execute";
		if(DispatchAction.class.isAssignableFrom(action.getClass())){//判断DispatchAction是否是action.getClass()的父类
			methodName = request.getParameter(mapping.getParameter());//取得由请求参数指定的方法名称
		}
		try {
			return action.getClass().getMethod(methodName, ActionMapping.class, ActionForm.class,
				HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
		}
		return null;
	}

}
