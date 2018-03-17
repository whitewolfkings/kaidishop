package cn.mmk.web.action.product;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.service.product.ProductStyleService;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.product.ProductForm;

@Controller("/control/product/style/list")
public class ProductStyleAction extends Action {
	@Resource(name="productStyleServiceBean")
	private ProductStyleService productStyleService;

	@Override @Permission(module="product", privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductForm formbean = (ProductForm) form;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "desc");
		orderby.put("id", "asc");
		request.setAttribute("styles", productStyleService.getScrollData(-1, -1,
				"o.product.id=?1", new Object[]{formbean.getProductid()},orderby).getResultlist());
		return mapping.findForward("list");
	}
	
	
}
