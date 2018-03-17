package cn.mmk.service.product;

import cn.mmk.bean.product.ProductStyle;
import cn.mmk.service.base.DAO;


public interface ProductStyleService extends DAO<ProductStyle> {
	/**
	 * 设置该样式的产品是否上架
	 * @param productstyleids 产品id数组
	 * @param statu true为上架,false为下架
	 */
	public void setVisibleStatu(Integer[] productstyleids, boolean statu);
}
