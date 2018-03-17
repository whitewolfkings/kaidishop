package cn.mmk.web.action.product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.service.product.ProductInfoService;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.formbean.product.FrontProductForm;


@Controller("/product/switch")
public class ProductSwitchAction extends DispatchAction {
	@Resource(name="productInfoServiceBean")
	private ProductInfoService productInfoService;
	
	/**
	 * ��ʾ��Ʒ��ͼƬ
	 */
	public ActionForward showimage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("showimage");
	}
	
	/**
	 * ��ȡ10������Ĳ�Ʒ
	 */
	public ActionForward topsell(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontProductForm formbean = (FrontProductForm) form;
		request.setAttribute("topsellproducts", productInfoService.getTopSell(formbean.getTypeid(), 10));
		return mapping.findForward("topsell");
	}
	
	/**
	 * ��ȡ10���û�������Ĳ�Ʒ
	 */
	public ActionForward getViewHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cookieValue = WebUtil.getCookieByName(request, "productViewHistory");
		if(cookieValue!=null && !"".equals(cookieValue.trim())){			
			String[] ids = cookieValue.split("-");
			Integer[] productids = new Integer[ids.length];
			for(int i=0 ;i<ids.length; i++){
				productids[i]=new Integer(ids[i].trim());
			}
			request.setAttribute("viewHistory", productInfoService.getViewHistory(productids, 10));
		}
		return mapping.findForward("viewHistory");
	}
}
