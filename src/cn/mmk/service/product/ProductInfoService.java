package cn.mmk.service.product;

import java.util.List;

import cn.mmk.bean.product.Brand;
import cn.mmk.bean.product.ProductInfo;
import cn.mmk.service.base.DAO;


public interface ProductInfoService extends DAO<ProductInfo> {
	/**
	 * 设置产品是否上架
	 * @param productids 产品id数组
	 * @param statu true为上架,false为下架
	 */
	public void setVisibleStatu(Integer[] productids, boolean statu);
	
	/**
	 * 设置产品的推荐状态
	 * @param productids 产品id数组
	 * @param statu true为推荐,否则不推荐
	 */
	public void setCommendStatu(Integer[] productids, boolean statu);
	/**
	 * 获取类别下产品所使用到的品牌
	 * @param typeids 产品类别id
	 * @return
	 */
	public List<Brand> getBrandsByProductTypeid(Integer[] typeids);
	
	/**
	 * 获取销量最多并且被推荐的产品
	 * @param typeid 类别id
	 * @param maxResult 获取的产品数量
	 * @return
	 */
	public List<ProductInfo> getTopSell(Integer typeid, int maxResult);
	/**
	 * 获取指定ID的产品
	 * @param productids 产品id数组
	 * @param maxResult 最大获取多少条记录
	 * @return
	 */
	public List<ProductInfo> getViewHistory(Integer[] productids, int maxResult);
}
