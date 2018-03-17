package cn.mmk.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import cn.mmk.bean.privilege.SystemPrivilegePK;

public class SystemPrivilegePKConverter implements Converter{

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object value) {
		if(clazz==String.class){
			SystemPrivilegePK id = (SystemPrivilegePK) value;
			return id.getModule()+","+ id.getPrivilege();
		}
		if(clazz==SystemPrivilegePK.class){
			try{
				String idstr = (String)value;
				String[] ids = idstr.split(",");
				if(ids.length==2){
					return new SystemPrivilegePK(ids[0], ids[1]);
				}
			}catch (Exception e) {}
		}
		return null;
	}
}
