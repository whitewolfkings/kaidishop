package cn.mmk.service.book.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.mmk.bean.book.Order;
import cn.mmk.bean.book.OrderItem;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.book.OrderItemService;

@Service
public class OrderItemServiceBean extends DaoSupport<OrderItem> implements
		OrderItemService {
	
	@Override
	public void delete(Serializable... entityids) {
		for(Serializable itemid : entityids){
			OrderItem item = this.find(itemid);			
			Order order = item.getOrder();
			order.getItems().remove(item);
			float result = 0f;
			for(OrderItem oItem : order.getItems()){
				result += oItem.getProductPrice() * oItem.getAmount();
			}
			order.setProductTotalPrice(result);
			order.setTotalPrice(order.getProductTotalPrice()+ order.getDeliverFee());
			order.setPayablefee(order.getTotalPrice());
			em.remove(item);
		}
	}

	public void updateAmount(Integer itemid, int amount){
		OrderItem item = this.find(itemid);
		item.setAmount(amount);
		Order order = item.getOrder();
		float result = 0f;
		for(OrderItem oItem : order.getItems()){
			result += oItem.getProductPrice() * oItem.getAmount();
		}
		order.setProductTotalPrice(result);
		order.setTotalPrice(order.getProductTotalPrice()+ order.getDeliverFee());
		order.setPayablefee(order.getTotalPrice());
	}
}
