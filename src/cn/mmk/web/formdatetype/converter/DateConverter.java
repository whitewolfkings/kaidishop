package cn.mmk.web.formdatetype.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object value) {
		if(value==null || "".equals((String)value)) return null;
		if(value instanceof Date) return value;
		DateFormat convert = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return convert.parse((String) value);
		} catch (ParseException e) {
			DateFormat convert2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return convert2.parse((String) value);
			} catch (ParseException e1) {}
		}
		return null;
	}

}
