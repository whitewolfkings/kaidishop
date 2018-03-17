package cn.mmk.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.mmk.bean.BuyCart;
import cn.mmk.utils.WebUtil;

public class BuyCartValidateFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		BuyCart cart = WebUtil.getBuyCart(request);
		if(cart==null || cart.getItems().isEmpty()){
			request.setAttribute("message", "目前,您的购物车中没有商品,请购买商品后再执行该操作");
			request.setAttribute("urladdress", "/");
			request.getRequestDispatcher("/WEB-INF/page/share/message.jsp").forward(req, res);
		}else{
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
