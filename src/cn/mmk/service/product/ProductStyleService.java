package cn.mmk.service.product;

import cn.mmk.bean.product.ProductStyle;
import cn.mmk.service.base.DAO;


public interface ProductStyleService extends DAO<ProductStyle> {
	/**
	 * ���ø���ʽ�Ĳ�Ʒ�Ƿ��ϼ�
	 * @param productstyleids ��Ʒid����
	 * @param statu trueΪ�ϼ�,falseΪ�¼�
	 */
	public void setVisibleStatu(Integer[] productstyleids, boolean statu);
}
