package cn.mmk.web.formbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class BaseForm extends ActionForm {
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(BaseForm.class.getClassLoader().getResourceAsStream("arrowuploadfiletype.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 获取当前页 **/
	private int page;
	/** 设置是否进行查找 **/
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	public int getPage() {
		return page<1? 1 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * 验证上传文件类型是否属于图片格式
	 * @return
	 */
	public static boolean validateImageFileType(FormFile formfile){
		if(formfile!=null && formfile.getFileSize()>0){
			List<String> arrowType = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg");
			List<String> arrowExtension = Arrays.asList("gif","jpg","bmp","png");
			String ext = getExt(formfile);
			return arrowType.contains(formfile.getContentType().toLowerCase()) && arrowExtension.contains(ext);
		}
		return true;
	}
	
	public static String getExt(FormFile formfile){
		return formfile.getFileName().substring(formfile.getFileName().lastIndexOf('.')+1).toLowerCase();
	}
	/**
	 * 验证上传文件是否属于图片/flash动画/word文件/exe文件/pdf文件/TxT文件/xls文件/ppt文件
	 * @param formfile
	 * @return
	 */
	public static boolean validateFileType(FormFile formfile){
		if(formfile!=null && formfile.getFileSize()>0){
			String ext = formfile.getFileName().substring(formfile.getFileName().lastIndexOf('.')+1).toLowerCase();
			List<String> arrowType = new ArrayList<String>();
			for(Object key : properties.keySet()){
				String value = (String)properties.get(key);
				String[] values = value.split(",");
				for(String v : values){
					arrowType.add(v.trim());
				}
			}
			return arrowType.contains(formfile.getContentType().toLowerCase()) && properties.keySet().contains(ext);
		}
		return true;
	}
	
	/**
	 * 保存文件
	 * @param savedir 存放目录
	 * @param fileName 文件名称
	 * @param data 保存的内容
	 * @return 保存的文件
	 * @throws Exception
	 */
	public static File saveFile(File savedir, String fileName, byte[] data) throws Exception{
		if(!savedir.exists()) savedir.mkdirs();//如果目录不存在就创建
		File file = new File(savedir, fileName);
		FileOutputStream fileoutstream = new FileOutputStream(file);
		fileoutstream.write(data);
		fileoutstream.close();
		return file;
	}
	/*
	public boolean validateFileType(String propertyName) throws Exception{
		PropertyDescriptor[] propertydesc = Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors();
		boolean exsit = false;
		for(PropertyDescriptor property : propertydesc){
			if(property.getName().equals(propertyName)){
				exsit = true;
				Method method = property.getReadMethod();
				if(method!=null){
					FormFile formfile = (FormFile) method.invoke(this);
					if(formfile!=null && formfile.getFileSize()>0){
						List<String> arrowType = Arrays.asList("image/bmp","image/png","image/gif","image/jpeg","image/pjpeg");
						return arrowType.contains(formfile.getContentType().toLowerCase());
					}
				}else{
					new RuntimeException(propertyName+"属性的getter方法不存在");
				}
			}
		}
		if(!exsit) new RuntimeException(propertyName+"属性不存在");
		return true;
	}*/
}
