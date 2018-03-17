package cn.mmk.web.action.product;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.product.Brand;
import cn.mmk.service.product.BrandService;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.product.BrandForm;

/**
 * Ʒ�ƹ���
 *
 */
@Controller("/control/brand/manage")
public class BrandManageAction extends DispatchAction {
	@Resource(name="brandServiceBean")
	private BrandService brandService;
	
	/**
	 * Ʒ�Ʋ�ѯ����
	 */
	@Permission(module="brand", privilege="view")
	public ActionForward queryUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		return mapping.findForward("query");
	}
	
	/**
	 * Ʒ���޸Ľ���
	 */
	@Permission(module="brand", privilege="update")
	public ActionForward editUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BrandForm formbean = (BrandForm) form;
		Brand brand = brandService.find(formbean.getCode());
		formbean.setName(brand.getName());
		formbean.setLogoimagepath(brand.getLogopath());
		return mapping.findForward("edit");
	}
	
	/**
	 * Ʒ���޸�
	 */
	@Permission(module="brand", privilege="update")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BrandForm formbean = (BrandForm) form;
		if(!BrandForm.validateImageFileType(formbean.getLogofile())){
			request.setAttribute("message", "ͼƬ��ʽ����ȷ");
			return mapping.findForward("message");
		}
		Brand brand = brandService.find(formbean.getCode());
		brand.setName(formbean.getName());
		if(formbean.getLogofile()!=null && formbean.getLogofile().getFileSize()>0){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
			String logopathdir = "/images/brand/"+ dateformat.format(new Date());//����ͼƬ�����Ŀ¼
			//�õ�ͼƬ����Ŀ¼����ʵ·��
			String logorealpathdir = request.getSession().getServletContext().getRealPath(logopathdir);
			File logosavedir = new File(logorealpathdir);
			if(!logosavedir.exists()) logosavedir.mkdirs();//���Ŀ¼�����ھʹ���
			String ext = formbean.getLogofile().getFileName().substring(formbean.getLogofile().getFileName().lastIndexOf('.'));
			String imagename = UUID.randomUUID().toString()+ ext;//�����ļ�����
			FileOutputStream fileoutstream = new FileOutputStream(new File(logorealpathdir, imagename));
			fileoutstream.write(formbean.getLogofile().getFileData());
			fileoutstream.close();
			String logopath = logopathdir+"/"+imagename;
			brand.setLogopath(logopath);
		}		
		brandService.update(brand);
		request.setAttribute("message", "Ʒ���޸ĳɹ�");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.brand.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * Ʒ����ӽ���
	 */
	@Permission(module="brand", privilege="insert")
	public ActionForward addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}
	
	/**
	 * Ʒ�����
	 */
	@Permission(module="brand", privilege="insert")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BrandForm formbean = (BrandForm) form;
		if(!BrandForm.validateImageFileType(formbean.getLogofile())){
			System.out.println(formbean.getLogofile().getContentType());
			request.setAttribute("message", "ͼƬ��ʽ����ȷ");
			return mapping.findForward("message");
		}
		Brand brand = new Brand();
		brand.setName(formbean.getName());
		if(formbean.getLogofile()!=null && formbean.getLogofile().getFileSize()>0){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
			String logopathdir = "/images/brand/"+ dateformat.format(new Date());//����ͼƬ�����Ŀ¼
			//�õ�ͼƬ����Ŀ¼����ʵ·��
			String logorealpathdir = request.getSession().getServletContext().getRealPath(logopathdir);
			File logosavedir = new File(logorealpathdir);
			if(!logosavedir.exists()) logosavedir.mkdirs();//���Ŀ¼�����ھʹ���
			String ext = formbean.getLogofile().getFileName().substring(formbean.getLogofile().getFileName().lastIndexOf('.'));
			String imagename = UUID.randomUUID().toString()+ ext;//�����ļ�����
			FileOutputStream fileoutstream = new FileOutputStream(new File(logorealpathdir, imagename));
			fileoutstream.write(formbean.getLogofile().getFileData());
			fileoutstream.close();
			String logopath = logopathdir+"/"+imagename;
			brand.setLogopath(logopath);
		}		
		brandService.save(brand);
		request.setAttribute("message", "Ʒ����ӳɹ�");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.brand.list"));
		return mapping.findForward("message");
	}
}
