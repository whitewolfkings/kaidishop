package cn.mmk.bean.privilege;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * 系统权限
 *
 */
@Entity
public class SystemPrivilege {
	private SystemPrivilegePK id;
	private String name;
	private Set<PrivilegeGroup> groups = new HashSet<PrivilegeGroup>();
	
	@ManyToMany(cascade=CascadeType.REFRESH, mappedBy="privileges")
	public Set<PrivilegeGroup> getGroups() {
		return groups;
	}
	public void setGroups(Set<PrivilegeGroup> groups) {
		this.groups = groups;
	}

	public SystemPrivilege(){}
	
	public SystemPrivilege(String module, String privilege, String name) {
		this.id = new SystemPrivilegePK(module, privilege);
		this.name = name;
	}
	
	public SystemPrivilege(SystemPrivilegePK id) {
		this.id = id;
	}
	
	@EmbeddedId
	public SystemPrivilegePK getId() {//JPA 实体的标识属性必须实现序列化
		return id;
	}
	public void setId(SystemPrivilegePK id) {
		this.id = id;
	}
	@Column(length=20,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SystemPrivilege other = (SystemPrivilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
