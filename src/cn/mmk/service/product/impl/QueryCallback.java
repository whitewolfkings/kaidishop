package cn.mmk.service.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassSession;
import org.compass.core.CompassQuery.SortDirection;
import org.compass.core.CompassQuery.SortPropertyType;

import cn.mmk.bean.QueryResult;
import cn.mmk.bean.product.ProductInfo;

public class QueryCallback implements CompassCallback<QueryResult<ProductInfo>> {
	private String keyword;
	private int firstResult;
	private int maxResult;

	public QueryCallback(String keyword, int firstResult, int maxResult) {
		this.firstResult = firstResult;
		this.maxResult = maxResult;
		this.keyword = keyword;
	}

	public QueryResult<ProductInfo> doInCompass(CompassSession session) throws CompassException {
		/*查询指定类别的匹配记录，并按position降序排序
		 CompassQueryBuilder queryBuilder = session.queryBuilder();
		 CompassHits hits = queryBuilder.bool()
		 	.addMust(queryBuilder.spanEq("typeid", typeid))
	 		.addMust(queryBuilder.queryString(keyword).toQuery())
	   		.toQuery().addSort("position", SortPropertyType.FLOAT, SortDirection.REVERSE)
	   		.hits();//sql: typeid=1 and (xxxx like ?) order by positoin desc
		 */
		CompassHits hits = session.find(keyword);//5
		QueryResult<ProductInfo> qr = new QueryResult<ProductInfo>();
		qr.setTotalrecord(hits.length());//获取匹配记录的总数
		int length = firstResult + maxResult;
		if(length>hits.length()) length = hits.length();
		List<ProductInfo> products = new ArrayList<ProductInfo>();
		for(int i = firstResult ; i < length ; i++){
			ProductInfo product = (ProductInfo)hits.data(i);
			if(hits.highlighter(i).fragment("productName")!=null)
				product.setName(hits.highlighter(i).fragment("productName"));
			if(hits.highlighter(i).fragment("description")!=null)
				product.setDescription(hits.highlighter(i).fragment("description"));
			products.add(product);
		}
		qr.setResultlist(products);
		return qr;
	}

}
