package cn.mmk.web.action.product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.product.ProductInfo;
import cn.mmk.bean.product.ProductType;
import cn.mmk.service.product.ProductInfoService;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.formbean.product.FrontProductForm;

/**
 * 浏览商品
 * @author mmk 18801069539@163.com
 *
 */
@Controller("/product/view")
public class ViewProductAction extends Action {
	@Resource(name="productInfoServiceBean")
	private ProductInfoService productInfoService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontProductForm formbean = (FrontProductForm) form;
		ProductInfo product = productInfoService.find(formbean.getProductid());
		if(product==null){
			request.setAttribute("message", "获取不到你需要浏览的产品");
			request.setAttribute("urladdress", "/");
			return mapping.findForward("message");
		}
		WebUtil.addCookie(response, "productViewHistory", 
				buildViewHistory(request, formbean.getProductid()), 30*24*60*60);
		List<ProductType> stypes = new ArrayList<ProductType>();
		ProductType parent = product.getType();
		while(parent!=null){
			stypes.add(parent);
			parent = parent.getParent();
		}
		request.setAttribute("product", product);
		request.setAttribute("stypes", stypes);
		return mapping.findForward("product");
	}
	
	public String buildViewHistory(HttpServletRequest request, Integer currentProductId){
		//23-2-6-5
		//1.如果当前浏览的id已经在浏览历史里了,我们要把移到最前面
		//2.如果浏览历史里已经达到了10个产品了,我们需要把最选进入的元素删除
		String cookieValue = WebUtil.getCookieByName(request, "productViewHistory");
		LinkedList<Integer> productids = new LinkedList<Integer>();
		if(cookieValue!=null && !"".equals(cookieValue.trim())){
			String[] ids = cookieValue.split("-");			
			for(String id : ids){
				productids.offer(new Integer(id.trim()));
			}
			if(productids.contains(currentProductId)) productids.remove(currentProductId);
			if(productids.size()>=10) productids.poll();
		}
		productids.offer(currentProductId);
		StringBuffer out = new StringBuffer();
		for(Integer id : productids){
			out.append(id).append('-');
		}
		out.deleteCharAt(out.length()-1);
		return out.toString();
	}
}
