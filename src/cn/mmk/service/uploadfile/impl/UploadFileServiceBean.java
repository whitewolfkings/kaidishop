package cn.mmk.service.uploadfile.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mmk.bean.uploadfile.UploadFile;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.uploadfile.UploadFileService;


@Service @Transactional
public class UploadFileServiceBean extends DaoSupport<UploadFile> implements UploadFileService {

	@SuppressWarnings("unchecked")
	public List<String> getFilepath(Integer[] ids){
		if(ids!=null && ids.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0; i<ids.length;i++){
				jpql.append('?').append((i+1)).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("select o.filepath from UploadFile o where o.id in("+ jpql.toString()+ ")");
			for(int i=0; i<ids.length;i++){
				query.setParameter(i+1, ids[i]);
			}
			return query.getResultList();
		}
		return null;
	}
}
