package cn.mmk.service.product.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mmk.bean.product.Brand;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.product.BrandService;


@Service
@Transactional
public class BrandServiceBean extends DaoSupport<Brand> implements BrandService {

	@Override
	public void save(Brand entity) {
		entity.setCode(UUID.randomUUID().toString());
		super.save(entity);
	}
	
}
