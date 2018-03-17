package cn.mmk.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mmk.bean.privilege.Employee;
import cn.mmk.utils.WebUtil;

public class PrivilegeFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		Employee employee = WebUtil.getEmployee(request);
		if(employee==null){//���Ա��δ��¼���ض���Ա����½����
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect("/employee/logon.do");
		}else{
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
