package junit.test;

import java.util.Date;

import org.junit.Test;

import cn.mmk.web.formdatetype.converter.DateConverter;

public class ConvertDateTest {
	@Test
	public void test(){
		String date = "2008-9-10";
		DateConverter c = new DateConverter();
		System.out.println(c.convert(Date.class, date));
	}
}
