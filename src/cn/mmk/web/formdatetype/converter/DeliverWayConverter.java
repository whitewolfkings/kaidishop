package cn.mmk.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import cn.mmk.bean.book.DeliverWay;

public class DeliverWayConverter implements Converter{

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object value) {
		if(clazz==String.class){
			return value.toString();
		}
		if(clazz==DeliverWay.class){
			try{
				return DeliverWay.valueOf((String) value);
			}catch (Exception e) {}
		}
		return null;
	}

}
