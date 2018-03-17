package cn.mmk.web.action.shopping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.BuyCart;
import cn.mmk.bean.BuyItem;
import cn.mmk.bean.product.ProductInfo;
import cn.mmk.bean.product.ProductStyle;
import cn.mmk.service.product.ProductInfoService;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.formbean.shopping.BuyCartForm;
/**
 * 购物车
 */
@Controller("/shopping/cart")
public class BuyCartAction extends Action {
	@Resource ProductInfoService infoService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyCartForm formbean = (BuyCartForm)form;
		BuyCart cart = WebUtil.getBuyCart(request);
		if(cart == null ){
			cart = new BuyCart();
			request.getSession().setAttribute("buyCart", cart);
		}
		WebUtil.addCookie(response, "JSESSIONID", request.getSession().getId(), request.getSession().getMaxInactiveInterval());
		if(formbean.getProductid()!=null && formbean.getProductid()>0){
			ProductInfo product = infoService.find(formbean.getProductid());
			if(product!=null){//下面处理产品的样式,只保留用户选择的样式
				ProductStyle currentStyle = null;
				for(ProductStyle style : product.getStyles()){
					if(style.getId().equals(formbean.getStyleid())){
						currentStyle = style;
						break;
					}
				}
				product.getStyles().clear();
				product.addProductStyle(currentStyle);
			}
			BuyItem item = new BuyItem(product);//把商品添加进购物车
			cart.addBuyItem(item);
		}		
		return mapping.findForward("buyCart");
	}

}
