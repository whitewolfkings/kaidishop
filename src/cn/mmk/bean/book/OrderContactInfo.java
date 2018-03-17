package cn.mmk.bean.book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import cn.mmk.bean.user.Gender;
/**
 * ��������ϵ��Ϣ
 *
 */
@Entity
public class OrderContactInfo {
	private Integer contactid;
	/* ���������� */
	private String buyerName;
	/* ��ϵ��ַ */
	private String address;
	/* �������� */
	private String email;
	/* �ʱ� */
	private String postalcode;
	/* ���� */
	private String tel;
	/* �ֻ� */
	private String mobile;
	/* �Ա� */
	private Gender gender = Gender.MAN;
	/* �������� */
	private Order order;
	
	@OneToOne(cascade=CascadeType.REFRESH, mappedBy="orderContactInfo")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Id @GeneratedValue
	public Integer getContactid() {
		return contactid;
	}
	public void setContactid(Integer contactid) {
		this.contactid = contactid;
	}
	@Column(length=8,nullable=false)
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	@Column(length=40,nullable=false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length=40)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=6)
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	@Column(length=18)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(length=11)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Enumerated(EnumType.STRING) @Column(length=5,nullable=false)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactid == null) ? 0 : contactid.hashCode());
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
		final OrderContactInfo other = (OrderContactInfo) obj;
		if (contactid == null) {
			if (other.contactid != null)
				return false;
		} else if (!contactid.equals(other.contactid))
			return false;
		return true;
	}
	
}
