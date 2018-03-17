package junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mmk.bean.product.Brand;
import cn.mmk.bean.product.ProductInfo;
import cn.mmk.bean.product.ProductStyle;
import cn.mmk.bean.product.ProductType;
import cn.mmk.bean.product.Sex;
import cn.mmk.service.product.ProductInfoService;


public class ProductInfoServiceTest {
	private static ApplicationContext cxt;
	private static ProductInfoService productInfoService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			productInfoService = (ProductInfoService)cxt.getBean("productInfoServiceBean");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void xxxx() {}
	
	@Test
	public void testGetTopSell() {
		List<ProductInfo> products = productInfoService.getTopSell(1, 2);
		for(ProductInfo p : products){
			System.out.println(p.getName());
		}
	}
	
	@Test
	public void testSave() {
		ProductInfo product = new ProductInfo();
		product.setName("足球sss");
		product.setBaseprice(100f);
		product.setBrand(new Brand("0ed7bfff-bbe8-40a9-8593-d43f5dfcbc63"));
		product.setCode("UI002");
		product.setDescription("好产品");
		product.setMarketprice(600f);
		product.setModel("T60");
		product.setSellprice(300f);
		product.setSexrequest(Sex.NONE);
		product.addProductStyle(new ProductStyle("红色", "xxx.gif"));
		product.setType(new ProductType(3));
		product.setWeight(50);
		productInfoService.save(product);
		System.out.println(product.getId());
	}

}
