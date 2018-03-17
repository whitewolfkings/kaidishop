package cn.mmk.service.product;

import cn.mmk.bean.QueryResult;
import cn.mmk.bean.product.ProductInfo;

public interface ProductSearchService {
	/**
	 * 搜索商品
	 * @param keyword 关键字
	 * @param firstResult 开始索引
	 * @param maxResult 每页获取的记录数
	 * @return
	 */
	public QueryResult<ProductInfo> query(String keyword, int firstResult, int maxResult);

}