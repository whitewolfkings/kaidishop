package cn.mmk.web.action.product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.PageView;
import cn.mmk.bean.product.ProductInfo;
import cn.mmk.service.product.ProductSearchService;
import cn.mmk.web.formbean.product.ProductQueryForm;
/**
 * ÉÌÆ·ËÑË÷
 */
@Controller("/product/query")
public class ProductSearchAction extends Action {
	@Resource ProductSearchService productSearchService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductQueryForm formbean = (ProductQueryForm)form;
		PageView<ProductInfo> pageView = new PageView<ProductInfo>(18, formbean.getPage());
		pageView.setQueryResult(productSearchService.query(formbean.getWord(), pageView.getFirstResult(), pageView.getMaxresult()));
		request.setAttribute("pageView", pageView);
		return mapping.findForward("list");
	}

}
