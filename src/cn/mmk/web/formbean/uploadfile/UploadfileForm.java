package cn.mmk.web.formbean.uploadfile;

import org.apache.struts.upload.FormFile;

import cn.mmk.web.formbean.BaseForm;

public class UploadfileForm extends BaseForm {
	private FormFile uploadfile;
	private Integer[] fileids;
	
	public Integer[] getFileids() {
		return fileids;
	}

	public void setFileids(Integer[] fileids) {
		this.fileids = fileids;
	}

	public FormFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(FormFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	
}
