package cn.mmk.web.formbean.privilege;

import java.util.Date;

import org.apache.struts.upload.FormFile;

import cn.mmk.bean.user.Gender;
import cn.mmk.web.formbean.BaseForm;

public class EmployeeForm extends BaseForm {
	private String username;
	private String password;
	private String realname;
	private Gender gender;
	private FormFile picture;
	private String cardno;
	private Date birthday;
	private String address;
	private String phone;
	private String email;
	private String degree;
	private String school;
	private String departmentid;
	private String[] groupids;
	
	public String[] getGroupids() {
		return groupids;
	}
	public void setGroupids(String[] groupids) {
		this.groupids = groupids;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public FormFile getPicture() {
		return picture;
	}
	public void setPicture(FormFile picture) {
		this.picture = picture;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
}
