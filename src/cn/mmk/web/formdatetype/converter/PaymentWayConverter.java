package cn.mmk.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import cn.mmk.bean.book.PaymentWay;

public class PaymentWayConverter implements Converter{

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object value) {
		if(clazz==String.class){
			return value.toString();
		}
		if(clazz==PaymentWay.class){
			try{
				return PaymentWay.valueOf((String) value);
			}catch (Exception e) {}
		}
		return null;
	}
}
