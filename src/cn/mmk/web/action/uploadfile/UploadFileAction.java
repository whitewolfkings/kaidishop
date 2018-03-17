package cn.mmk.web.action.uploadfile;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.PageView;
import cn.mmk.bean.uploadfile.UploadFile;
import cn.mmk.service.uploadfile.UploadFileService;
import cn.mmk.web.formbean.uploadfile.UploadfileForm;


@Controller("/control/uploadfile/list")
public class UploadFileAction extends Action {
	@Resource(name="uploadFileServiceBean")
	private UploadFileService uploadFileService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadfileForm formbean = (UploadfileForm) form;
		PageView<UploadFile> pageView = new PageView<UploadFile>(12, formbean.getPage());
		int firstindex = (pageView.getCurrentpage()-1)* pageView.getMaxresult();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		pageView.setQueryResult(uploadFileService.getScrollData(firstindex, 
				pageView.getMaxresult(), orderby));
		request.setAttribute("pageView", pageView);
		return mapping.findForward("list");
	}

}
