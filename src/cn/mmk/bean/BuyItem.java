package cn.mmk.bean;

import cn.mmk.bean.product.ProductInfo;

/**
 * 购物项
 */
public class BuyItem {
	/* 所购商品 */
	private ProductInfo product;
	/* 购买数量 */
	private int amount = 1;
	
	public BuyItem(){}
	
	public BuyItem(ProductInfo product) {
		this.product = product;
	}
	
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public int hashCode() {
		String result = product.getId() +"_";
		result += product.getStyles().iterator().next().getId();
		return result.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BuyItem other = (BuyItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		
		if(product.getStyles().size()!=other.product.getStyles().size()) return false;
			
		Integer styleid = product.getStyles().iterator().next().getId();
		Integer otherStyleid = other.product.getStyles().iterator().next().getId();
		if(!styleid.equals(otherStyleid)) return false;
		return true;
	}
	
}
