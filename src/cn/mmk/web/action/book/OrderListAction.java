package cn.mmk.web.action.book;

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
import cn.mmk.bean.book.Order;
import cn.mmk.bean.book.OrderState;
import cn.mmk.service.book.OrderService;
import cn.mmk.web.action.privilege.Permission;
import cn.mmk.web.formbean.book.OrderForm;
/**
 * 订单分页列表
 */
@Controller("/control/order/list")
public class OrderListAction extends Action {
	@Resource OrderService orderService;

	@Override  @Permission(module="order", privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm formbean = (OrderForm)form;
		PageView<Order> pageView = new PageView<Order>(12, formbean.getPage());
		//如果传递了订单状态state请求参数,那么查询该状态下的订单,否则查询待审核状态的订单
		OrderState state = formbean.getState()!=null ? formbean.getState() : OrderState.WAITCONFIRM;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "asc");
		if("true".equals(formbean.getQuery())){
			StringBuilder sb = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			if(formbean.getOrderid()!=null && !"".equals(formbean.getOrderid().trim())){
				params.add("%"+ formbean.getOrderid().trim() +"%");
				sb.append("o.orderid like ?").append(params.size());
			}
			if(formbean.getState()!=null){
				if(!params.isEmpty()) sb.append(" and ");
				params.add(formbean.getState());
				sb.append("o.state=?").append(params.size());
			}
			if(formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())){
				if(!params.isEmpty()) sb.append(" and ");
				params.add("%"+ formbean.getUsername().trim() +"%");
				sb.append("o.buyer.username like ?").append(params.size());
			}
			if(formbean.getRecipients()!=null && !"".equals(formbean.getRecipients().trim())){
				if(!params.isEmpty()) sb.append(" and ");
				params.add("%"+ formbean.getRecipients().trim() +"%");
				sb.append("o.orderDeliverInfo.recipients like ?").append(params.size());
			}
			if(formbean.getBuyer()!=null && !"".equals(formbean.getBuyer().trim())){
				if(!params.isEmpty()) sb.append(" and ");
				params.add("%"+ formbean.getBuyer().trim() +"%");
				sb.append("o.orderContactInfo.buyerName like ?").append(params.size());
			}
			pageView.setQueryResult(orderService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),
					sb.toString(), params.toArray(), orderby));
		}else{
			pageView.setQueryResult(orderService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),
					"o.state=?1", new Object[]{state}, orderby));
		}
		request.setAttribute("pageView", pageView);
		
		return mapping.findForward("list");
	}

}
