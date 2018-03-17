package cn.mmk.service.privilege.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.mmk.bean.privilege.SystemPrivilege;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.privilege.SystemPrivilegeService;

@Service
public class SystemPrivilegeServiceBean extends DaoSupport<SystemPrivilege> implements SystemPrivilegeService {
	
	public void batchSave(List<SystemPrivilege> privileges){
		for(SystemPrivilege p : privileges){
			save(p);
		}
	}
}
