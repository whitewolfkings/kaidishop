package cn.mmk.web.action.book;

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
import cn.mmk.bean.book.Order;
import cn.mmk.service.book.OrderService;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.book.OrderForm;
/**
 * 锁定订单列表
 */
@Controller("/contorl/lockorder/list")
public class LockOrderListAction extends Action {
	@Resource OrderService orderService;

	@Override @Permission(module="order", privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		PageView<Order> pageView = new PageView<Order>(12 , formbean.getPage());
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "asc");
		pageView.setQueryResult(orderService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),
				"o.lockuser is not null", null, orderby));
		request.setAttribute("pageView", pageView);
		request.setAttribute("showButton", true);
		return mapping.findForward("list");
	}

}
