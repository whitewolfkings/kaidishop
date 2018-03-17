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
 * 品牌管理
 *
 */
@Controller("/control/brand/manage")
public class BrandManageAction extends DispatchAction {
	@Resource(name="brandServiceBean")
	private BrandService brandService;
	
	/**
	 * 品牌查询界面
	 */
	@Permission(module="brand", privilege="view")
	public ActionForward queryUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		return mapping.findForward("query");
	}
	
	/**
	 * 品牌修改界面
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
	 * 品牌修改
	 */
	@Permission(module="brand", privilege="update")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BrandForm formbean = (BrandForm) form;
		if(!BrandForm.validateImageFileType(formbean.getLogofile())){
			request.setAttribute("message", "图片格式不正确");
			return mapping.findForward("message");
		}
		Brand brand = brandService.find(formbean.getCode());
		brand.setName(formbean.getName());
		if(formbean.getLogofile()!=null && formbean.getLogofile().getFileSize()>0){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
			String logopathdir = "/images/brand/"+ dateformat.format(new Date());//构建图片保存的目录
			//得到图片保存目录的真实路径
			String logorealpathdir = request.getSession().getServletContext().getRealPath(logopathdir);
			File logosavedir = new File(logorealpathdir);
			if(!logosavedir.exists()) logosavedir.mkdirs();//如果目录不存在就创建
			String ext = formbean.getLogofile().getFileName().substring(formbean.getLogofile().getFileName().lastIndexOf('.'));
			String imagename = UUID.randomUUID().toString()+ ext;//构建文件名称
			FileOutputStream fileoutstream = new FileOutputStream(new File(logorealpathdir, imagename));
			fileoutstream.write(formbean.getLogofile().getFileData());
			fileoutstream.close();
			String logopath = logopathdir+"/"+imagename;
			brand.setLogopath(logopath);
		}		
		brandService.update(brand);
		request.setAttribute("message", "品牌修改成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.brand.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * 品牌添加界面
	 */
	@Permission(module="brand", privilege="insert")
	public ActionForward addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}
	
	/**
	 * 品牌添加
	 */
	@Permission(module="brand", privilege="insert")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BrandForm formbean = (BrandForm) form;
		if(!BrandForm.validateImageFileType(formbean.getLogofile())){
			System.out.println(formbean.getLogofile().getContentType());
			request.setAttribute("message", "图片格式不正确");
			return mapping.findForward("message");
		}
		Brand brand = new Brand();
		brand.setName(formbean.getName());
		if(formbean.getLogofile()!=null && formbean.getLogofile().getFileSize()>0){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
			String logopathdir = "/images/brand/"+ dateformat.format(new Date());//构建图片保存的目录
			//得到图片保存目录的真实路径
			String logorealpathdir = request.getSession().getServletContext().getRealPath(logopathdir);
			File logosavedir = new File(logorealpathdir);
			if(!logosavedir.exists()) logosavedir.mkdirs();//如果目录不存在就创建
			String ext = formbean.getLogofile().getFileName().substring(formbean.getLogofile().getFileName().lastIndexOf('.'));
			String imagename = UUID.randomUUID().toString()+ ext;//构建文件名称
			FileOutputStream fileoutstream = new FileOutputStream(new File(logorealpathdir, imagename));
			fileoutstream.write(formbean.getLogofile().getFileData());
			fileoutstream.close();
			String logopath = logopathdir+"/"+imagename;
			brand.setLogopath(logopath);
		}		
		brandService.save(brand);
		request.setAttribute("message", "品牌添加成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.brand.list"));
		return mapping.findForward("message");
	}
}
