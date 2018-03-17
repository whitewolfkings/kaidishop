package junit.test;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mmk.bean.book.DeliverWay;
import cn.mmk.bean.book.Order;
import cn.mmk.bean.book.PaymentWay;
import cn.mmk.service.book.GeneratedOrderidService;
import cn.mmk.service.book.OrderService;

public class OrderServiceTest {
	private static OrderService orderService;
	private static GeneratedOrderidService generatedOrderidService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			orderService = (OrderService)cxt.getBean("orderServiceBean");
			generatedOrderidService = (GeneratedOrderidService)cxt.getBean("generatedOrderidServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateDeliverFee(){
		orderService.updateDeliverFee("09120200000006", 20f);
	}
	
	@Test public void updateDeliverWay(){
		orderService.updateDeliverWay("09120200000001", DeliverWay.EXIGENCEEXPRESSDELIVERY);
	}
	
	@Test
	public void save(){
		//generatedOrderidService.init();
	    Order order = orderService.createOrder(null, null);
		System.out.println(order.getOrderid());
	}
	
	@Test
	public void updatePayementWay(){
		orderService.updatePaymentWay("09120200000001", PaymentWay.NET);
	}
}
