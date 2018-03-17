package cn.mmk.web.action.product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.product.ProductInfo;
import cn.mmk.bean.product.ProductStyle;
import cn.mmk.bean.product.ProductType;
import cn.mmk.bean.product.Sex;
import cn.mmk.service.product.BrandService;
import cn.mmk.service.product.ProductInfoService;
import cn.mmk.service.product.ProductTypeService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.product.ProductForm;
import cn.mmk.web.formbean.uploadfile.UploadfileForm;


@Controller("/control/product/manage")
public class ProductManageAction extends DispatchAction {
	@Resource(name="productInfoServiceBean")
	private ProductInfoService productInfoService;
	@Resource(name="brandServiceBean")
	private BrandService brandService;	
	@Resource(name="productTypeServiceBean")
	private ProductTypeService productTypeService;

	/**
	 * ���ò�Ʒ�ϼ�
	 */
	@Permission(module="product", privilege="visible")
	public ActionForward visible(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		productInfoService.setVisibleStatu(formbean.getProductids(), true);
		request.setAttribute("message", "���óɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * ���ò�Ʒ�¼�
	 */
	@Permission(module="product", privilege="visible")
	public ActionForward disable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		productInfoService.setVisibleStatu(formbean.getProductids(), false);
		request.setAttribute("message", "���óɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * ���ò�ƷΪ�Ƽ�
	 */
	@Permission(module="product", privilege="commend")
	public ActionForward commend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		productInfoService.setCommendStatu(formbean.getProductids(), true);
		request.setAttribute("message", "���óɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * ���ò�ƷΪ���Ƽ�
	 */
	@Permission(module="product", privilege="commend")
	public ActionForward uncommend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		productInfoService.setCommendStatu(formbean.getProductids(), false);
		request.setAttribute("message", "���óɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.list"));
		return mapping.findForward("message");
	}
	/**
	 * ��Ʒ��ѯ����
	 */
	@Permission(module="product", privilege="view")
	public ActionForward queryUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("brands", brandService.getScrollData().getResultlist());
		return mapping.findForward("query");
	}
	/**
	 * ѡ�����
	 */
	@Permission(module="product", privilege="view")
	public ActionForward selectUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		String jpql = "o.parent is null and o.visible=true";
		Object[] params = new Object[0];
		if(formbean.getTypeid()!=null && formbean.getTypeid()>0){
			jpql = "o.parent.id=?1";
			params = new Object[]{formbean.getTypeid()};
			ProductType type = productTypeService.find(formbean.getTypeid());
			ProductType parent = type.getParent();
			List<ProductType> types = new ArrayList<ProductType>();
			types.add(type);
			while(parent!=null){
			     types.add(parent);
			     parent =  parent.getParent();
			}
			request.setAttribute("menutypes", types);
		}
		request.setAttribute("types", productTypeService.getScrollData(-1, -1, jpql, params).getResultlist());
		return mapping.findForward("typeselect");
	}
	/**
	 * ��Ʒ��ӽ���
	 */
	@Permission(module="product", privilege="insert")
	public ActionForward addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("brands", brandService.getScrollData().getResultlist());
		return mapping.findForward("add");
	}
	
	/**
	 * ��Ʒ���
	 */
	@Permission(module="product", privilege="insert")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		ProductInfo product = new ProductInfo();
		product.setName(formbean.getName());
		product.setBaseprice(formbean.getBaseprice());
		product.setSellprice(formbean.getSellprice());
		product.setMarketprice(formbean.getMarketprice());
		if(formbean.getBrandid()!=null && !"".equals(formbean.getBrandid().trim())){
			product.setBrand(brandService.find(formbean.getBrandid()));
		}
		product.setBuyexplain(formbean.getBuyexplain());
		product.setCode(formbean.getCode());
		product.setDescription(formbean.getDescription());
		product.setModel(formbean.getModel());
		product.setSexrequest(Sex.valueOf(formbean.getSex()));
		product.setWeight(formbean.getWeight());
		product.setType(productTypeService.find(formbean.getTypeid()));
		
		if(!ProductForm.validateImageFileType(formbean.getImagefile())){
			request.setAttribute("message", "�ļ���ʽ����ȷ,ֻ�����ϴ�gif/jpg/png/bmpͼƬ");
			request.setAttribute("urladdress", SiteUrl.readUrl("control.product.add"));
			return mapping.findForward("message");
		}		
		if(formbean.getImagefile().getFileSize()>204800){
			request.setAttribute("message", "ͼƬ���ܴ���200K");
			request.setAttribute("urladdress", SiteUrl.readUrl("control.product.add"));
			return mapping.findForward("message");
		}
		String ext = UploadfileForm.getExt(formbean.getImagefile());
		String filename = UUID.randomUUID().toString()+ "."+ext;//�����ļ�����			
		product.addProductStyle(new ProductStyle(formbean.getStylename(), filename));
		productInfoService.save(product);
		
		ProductForm.saveProductImageFile(request, formbean.getImagefile(),formbean.getTypeid(),
				product.getId(), filename);	
		request.setAttribute("message", "��ӳɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.list"));
		//���ɵ��ļ��������վ��Ŀ¼��html/product/���id/
		File saveDir = new File(request.getSession().getServletContext().getRealPath("/html/product/"+product.getType().getTypeid()));
		BuildHtmlFile.createProductHtml(product, saveDir);
		return mapping.findForward("message");
	}
	
	/**
	 * ��Ʒ�޸Ľ���
	 */
	@Permission(module="product", privilege="update")
	public ActionForward editUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		ProductInfo product = productInfoService.find(formbean.getProductid());
		formbean.setBaseprice(product.getBaseprice());
		if(product.getBrand()!=null) formbean.setBrandid(product.getBrand().getCode());
		formbean.setBuyexplain(product.getBuyexplain());
		formbean.setCode(product.getCode());
		formbean.setDescription(product.getDescription());
		formbean.setMarketprice(product.getMarketprice());
		formbean.setModel(product.getModel());
		formbean.setName(product.getName());
		formbean.setSellprice(product.getSellprice());
		formbean.setSex(product.getSexrequest().toString());
		formbean.setTypeid(product.getType().getTypeid());
		formbean.setWeight(product.getWeight());
		request.setAttribute("typename", product.getType().getName());
		request.setAttribute("brands", brandService.getScrollData().getResultlist());
		return mapping.findForward("edit");
	}
	
	/**
	 * ��Ʒ�޸�
	 */
	@Permission(module="product", privilege="update")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		ProductInfo product = productInfoService.find(formbean.getProductid());
		product.setName(formbean.getName());
		product.setBaseprice(formbean.getBaseprice());
		product.setSellprice(formbean.getSellprice());
		product.setMarketprice(formbean.getMarketprice());
		if(formbean.getBrandid()!=null && !"".equals(formbean.getBrandid().trim()) &&
				!product.getBrand().getCode().equals(formbean.getBrandid().trim())){
			product.setBrand(brandService.find(formbean.getBrandid()));
		}
		product.setBuyexplain(formbean.getBuyexplain());
		product.setCode(formbean.getCode());
		product.setDescription(formbean.getDescription());
		product.setModel(formbean.getModel());
		product.setSexrequest(Sex.valueOf(formbean.getSex()));
		product.setWeight(formbean.getWeight());
		product.setType(productTypeService.find(formbean.getTypeid()));		
		productInfoService.update(product);
		
		request.setAttribute("message", "�޸ĳɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.list"));
		
		File saveDir = new File(request.getSession().getServletContext().getRealPath("/html/product/"+product.getType().getTypeid()));
		BuildHtmlFile.createProductHtml(product, saveDir);
		
		return mapping.findForward("message");
	}
	
}
