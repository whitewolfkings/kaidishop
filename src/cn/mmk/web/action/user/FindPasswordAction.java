package cn.mmk.web.action.user;

import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.user.Buyer;
import cn.mmk.mail.EmailSender;
import cn.mmk.service.user.BuyerService;
import cn.mmk.utils.MD5;
import cn.mmk.utils.SiteUrl;
import cn.mmk.web.formbean.user.BuyerForm;
/**
 * �һ�����
 */
@Controller("/user/post")
public class FindPasswordAction extends DispatchAction {
	@Resource BuyerService buyerService;
	/**
	 * �һ�����֮�����ʼ�
	 */
	public ActionForward getpassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyerForm formbean = (BuyerForm) form;
		if(formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())){
			if(buyerService.exsit(formbean.getUsername().trim())){
				Buyer buyer = buyerService.find(formbean.getUsername().trim());
				Template template = Velocity.getTemplate("mailContent.html");
				VelocityContext context = new VelocityContext();
				context.put("username", buyer.getUsername());
				context.put("validateCode", MD5.MD5Encode(buyer.getUsername()+ buyer.getPassword()));
				StringWriter writer = new StringWriter(); 
				template.merge(context, writer);
				String content = writer.toString();
				EmailSender.send(buyer.getEmail(), "�Ͱ��˶���--�һ������ʼ�", content, "text/html");
				return mapping.findForward("findpassword2");
			}else{
				request.setAttribute("message", "�û�������");
			}			
		}else{
			request.setAttribute("message", "�������û���");
		}
		return mapping.findForward("findpassword");
	}
	
	/**
	 * �һ�����֮��ʾ�����޸Ľ���
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyerForm formbean = (BuyerForm) form;
		if(formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())){
			if(buyerService.exsit(formbean.getUsername().trim())){
				Buyer buyer = buyerService.find(formbean.getUsername().trim());
				String code = MD5.MD5Encode(buyer.getUsername()+ buyer.getPassword());
				if(code.equals(formbean.getValidateCode())){//У��ͨ������ʾ��Դ�Ϸ�					
					return mapping.findForward("findPassword3");
				}
			}			
		}
		return mapping.findForward("errorresult");
	}
	
	/**
	 * �һ�����֮�޸�����
	 */
	public ActionForward changepassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyerForm formbean = (BuyerForm) form;
		if(formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())){
			if(buyerService.exsit(formbean.getUsername().trim())){
				Buyer buyer = buyerService.find(formbean.getUsername().trim());
				String code = MD5.MD5Encode(buyer.getUsername()+ buyer.getPassword());
				if(code.equals(formbean.getValidateCode())){//У��ͨ������ʾ��Դ�Ϸ�					
					buyerService.updatePassword(buyer.getUsername(), formbean.getPassword().trim());
					request.setAttribute("message", "�����޸ĳɹ�");
					request.setAttribute("urladdress", "/user/logon.do");
					return mapping.findForward("message");
				}
			}			
		}
		return mapping.findForward("errorresult");
	}
}
