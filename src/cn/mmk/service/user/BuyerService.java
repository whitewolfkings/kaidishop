package cn.mmk.service.user;

import java.io.Serializable;

import cn.mmk.bean.user.Buyer;
import cn.mmk.service.base.DAO;


public interface BuyerService extends DAO<Buyer>{
	/**
	 * 启用指定用户
	 * @param usernames
	 */
	public void enable(Serializable ... usernames);
	/**
	 * 判断用户是否存在
	 * @param username
	 * @return
	 */
	public boolean exsit(String username);
	/**
	 * 判断用户名及密码是否正确
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUser(String username, String password);
	
	/**
	 * 更新密码
	 * @param username 用户名
	 * @param newpassword 新密码
	 */
	public void updatePassword(String username, String newpassword);
}