package cn.mmk.web.action.product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.product.ProductType;
import cn.mmk.service.product.ProductTypeService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.product.ProductTypeForm;


@Controller("/control/product/type/manage")
public class ProductTypeManageAction extends DispatchAction {
	@Resource(name="productTypeServiceBean")
	private ProductTypeService productTypeService;
	
	/**
	 * ��ѯ����
	 */
	@Permission(module="productType", privilege="view")
	public ActionForward queryUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("query");
	}
	
	/**
	 * �����ӽ���
	 */
	@Permission(module="productType", privilege="insert")
	public ActionForward addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}
	
	/**
	 * ������
	 */
	@Permission(module="productType", privilege="insert")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductTypeForm formbean = (ProductTypeForm) form;
		ProductType type = new ProductType(formbean.getName(),formbean.getNote());
		if(formbean.getParentid()!=null && formbean.getParentid()>0) type.setParent(new ProductType(formbean.getParentid()));
		productTypeService.save(type);
		request.setAttribute("message", "������ɹ�");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.type.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * ����޸Ľ���
	 */
	@Permission(module="productType", privilege="update")
	public ActionForward editUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductTypeForm formbean = (ProductTypeForm) form;
		ProductType type = productTypeService.find(formbean.getTypeid());
		formbean.setName(type.getName());
		formbean.setNote(type.getNote());
		return mapping.findForward("edit");
	}
	
	/**
	 * ����޸�
	 */
	@Permission(module="productType", privilege="update")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductTypeForm formbean = (ProductTypeForm) form;
		ProductType type = productTypeService.find(formbean.getTypeid());
		type.setName(formbean.getName());
		type.setNote(formbean.getNote());
		productTypeService.update(type);
		request.setAttribute("message", "�޸����ɹ�");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.product.type.list"));
		return mapping.findForward("message");
	}
}
