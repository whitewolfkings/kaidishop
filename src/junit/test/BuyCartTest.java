package junit.test;

import org.junit.Test;

import cn.mmk.bean.BuyCart;
import cn.mmk.bean.BuyItem;
import cn.mmk.bean.product.ProductInfo;
import cn.mmk.bean.product.ProductStyle;

public class BuyCartTest {

	@Test
	public void xxx(){
		BuyItem item1 = new BuyItem();
		ProductInfo product = new ProductInfo(10);
		product.addProductStyle(new ProductStyle(10));		
		item1.setProduct(product);
		
		BuyItem item2 = new BuyItem();
		ProductInfo product2 = new ProductInfo(10);
		product2.addProductStyle(new ProductStyle(90));		
		item2.setProduct(product2);
		
		System.out.println(item1.equals(item2));
	}
	
	@Test
	public void ppp(){
		BuyCart buyCart = new BuyCart();
		BuyItem item1 = new BuyItem();
		ProductInfo product = new ProductInfo(10);
		product.addProductStyle(new ProductStyle(20));		
		item1.setProduct(product);
		buyCart.addBuyItem(item1);
		
		BuyItem item2 = new BuyItem();
		ProductInfo product2 = new ProductInfo(10);
		product2.addProductStyle(new ProductStyle(10));		
		item2.setProduct(product2);
		buyCart.addBuyItem(item2);
		
		for(BuyItem bItem : buyCart.getItems()){
			System.out.println(bItem.getProduct().getId()+",amount="+ bItem.getAmount());
		}
		
	}
}
