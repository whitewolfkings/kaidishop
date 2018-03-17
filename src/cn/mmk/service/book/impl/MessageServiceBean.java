package cn.mmk.service.book.impl;

import org.springframework.stereotype.Service;

import cn.mmk.bean.book.Message;
import cn.mmk.service.base.DaoSupport;
import cn.mmk.service.book.MessageService;

@Service
public class MessageServiceBean extends DaoSupport<Message> implements MessageService {

}
