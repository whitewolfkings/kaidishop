package cn.mmk.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import cn.mmk.bean.book.OrderState;

public class OrderStateConverter implements Converter{

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object value) {
		if(clazz==String.class){
			return value.toString();
		}
		if(clazz==OrderState.class){
			try{
				return OrderState.valueOf((String) value);
			}catch (Exception e) {}
		}
		return null;
	}

}
