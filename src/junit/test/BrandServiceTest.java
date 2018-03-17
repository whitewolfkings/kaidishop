package junit.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mmk.bean.product.Brand;
import cn.mmk.service.product.BrandService;


public class BrandServiceTest {
	private static BrandService brandService;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			brandService = (BrandService)cxt.getBean("brandServiceBean");
			String[] ss = cxt.getAliases("brandServiceBean");
			for(String s :ss){
				System.out.println(s);
			}
			cxt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		
		//for(int i=0;i<20;i++)
		//brandService.save(new Brand("Ô¶Ñôè¤Ù¤","/images/brand/2008/08/02/10/dslfkdsjfkjdsfldsfdsf.gif"));
	}

}
