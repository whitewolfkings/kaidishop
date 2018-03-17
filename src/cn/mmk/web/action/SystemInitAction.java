package cn.mmk.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.mmk.bean.privilege.Employee;
import cn.mmk.bean.privilege.IDCard;
import cn.mmk.bean.privilege.PrivilegeGroup;
import cn.mmk.bean.privilege.SystemPrivilege;
import cn.mmk.bean.user.Gender;
import cn.mmk.service.book.GeneratedOrderidService;
import cn.mmk.service.privilege.EmployeeService;
import cn.mmk.service.privilege.PrivilegeGroupService;
import cn.mmk.service.privilege.SystemPrivilegeService;
/**
 * 初始化 (此action是在系统安装完后就执行)
 */
@Controller("/system/init")
public class SystemInitAction extends Action {
	@Resource GeneratedOrderidService generatedOrderidService;
	@Resource SystemPrivilegeService privilegeService;
	@Resource PrivilegeGroupService groupService;
	@Resource EmployeeService employeeService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		generatedOrderidService.init();
		initSystemPrivilege();
		initPrivilegeGroup();
		initAdmin();
		request.setAttribute("message", "初始化完成");
		request.setAttribute("urladdress", "/employee/logon.do");
		return mapping.findForward("message");
	}
	/**
	 * 初始化管理员账号
	 */
	private void initAdmin() {
		if(employeeService.getCount()==0){
			Employee employee = new Employee();
			employee.setUsername("admin");
			employee.setPassword("123456");
			employee.setRealname("系统管理员");
			employee.setGender(Gender.MAN);
			employee.setIdCard(new IDCard("213213","北京", new Date()));
			employee.getGroups().addAll(groupService.getScrollData().getResultlist());//赋予权限
			employeeService.save(employee);
		}		
	}
	/**
	 * 初始化系统权限组
	 */
	private void initPrivilegeGroup() {
		if(groupService.getCount()==0){
			PrivilegeGroup group = new PrivilegeGroup();
			group.setName("系统权限组");
			group.getPrivileges().addAll(privilegeService.getScrollData().getResultlist());
			groupService.save(group);
		}		
	}
	/**
	 * 初始化权限
	 */
	private void initSystemPrivilege() {
		if(privilegeService.getCount()==0){
			List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
			privileges.add(new SystemPrivilege("department", "view", "部门查看"));
			privileges.add(new SystemPrivilege("department", "insert", "部门添加"));
			privileges.add(new SystemPrivilege("department", "update", "部门修改"));
			privileges.add(new SystemPrivilege("department", "delete", "部门删除"));
			
			privileges.add(new SystemPrivilege("order","turnReceived","转已收货订单"));
			privileges.add(new SystemPrivilege("order","turnDelivered","转已发货订单"));
			privileges.add(new SystemPrivilege("order","turnWaitdeliver","转等待发货订单"));
			privileges.add(new SystemPrivilege("order","cancelOrder","取消订单"));
			privileges.add(new SystemPrivilege("order","confirmOrder","审核通过订单"));
			privileges.add(new SystemPrivilege("order","confirmPayment","财务确认订单已付款"));
			privileges.add(new SystemPrivilege("order","allUnLock","批量解锁订单"));
			privileges.add(new SystemPrivilege("order","view","订单查看"));
			privileges.add(new SystemPrivilege("order","modifyContactInfo","订单联系信息修改"));
			privileges.add(new SystemPrivilege("order","modifyDeliverInfo","订单配送信息修改"));
			privileges.add(new SystemPrivilege("order","modifyPaymentWay","支付方式修改"));
			privileges.add(new SystemPrivilege("order","modifyDeliverWay","配送方式修改"));
			privileges.add(new SystemPrivilege("order","modifyProductAmount","商品购买数量修改"));
			privileges.add(new SystemPrivilege("order","modifyDeliverFee","配送费修改"));
			privileges.add(new SystemPrivilege("order","deleteOrderItem","删除订单项"));
			
			privileges.add(new SystemPrivilege("employee","leave","员工离职设置"));
			privileges.add(new SystemPrivilege("employee","insert","员工添加"));
			privileges.add(new SystemPrivilege("employee","update","员工信息修改"));
			privileges.add(new SystemPrivilege("employee","view","员工查看"));
			privileges.add(new SystemPrivilege("employee","privilegeGroupSet","员工权限设置"));
			
			privileges.add(new SystemPrivilege("brand","insert","品牌添加"));
			privileges.add(new SystemPrivilege("brand","update","品牌信息修改"));
			privileges.add(new SystemPrivilege("brand","view","品牌查看"));
			
			privileges.add(new SystemPrivilege("product","insert","产品添加"));
			privileges.add(new SystemPrivilege("product","update","产品信息修改"));
			privileges.add(new SystemPrivilege("product","view","产品查看"));
			privileges.add(new SystemPrivilege("product","visible","产品上/下架"));
			privileges.add(new SystemPrivilege("product","commend","产品推荐"));
			
			privileges.add(new SystemPrivilege("productType","insert","产品类别添加"));
			privileges.add(new SystemPrivilege("productType","update","产品类别修改"));
			privileges.add(new SystemPrivilege("productType","view","产品类别查看"));
			
			privileges.add(new SystemPrivilege("buyer","enable","网站用户启用"));
			privileges.add(new SystemPrivilege("buyer","delete","网站用户禁用"));
			privileges.add(new SystemPrivilege("buyer","view","网站用户查看"));
			
			privilegeService.batchSave(privileges);
		}		
	}

}
