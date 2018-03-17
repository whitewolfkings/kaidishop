package cn.mmk.service.privilege;

import java.util.List;

import cn.mmk.bean.privilege.SystemPrivilege;
import cn.mmk.service.base.DAO;

public interface SystemPrivilegeService extends DAO<SystemPrivilege> {
	/**
	 * ��������ϵͳȨ��
	 * @param privileges
	 */
	public void batchSave(List<SystemPrivilege> privileges);
}
