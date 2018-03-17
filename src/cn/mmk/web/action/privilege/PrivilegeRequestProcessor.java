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
		if(WebUtil.getRequestURI(request).startsWith("/control/")){//ֻ�԰칫ƽ̨�е�action����У��
			if(!validate(request, action, mapping)){//û��Ȩ�޵�ʱ��ִ��������δ���
				request.setAttribute("message", "��û��ִ�иò�����Ȩ��");
				request.setAttribute("urladdress", SiteUrl.readUrl("control.control.right"));
				return mapping.findForward("message");
			}
		}
		return super.processActionPerform(request, response, action, form, mapping);
	}
	/**
	 * Ȩ��У��
	 * @return
	 */
	private boolean validate(HttpServletRequest request, Action action, ActionMapping mapping) {
		Method method = getCurrentMethod(request, action, mapping);
		if(method!=null && method.isAnnotationPresent(Permission.class)){
			Permission permission = method.getAnnotation(Permission.class);//�õ������ϵ�ע��
			//�����ǵõ�ִ�з�����Ҫ��Ȩ��
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
	 * ��ȡ�û���ǰִ�еķ���
	 * @return
	 */
	private Method getCurrentMethod(HttpServletRequest request, Action action,
			ActionMapping mapping) {
		String methodName = "execute";
		if(DispatchAction.class.isAssignableFrom(action.getClass())){//�ж�DispatchAction�Ƿ���action.getClass()�ĸ���
			methodName = request.getParameter(mapping.getParameter());//ȡ�����������ָ���ķ�������
		}
		try {
			return action.getClass().getMethod(methodName, ActionMapping.class, ActionForm.class,
				HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
		}
		return null;
	}

}
