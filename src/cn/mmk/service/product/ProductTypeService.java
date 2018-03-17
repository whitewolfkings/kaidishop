package cn.mmk.service.product;

import java.util.List;

import cn.mmk.bean.product.ProductType;
import cn.mmk.service.base.DAO;


public interface ProductTypeService extends DAO<ProductType> {
	/**
	 * ��ȡ������id
	 * @param parentids ����id����
	 * @return
	 */
	public List<Integer> getSubTypeid(Integer[] parentids);
}