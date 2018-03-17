package cn.mmk.service.book.impl;

import org.springframework.stereotype.Service;

import cn.mmk.bean.book.GeneratedOrderid;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.book.GeneratedOrderidService;

@Service
public class GeneratedOrderidServiceBean extends DaoSupport<GeneratedOrderid> implements GeneratedOrderidService {
	
	//entityManager = context.getEntityManager(){
	//        		       if(entityManager == null) return createEntityManager() : entityManager;
    //                }
	//entityMnager.getTransaction().begin();//打开entiyManager执行事务
	public void init(){
		if(this.getCount()==0){
			GeneratedOrderid go = new GeneratedOrderid();
			go.setId("order");
			this.save(go);
		}
	}
	//entityManager = context.getEntityManager();
	//entityManager.commit()/rollback();
	//entityManager.close();
	
	public int buildOrderid(){
		em.createQuery("update GeneratedOrderid o set o.orderid=orderid+1 where o.id=?1")
			.setParameter(1, "order").executeUpdate();
		em.flush();
		GeneratedOrderid go = this.find("order");
		return go.getOrderid();
	}
}
