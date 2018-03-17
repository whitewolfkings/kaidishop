package cn.mmk.web.action.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
import cn.mmk.service.product.ProductInfoService;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.product.ProductForm;

/**
 * ��Ʒ��ҳ�б�
 *
 */
@Controller("/control/product/list")
public class ProductAction extends Action {
	@Resource(name="productInfoServiceBean")
	private ProductInfoService productInfoService;

	@Override @Permission(module="product", privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		PageView<ProductInfo> pageView = new PageView<ProductInfo>(12, formbean.getPage());
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "desc");
		orderby.put("id", "desc");
		
		if("true".equals(formbean.getQuery())){
			StringBuffer jpql = new StringBuffer("");
			List<Object> params = new ArrayList<Object>();
			//����Ʒ���Ʋ�ѯ
			if(formbean.getName()!=null && !"".equals(formbean.getName())){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.name like ?").append((params.size()+1));
				params.add("%"+ formbean.getName()+ "%");
			}
			//����Ʒ���Ͳ�ѯ
			if(formbean.getTypeid()!=null && formbean.getTypeid()>0){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.type.typeid=?").append((params.size()+1));
				params.add(formbean.getTypeid());
			}
			//���ɹ��������ѯ
			if(formbean.getStartbaseprice()!=null && formbean.getStartbaseprice()>0){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.baseprice>=?").append((params.size()+1));
				params.add(formbean.getStartbaseprice());
			}
			if(formbean.getEndbaseprice()!=null && formbean.getEndbaseprice()>0){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.baseprice<=?").append((params.size()+1));
				params.add(formbean.getEndbaseprice());
			}
			//�����ۼ������ѯ
			if(formbean.getStartsellprice()!=null && formbean.getStartsellprice()>0){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.sellprice>=?").append((params.size()+1));
				params.add(formbean.getStartsellprice());
			}
			if(formbean.getEndsellprice()!=null && formbean.getEndsellprice()>0){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.sellprice<=?").append((params.size()+1));
				params.add(formbean.getEndsellprice());
			}
			//�����Ų�ѯ
			if(formbean.getCode()!=null && !"".equals(formbean.getCode())){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.code=?").append((params.size()+1));
				params.add(formbean.getCode());
			}
			//��Ʒ�Ʋ�ѯ
			if(formbean.getBrandid()!=null && !"".equals(formbean.getBrandid())){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.brand.code=?").append((params.size()+1));
				params.add(formbean.getBrandid());
			}
			pageView.setQueryResult(productInfoService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby));
		}else{
			pageView.setQueryResult(productInfoService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(), orderby));
		}
		request.setAttribute("pageView", pageView);		
		return mapping.findForward("list");
	}
	
	
	
}
