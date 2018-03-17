package junit.test;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mmk.service.book.OrderItemService;

public class OrderItemServiceTest {
	private static OrderItemService itemService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			itemService = (OrderItemService)cxt.getBean("orderItemServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test public void updateAmount(){
		itemService.updateAmount(10, 1);
	}
	@Test public void delete(){
		itemService.delete(10);
	}
}
