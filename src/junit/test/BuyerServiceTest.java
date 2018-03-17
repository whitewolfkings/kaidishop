package junit.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mmk.bean.user.Buyer;
import cn.mmk.bean.user.ContactInfo;
import cn.mmk.service.product.BrandService;
import cn.mmk.service.user.BuyerService;


public class BuyerServiceTest {
	private static BuyerService buyerService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			buyerService = (BuyerService)cxt.getBean("buyerServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEnable() {
		fail("Not yet implemented");
	}

	@Test
	public void testExsit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		Buyer buyer = new Buyer("lihuoming","123456","test@sina.com");
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setAddress("北京市朝阳区左家庄");
		contactInfo.setMobile("13671323507");
		contactInfo.setPhone("010-64469090-9");
		contactInfo.setPostalcode("100028");
		buyer.setContactInfo(contactInfo);
		buyer.setRealname("黎明");
		buyerService.save(buyer);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfTIntIntStringObjectArrayLinkedHashMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfTIntIntStringObjectArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfTIntIntLinkedHashMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfTIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfT() {
		fail("Not yet implemented");
	}

}
