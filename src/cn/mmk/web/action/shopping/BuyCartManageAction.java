package cn.mmk.web.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.BuyCart;
import cn.mmk.bean.BuyItem;
import cn.mmk.bean.product.ProductInfo;
import cn.mmk.bean.product.ProductStyle;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.formbean.shopping.BuyCartForm;

@Controller("/shopping/cart/manage")
public class BuyCartManageAction extends DispatchAction {
	/**
	 * 清空购物车
	 */
	public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyCart cart = WebUtil.getBuyCart(request);
		cart.deleteAll();
		BuyCartForm formbean = (BuyCartForm)form;
		String param = formbean.getDirectUrl()!=null && !"".equals(formbean.getDirectUrl()) ? "?directUrl=" + formbean.getDirectUrl() : ""; 
		request.setAttribute("directUrl", "/shopping/cart.do"+ param);
		return mapping.findForward("directUrl");
	}
	/**
	 * 删除购物项
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyCartForm formbean = (BuyCartForm)form;
		BuyCart cart = WebUtil.getBuyCart(request);
		ProductInfo product = new ProductInfo(formbean.getProductid());
		product.addProductStyle(new ProductStyle(formbean.getStyleid()));
		BuyItem item = new BuyItem(product);
		cart.deleteBuyItem(item);
		String param = formbean.getDirectUrl()!=null && !"".equals(formbean.getDirectUrl()) ? "?directUrl=" + formbean.getDirectUrl() : ""; 
		request.setAttribute("directUrl", "/shopping/cart.do"+ param);
		return mapping.findForward("directUrl");
	}
	/**
	 * 修改购买数量
	 */
	public ActionForward updateAmount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setAmount(request);
		BuyCartForm formbean = (BuyCartForm)form;
		String param = formbean.getDirectUrl()!=null && !"".equals(formbean.getDirectUrl()) ? "?directUrl=" + formbean.getDirectUrl() : ""; 
		request.setAttribute("directUrl", "/shopping/cart.do"+ param);
		return mapping.findForward("directUrl");
	}
	/**
	 * 修改商品购买数量
	 * @param request
	 */
	private void setAmount(HttpServletRequest request) {
		BuyCart cart = WebUtil.getBuyCart(request);
		for(BuyItem item : cart.getItems()){
			String paramName = "amount_"+ item.getProduct().getId()+"_"+
								item.getProduct().getStyles().iterator().next().getId();
			Integer amount = new Integer(request.getParameter(paramName));
			item.setAmount(amount);
		}
	}
	/**
	 * 结算
	 */
	public ActionForward settleAccounts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setAmount(request);
		BuyCartForm formbean = (BuyCartForm)form;
		String url = "/customer/shopping/deliver.do";
		if(formbean.getDirectUrl()!=null && !"".equals(formbean.getDirectUrl())){
			url = new String(Base64.decodeBase64(formbean.getDirectUrl().trim().getBytes()));//获取解码后的url
		}
		request.setAttribute("directUrl", url);
		return mapping.findForward("directUrl");
	}
	
}
