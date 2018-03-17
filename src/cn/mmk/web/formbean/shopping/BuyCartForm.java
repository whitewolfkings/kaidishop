package cn.mmk.web.formbean.shopping;

import cn.mmk.web.formbean.BaseForm;

public class BuyCartForm extends BaseForm {
	private Integer productid;
	private Integer styleid;
	private String directUrl;

	public String getDirectUrl() {
		return directUrl;
	}

	public void setDirectUrl(String directUrl) {
		this.directUrl = directUrl;
	}

	public void setBuyitemid(String buyitemid) {
		if(buyitemid!=null){
			String[] ids = buyitemid.split("-");
			if(ids.length==2){
				productid = new Integer(ids[0]);
				styleid = new Integer(ids[1]);
			}
		}
	}
	
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getStyleid() {
		return styleid;
	}
	public void setStyleid(Integer styleid) {
		this.styleid = styleid;
	}
}
