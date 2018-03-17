package cn.mmk.service.product;

import java.util.List;

import cn.mmk.bean.product.Brand;
import cn.mmk.bean.product.ProductInfo;
import cn.mmk.service.base.DAO;


public interface ProductInfoService extends DAO<ProductInfo> {
	/**
	 * ���ò�Ʒ�Ƿ��ϼ�
	 * @param productids ��Ʒid����
	 * @param statu trueΪ�ϼ�,falseΪ�¼�
	 */
	public void setVisibleStatu(Integer[] productids, boolean statu);
	
	/**
	 * ���ò�Ʒ���Ƽ�״̬
	 * @param productids ��Ʒid����
	 * @param statu trueΪ�Ƽ�,�����Ƽ�
	 */
	public void setCommendStatu(Integer[] productids, boolean statu);
	/**
	 * ��ȡ����²�Ʒ��ʹ�õ���Ʒ��
	 * @param typeids ��Ʒ���id
	 * @return
	 */
	public List<Brand> getBrandsByProductTypeid(Integer[] typeids);
	
	/**
	 * ��ȡ������ಢ�ұ��Ƽ��Ĳ�Ʒ
	 * @param typeid ���id
	 * @param maxResult ��ȡ�Ĳ�Ʒ����
	 * @return
	 */
	public List<ProductInfo> getTopSell(Integer typeid, int maxResult);
	/**
	 * ��ȡָ��ID�Ĳ�Ʒ
	 * @param productids ��Ʒid����
	 * @param maxResult ����ȡ��������¼
	 * @return
	 */
	public List<ProductInfo> getViewHistory(Integer[] productids, int maxResult);
}
