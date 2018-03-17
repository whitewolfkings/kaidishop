package cn.mmk.web.action.product;

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
import cn.mmk.service.product.ProductInfoService;
import cn.mmk.service.product.ProductStyleService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.product.ProductForm;
import cn.mmk.web.formbean.uploadfile.UploadfileForm;

@Controller("/control/product/style/manage")
public class ProductStyleManageAction extends DispatchAction {
	@Resource(name="productInfoServiceBean")
	private ProductInfoService productInfoService;
	
	@Resource(name="productStyleServiceBean")
	private ProductStyleService productStyleService;
	
	/**
	 * ���ò�Ʒ�ϼ�
	 */
	@Permission(module="product", privilege="visible")
	public ActionForward visible(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		productStyleService.setVisibleStatu(formbean.getStylesids(), true);
		request.setAttribute("message", "���óɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.style.list")+"?productid="+ formbean.getProductid());
		return mapping.findForward("message");
	}
	
	/**
	 * ���ò�Ʒ�¼�
	 */
	@Permission(module="product", privilege="visible")
	public ActionForward disable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm) form;
		productStyleService.setVisibleStatu(formbean.getStylesids(), false);
		request.setAttribute("message", "���óɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.style.list")+"?productid="+ formbean.getProductid());
		return mapping.findForward("message");
	}
	/**
	 * ��ƷͼƬ�޸Ľ���
	 */
	@Permission(module="product", privilege="update")
	public ActionForward editUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm)form;
		ProductStyle productstyle = productStyleService.find(formbean.getProductstyleid());
		formbean.setStylename(productstyle.getName());
		request.setAttribute("imagepath", productstyle.getImageFullPath());
		return mapping.findForward("edit");
	}

	/**
	 * ��ƷͼƬ�޸�
	 */
	@Permission(module="product", privilege="update")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm)form;
		ProductStyle productstyle = productStyleService.find(formbean.getProductstyleid());
		productstyle.setName(formbean.getStylename());
		if(formbean.getImagefile()!=null && formbean.getImagefile().getFileSize()>0){
			if(!ProductForm.validateImageFileType(formbean.getImagefile())){
				request.setAttribute("message", "�ļ���ʽ����ȷ,ֻ�����ϴ�gif/jpg/png/bmpͼƬ");
				return mapping.findForward("message");
			}
			if(formbean.getImagefile().getFileSize()>204800){
				request.setAttribute("message", "ͼƬ���ܴ���200K");
				return mapping.findForward("message");
			}
			String ext = UploadfileForm.getExt(formbean.getImagefile());
			String filename = UUID.randomUUID().toString()+ "."+ext;//�����ļ�����	
			ProductForm.saveProductImageFile(request, formbean.getImagefile(),productstyle.getProduct().getType().getTypeid(),
					productstyle.getProduct().getId(), filename);
			productstyle.setImagename(filename);
		}
		productStyleService.update(productstyle);
		request.setAttribute("message", "�޸ĳɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.style.list")+"?productid="+ productstyle.getProduct().getId());
		return mapping.findForward("message");
	}
	/**
	 * ��ƷͼƬ��ӽ���
	 */
	@Permission(module="product", privilege="insert")
	public ActionForward addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}
	
	/**
	 * ��ƷͼƬ���
	 */
	@Permission(module="product", privilege="insert")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductForm formbean = (ProductForm)form;
		if(!ProductForm.validateImageFileType(formbean.getImagefile())){
			request.setAttribute("message", "�ļ���ʽ����ȷ,ֻ�����ϴ�gif/jpg/png/bmpͼƬ");
			return mapping.findForward("message");
		}
		if(formbean.getImagefile().getFileSize()>204800){
			request.setAttribute("message", "ͼƬ���ܴ���200K");
			return mapping.findForward("message");
		}
		ProductInfo product = productInfoService.find(formbean.getProductid());
		String ext = UploadfileForm.getExt(formbean.getImagefile());
		String filename = UUID.randomUUID().toString()+ "."+ext;//�����ļ�����	
		ProductForm.saveProductImageFile(request, formbean.getImagefile(),product.getType().getTypeid(),
				product.getId(),filename);
		ProductStyle productStyle = new ProductStyle(formbean.getStylename(), filename);
		productStyle.setProduct(product);
		productStyleService.save(productStyle);
		
		request.setAttribute("message", "��ӳɹ���");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.style.list")+"?productid="+ product.getId());
		return mapping.findForward("message");
	}
}
