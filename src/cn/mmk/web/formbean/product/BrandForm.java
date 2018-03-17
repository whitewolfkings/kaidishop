package cn.mmk.web.formbean.product;

import org.apache.struts.upload.FormFile;

import cn.mmk.web.formbean.BaseForm;

public class BrandForm extends BaseForm {
	private String name;
	private FormFile logofile;
	private String code;
	private String logoimagepath;
	

	public String getLogoimagepath() {
		return logoimagepath;
	}

	public void setLogoimagepath(String logoimagepath) {
		this.logoimagepath = logoimagepath;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public FormFile getLogofile() {
		return logofile;
	}

	public void setLogofile(FormFile logofile) {
		this.logofile = logofile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
