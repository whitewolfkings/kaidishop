package cn.mmk.web.action.user;

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
import cn.mmk.bean.user.Buyer;
import cn.mmk.service.user.BuyerService;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.user.BuyerForm;

/**
 * 顾客分页列表
 *
 */
@Controller("/control/user/list")
public class BuyerListAction extends Action {
	@Resource(name="buyerServiceBean") BuyerService buyerService;

	@Override @Permission(module="buyer", privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuyerForm formbean = (BuyerForm)form;
		PageView<Buyer> pageView = new PageView<Buyer>(10,  formbean.getPage());
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("regTime", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if("true".equals(formbean.getQuery())){
			if(formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())){
				if(params.size()>0) jpql.append(" and ");
				jpql.append("o.username=?").append(params.size()+1);
				params.add(formbean.getUsername().trim());
			}
			if(formbean.getEmail()!=null && !"".equals(formbean.getEmail().trim())){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.email like ?").append(params.size()+1);
				params.add("%"+ formbean.getEmail().trim()+ "%");
			}
			if(formbean.getRealname()!=null && !"".equals(formbean.getRealname().trim())){
				if(params.size()>0) jpql.append(" and ");
				jpql.append(" o.realname like ?").append(params.size()+1);
				params.add("%"+ formbean.getRealname().trim()+ "%");
			}
		}
		pageView.setQueryResult(
				buyerService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult()
						, jpql.toString(), params.toArray(), orderby)
		);
		request.setAttribute("pageView", pageView);
		return mapping.findForward("list");
	}

}
