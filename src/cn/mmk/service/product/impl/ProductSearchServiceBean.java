package cn.mmk.service.product.impl;

import javax.annotation.Resource;

import org.compass.core.Compass;
import org.compass.core.CompassTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mmk.bean.QueryResult;
import cn.mmk.bean.product.ProductInfo;
import cn.mmk.service.product.ProductSearchService;

@Service @Transactional
public class ProductSearchServiceBean implements ProductSearchService {
	private CompassTemplate compassTemplate;
	
	@Resource
	public void setCompass(Compass compass){
		this.compassTemplate = new CompassTemplate(compass);
	}
	
	public QueryResult<ProductInfo> query(String keyword, int firstResult, int maxResult){
		return compassTemplate.execute(new QueryCallback(keyword, firstResult, maxResult));
	}
}
