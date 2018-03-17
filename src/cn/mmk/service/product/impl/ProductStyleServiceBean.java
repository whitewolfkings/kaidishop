package cn.mmk.service.product.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mmk.bean.product.ProductStyle;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.product.ProductStyleService;


@Service
@Transactional
public class ProductStyleServiceBean extends DaoSupport<ProductStyle> implements ProductStyleService {

	public void setVisibleStatu(Integer[] productstyleids, boolean statu) {
		if(productstyleids!=null && productstyleids.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0;i<productstyleids.length;i++){
				jpql.append('?').append((i+2)).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update ProductStyle o set o.visible=?1 where o.id in("+ jpql.toString()+ ")");
			query.setParameter(1, statu);
			for(int i=0;i<productstyleids.length;i++){
				query.setParameter(i+2, productstyleids[i]);
			}
			query.executeUpdate();
		}
	}
}
