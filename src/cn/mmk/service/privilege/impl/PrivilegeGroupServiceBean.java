package cn.mmk.service.privilege.impl;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.mmk.bean.privilege.Employee;
import cn.mmk.bean.privilege.PrivilegeGroup;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.privilege.PrivilegeGroupService;

@Service
public class PrivilegeGroupServiceBean extends DaoSupport<PrivilegeGroup> implements PrivilegeGroupService {

	@Override
	public void delete(Serializable... entityids) {
		for(Serializable id : entityids){
			PrivilegeGroup group = find(id);
			for(Employee employee : group.getEmployees()){
				employee.getGroups().remove(group);
			}
			em.remove(group);
		}
	}

	@Override
	public void save(PrivilegeGroup entity) {
		entity.setGroupid(UUID.randomUUID().toString());
		super.save(entity);
	}
	
}
