package cn.mmk.web.action.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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
import cn.mmk.bean.product.ProductStyle;
import cn.mmk.bean.product.ProductType;
import cn.mmk.bean.product.Sex;
import cn.mmk.service.product.ProductInfoService;
import cn.mmk.service.product.ProductTypeService;
import cn.mmk.utils.WebUtil;
import cn.mmk.web.formbean.product.FrontProductForm;


@Controller("/product/list/display")
public class FrontProductAction extends Action {
	@Resource(name="productInfoServiceBean")
	private ProductInfoService productInfoService;
	
	@Resource(name="productTypeServiceBean")
	private ProductTypeService productTypeService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontProductForm formbean = (FrontProductForm) form;
		PageView<ProductInfo> pageView = new PageView<ProductInfo>(2, formbean.getPage());
		pageView.setPagecode(20);
		int firstindex = (pageView.getCurrentpage()-1)* pageView.getMaxresult();
		LinkedHashMap<String, String> orderby = buildOrder(formbean.getSort());
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);	
		
		List<Integer> typeids = new ArrayList<Integer>();
		typeids.add(formbean.getTypeid());
		getTypeids(typeids, new Integer[]{formbean.getTypeid()});
		StringBuffer n = new StringBuffer();
		for(int i=0; i<typeids.size();i++){
			n.append('?').append((i+2)).append(',');
		}
		n.deleteCharAt(n.length()-1);
		jpql.append(" and o.type.typeid in("+ n.toString()+ ")");
		params.addAll(typeids);
		
		if(formbean.getBrandid()!=null && !"".equals(formbean.getBrandid().trim())){
			jpql.append(" and o.brand.code=?").append((params.size()+1));
			params.add(formbean.getBrandid());
		}			
		if(formbean.getSex()!=null ){
			String sex = formbean.getSex().trim();	
			if("NONE".equalsIgnoreCase(sex) || "MAN".equalsIgnoreCase(sex) || "WOMEN".equalsIgnoreCase(sex)){
				jpql.append(" and o.sexrequest=?").append((params.size()+1));
				params.add(Sex.valueOf(formbean.getSex()));
			}
		}		
		pageView.setQueryResult(productInfoService.getScrollData(firstindex, 
					pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby));
		for(ProductInfo product : pageView.getRecords()){
			Set<ProductStyle> styles = new HashSet<ProductStyle>();
			for(ProductStyle style : product.getStyles()){
				if(style.getVisible()){
					styles.add(style);
					break;
				}
			}
			product.setStyles(styles);
			//注意:执行此句代码会把修改后的数据同步回数据库,如果不想把数据同步回数据库,请在其后调用productInfoService.clear();
			product.setDescription(WebUtil.HtmltoText(product.getDescription()));
		}
		productInfoService.clear();//让托管状态的实体成为游离状态
		request.setAttribute("pageView", pageView);
		Integer[] ids = new Integer[typeids.size()];
		for(int i=0;i<typeids.size();i++){
			ids[i]=typeids.get(i);
		}
		request.setAttribute("brands", productInfoService.getBrandsByProductTypeid(ids));	
		if(formbean.getTypeid()!=null && formbean.getTypeid()>0){
			ProductType type = productTypeService.find(formbean.getTypeid());
			if(type!=null){
				List<ProductType> types = new ArrayList<ProductType>();
				types.add(type);
				ProductType parent = type.getParent();
				while(parent!=null){
					types.add(parent);
					parent = parent.getParent();
				}
				request.setAttribute("producttype", type);
				request.setAttribute("types", types);
			}
		}
		return mapping.findForward(getView(formbean.getStyle()));
	}
	/**
	 * 获取显示视图
	 * @param style 样式
	 * @return
	 */
	public String getView(String style){
		if("imagetext".equalsIgnoreCase(style)) return "list_imagetext";//图文版
		else return "list_image";//图片版		
	}
	
	/**
	 * 获取类别下所有子类的id(注:子类级其子类的id都会获取到)
	 * @param outtypeids
	 * @param typeids
	 */
	public void getTypeids(List<Integer> outtypeids, Integer[] typeids){
		List<Integer> subtypeids =productTypeService.getSubTypeid(typeids);
		if(subtypeids!=null && subtypeids.size()>0){
			outtypeids.addAll(subtypeids);
			Integer[] ids = new Integer[subtypeids.size()];
			for(int i=0;i<subtypeids.size();i++){
				ids[i]=subtypeids.get(i);
			}
			getTypeids(outtypeids, ids);
		}
	}
	/**
	 * 组拼排序
	 * @param orderfied
	 * @return
	 */
	private LinkedHashMap<String, String> buildOrder(String orderfied){
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		if("selldesc".equals(orderfied)){
			orderby.put("sellcount", "desc");
		}else if("sellpricedesc".equals(orderfied)){
			orderby.put("sellprice", "desc");
		}else if("sellpriceasc".equals(orderfied)){
			orderby.put("sellprice", "asc");
		}else{
			orderby.put("createdate", "desc");
		}
		return orderby;
	}
}
