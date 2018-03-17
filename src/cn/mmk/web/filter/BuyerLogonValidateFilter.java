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

import org.apache.commons.codec.binary.Base64;

import cn.mmk.bean.user.Buyer;
import cn.mmk.utils.WebUtil;

public class BuyerLogonValidateFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		Buyer buyer = WebUtil.getBuyer(request);
		if(buyer == null){
			String url = WebUtil.getRequestURIWithParam(request);//得到当前请求路径
			String directUrl = new String(Base64.encodeBase64(url.getBytes()));
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect("/user/logon.do?directUrl="+ directUrl);
		}else{
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
