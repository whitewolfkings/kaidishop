package cn.mmk.service.product;

import cn.mmk.bean.QueryResult;
import cn.mmk.bean.product.ProductInfo;

public interface ProductSearchService {
	/**
	 * ������Ʒ
	 * @param keyword �ؼ���
	 * @param firstResult ��ʼ����
	 * @param maxResult ÿҳ��ȡ�ļ�¼��
	 * @return
	 */
	public QueryResult<ProductInfo> query(String keyword, int firstResult, int maxResult);

}