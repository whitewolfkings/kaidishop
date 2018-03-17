package cn.mmk.service.user.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mmk.bean.user.ContactInfo;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.user.ContactInfoService;


@Service @Transactional
public class ContactInfoServiceBean extends DaoSupport<ContactInfo> implements ContactInfoService{

}
