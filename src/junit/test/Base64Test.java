package junit.test;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Test {
	@Test public void encode(){
		String url = "/customer/shopping/orderconfirm.do?id=1&name=xxx";//原文
		String code = new String(Base64.encodeBase64(url.getBytes()));//编码后的字符串
		
		//String url = new String(Base64.encodeBase64("/customer/shopping/comfirm.do?id=xxx&name=xxx".getBytes()));
		System.out.println(code);
	}
	
	@Test public void decode(){
		String url = new String(Base64.decodeBase64("L2N1c3RvbWVyL3Nob3BwaW5nL29yZGVyY29uZmlybS5kbz9pZD0xJm5hbWU9eHh4".getBytes()));
		System.out.println(url);
	}
	
	@Test public void xxx(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHH");
		String time = format.format(new Date());
		
		//Date todayDate = format.parse(time.replaceAll("\\d{2}\\b", "00"), new ParsePosition(0));
		System.out.println(time);
	}
}
