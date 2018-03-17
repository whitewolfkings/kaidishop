package cn.mmk.web.formbean.shopping;

import org.apache.commons.codec.binary.Base64;

import cn.mmk.bean.book.DeliverWay;
import cn.mmk.bean.book.PaymentWay;
import cn.mmk.bean.user.Gender;
import cn.mmk.web.formbean.BaseForm;

public class DeliverForm extends BaseForm {
	private String recipients;
	private Gender gender;
	private String address;
	private String email;
	private String postalcode;
	private String tel;
	private String mobile;
	private Boolean buyerIsrecipients;
	
	private String buyer;
	private Gender buyer_gender;
	private String buyer_address;
	private String buyer_postalcode;
	private String buyer_mobile;
	private String buyer_tel;
	
	private DeliverWay deliverway;
	private PaymentWay paymentway;
	private String requirement;
	private String delivernote;
	
	private String directUrl;
	private String note;
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDirectUrl() {
		return directUrl;
	}
	public void setDirectUrl(String directUrl) {
		if(directUrl!=null && !"".equals(directUrl.trim())){
			this.directUrl = new String(Base64.decodeBase64(directUrl.trim().getBytes()));//获取解码后的url
		}
	}
	public DeliverWay getDeliverway() {
		return deliverway;
	}
	public void setDeliverway(DeliverWay deliverway) {
		this.deliverway = deliverway;
	}
	public PaymentWay getPaymentway() {
		return paymentway;
	}
	public void setPaymentway(PaymentWay paymentway) {
		this.paymentway = paymentway;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public String getDelivernote() {
		return delivernote;
	}
	public void setDelivernote(String delivernote) {
		this.delivernote = delivernote;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Boolean getBuyerIsrecipients() {
		return buyerIsrecipients;
	}
	public void setBuyerIsrecipients(Boolean buyerIsrecipients) {
		this.buyerIsrecipients = buyerIsrecipients;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public Gender getBuyer_gender() {
		return buyer_gender;
	}
	public void setBuyer_gender(Gender buyer_gender) {
		this.buyer_gender = buyer_gender;
	}
	public String getBuyer_address() {
		return buyer_address;
	}
	public void setBuyer_address(String buyer_address) {
		this.buyer_address = buyer_address;
	}
	public String getBuyer_postalcode() {
		return buyer_postalcode;
	}
	public void setBuyer_postalcode(String buyer_postalcode) {
		this.buyer_postalcode = buyer_postalcode;
	}
	public String getBuyer_mobile() {
		return buyer_mobile;
	}
	public void setBuyer_mobile(String buyer_mobile) {
		this.buyer_mobile = buyer_mobile;
	}
	public String getBuyer_tel() {
		return buyer_tel;
	}
	public void setBuyer_tel(String buyer_tel) {
		this.buyer_tel = buyer_tel;
	}
	
}
