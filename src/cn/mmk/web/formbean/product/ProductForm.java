package cn.mmk.web.formbean.product;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

import cn.mmk.utils.ImageSizer;
import cn.mmk.web.formbean.BaseForm;


public class ProductForm extends BaseForm {
	private Integer productid;
	private Integer[] productids;
	/** ���� **/
	private String code;
	/** ��Ʒ���� **/
	private String name;
	/** Ʒ�� **/
	private String brandid;
	/** �ͺ� **/
	private String model;
	/** �׼�(�ɹ������ļ۸�) **/
	private Float baseprice;
	/** �г��� **/
	private Float marketprice;
	/** ���ۼ� **/
	private Float sellprice;
	/** ���� ��λ:�� **/
	private Integer weight;
	/** ��Ʒ��� **/
	private String description;
	/** ����˵�� **/
	private String buyexplain;
	/** ��Ʒ���� **/
	private Integer typeid;
	/** �Ա�Ҫ�� **/
	private String sex;
	private Integer[] stylesids;
	private String stylename;
	private FormFile imagefile;
	private Float startsellprice;
	private Float endsellprice;
	private Float startbaseprice;
	private Float endbaseprice;
	private Integer productstyleid;
	
	private String word;
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Integer[] getStylesids() {
		return stylesids;
	}
	public void setStylesids(Integer[] stylesids) {
		this.stylesids = stylesids;
	}
	public Integer getProductstyleid() {
		return productstyleid;
	}
	public void setProductstyleid(Integer productstyleid) {
		this.productstyleid = productstyleid;
	}
	public Integer[] getProductids() {
		return productids;
	}
	public void setProductids(Integer[] productids) {
		this.productids = productids;
	}
	public Float getStartbaseprice() {
		return startbaseprice;
	}
	public void setStartbaseprice(Float startbaseprice) {
		this.startbaseprice = startbaseprice;
	}
	public Float getEndbaseprice() {
		return endbaseprice;
	}
	public void setEndbaseprice(Float endbaseprice) {
		this.endbaseprice = endbaseprice;
	}
	public Float getStartsellprice() {
		return startsellprice;
	}
	public void setStartsellprice(Float startsellprice) {
		this.startsellprice = startsellprice;
	}
	public Float getEndsellprice() {
		return endsellprice;
	}
	public void setEndsellprice(Float endsellprice) {
		this.endsellprice = endsellprice;
	}
	public String getStylename() {
		return stylename;
	}
	public void setStylename(String stylename) {
		this.stylename = stylename;
	}
	public FormFile getImagefile() {
		return imagefile;
	}
	public void setImagefile(FormFile imagefile) {
		this.imagefile = imagefile;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Float getBaseprice() {
		return baseprice;
	}
	public void setBaseprice(Float baseprice) {
		this.baseprice = baseprice;
	}
	public Float getMarketprice() {
		return marketprice;
	}
	public void setMarketprice(Float marketprice) {
		this.marketprice = marketprice;
	}
	public Float getSellprice() {
		return sellprice;
	}
	public void setSellprice(Float sellprice) {
		this.sellprice = sellprice;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBuyexplain() {
		return buyexplain;
	}
	public void setBuyexplain(String buyexplain) {
		this.buyexplain = buyexplain;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * �����ƷͼƬ
	 * @param request
	 * @param imagefile �ϴ��Ĳ�ƷͼƬ
	 * @param productTypeId ��Ʒ���id
	 * @param productId ��Ʒid
	 * @return
	 * @throws Exception
	 */
	public static void saveProductImageFile(HttpServletRequest request, FormFile imagefile,
			Integer productTypeId,Integer productId,String filename) throws Exception{
		String ext = BaseForm.getExt(imagefile);					
		String pathdir = "/images/product/"+ productTypeId+ "/"+ productId+ "/prototype";//�����ļ������Ŀ¼
		//�õ�ͼƬ����Ŀ¼����ʵ·��
		String realpathdir = request.getSession().getServletContext().getRealPath(pathdir);
		File savedir = new File(realpathdir);
		File file = saveFile(savedir, filename, imagefile.getFileData());		
		String pathdir140 = "/images/product/"+ productTypeId+ "/"+ productId+ "/140x";//140��ȵ�ͼƬ����Ŀ¼
		String realpathdir140 = request.getSession().getServletContext().getRealPath(pathdir140);
		File savedir140 = new File(realpathdir140);
		if(!savedir140.exists()) savedir140.mkdirs();//���Ŀ¼�����ھʹ���
		File file140 = new File(realpathdir140, filename);
		ImageSizer.resize(file, file140, 140, ext);
	}

	
}
