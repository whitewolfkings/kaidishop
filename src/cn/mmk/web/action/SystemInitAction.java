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
 * ��ʼ�� (��action����ϵͳ��װ����ִ��)
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
		request.setAttribute("message", "��ʼ�����");
		request.setAttribute("urladdress", "/employee/logon.do");
		return mapping.findForward("message");
	}
	/**
	 * ��ʼ������Ա�˺�
	 */
	private void initAdmin() {
		if(employeeService.getCount()==0){
			Employee employee = new Employee();
			employee.setUsername("admin");
			employee.setPassword("123456");
			employee.setRealname("ϵͳ����Ա");
			employee.setGender(Gender.MAN);
			employee.setIdCard(new IDCard("213213","����", new Date()));
			employee.getGroups().addAll(groupService.getScrollData().getResultlist());//����Ȩ��
			employeeService.save(employee);
		}		
	}
	/**
	 * ��ʼ��ϵͳȨ����
	 */
	private void initPrivilegeGroup() {
		if(groupService.getCount()==0){
			PrivilegeGroup group = new PrivilegeGroup();
			group.setName("ϵͳȨ����");
			group.getPrivileges().addAll(privilegeService.getScrollData().getResultlist());
			groupService.save(group);
		}		
	}
	/**
	 * ��ʼ��Ȩ��
	 */
	private void initSystemPrivilege() {
		if(privilegeService.getCount()==0){
			List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
			privileges.add(new SystemPrivilege("department", "view", "���Ų鿴"));
			privileges.add(new SystemPrivilege("department", "insert", "�������"));
			privileges.add(new SystemPrivilege("department", "update", "�����޸�"));
			privileges.add(new SystemPrivilege("department", "delete", "����ɾ��"));
			
			privileges.add(new SystemPrivilege("order","turnReceived","ת���ջ�����"));
			privileges.add(new SystemPrivilege("order","turnDelivered","ת�ѷ�������"));
			privileges.add(new SystemPrivilege("order","turnWaitdeliver","ת�ȴ���������"));
			privileges.add(new SystemPrivilege("order","cancelOrder","ȡ������"));
			privileges.add(new SystemPrivilege("order","confirmOrder","���ͨ������"));
			privileges.add(new SystemPrivilege("order","confirmPayment","����ȷ�϶����Ѹ���"));
			privileges.add(new SystemPrivilege("order","allUnLock","������������"));
			privileges.add(new SystemPrivilege("order","view","�����鿴"));
			privileges.add(new SystemPrivilege("order","modifyContactInfo","������ϵ��Ϣ�޸�"));
			privileges.add(new SystemPrivilege("order","modifyDeliverInfo","����������Ϣ�޸�"));
			privileges.add(new SystemPrivilege("order","modifyPaymentWay","֧����ʽ�޸�"));
			privileges.add(new SystemPrivilege("order","modifyDeliverWay","���ͷ�ʽ�޸�"));
			privileges.add(new SystemPrivilege("order","modifyProductAmount","��Ʒ���������޸�"));
			privileges.add(new SystemPrivilege("order","modifyDeliverFee","���ͷ��޸�"));
			privileges.add(new SystemPrivilege("order","deleteOrderItem","ɾ��������"));
			
			privileges.add(new SystemPrivilege("employee","leave","Ա����ְ����"));
			privileges.add(new SystemPrivilege("employee","insert","Ա�����"));
			privileges.add(new SystemPrivilege("employee","update","Ա����Ϣ�޸�"));
			privileges.add(new SystemPrivilege("employee","view","Ա���鿴"));
			privileges.add(new SystemPrivilege("employee","privilegeGroupSet","Ա��Ȩ������"));
			
			privileges.add(new SystemPrivilege("brand","insert","Ʒ�����"));
			privileges.add(new SystemPrivilege("brand","update","Ʒ����Ϣ�޸�"));
			privileges.add(new SystemPrivilege("brand","view","Ʒ�Ʋ鿴"));
			
			privileges.add(new SystemPrivilege("product","insert","��Ʒ���"));
			privileges.add(new SystemPrivilege("product","update","��Ʒ��Ϣ�޸�"));
			privileges.add(new SystemPrivilege("product","view","��Ʒ�鿴"));
			privileges.add(new SystemPrivilege("product","visible","��Ʒ��/�¼�"));
			privileges.add(new SystemPrivilege("product","commend","��Ʒ�Ƽ�"));
			
			privileges.add(new SystemPrivilege("productType","insert","��Ʒ������"));
			privileges.add(new SystemPrivilege("productType","update","��Ʒ����޸�"));
			privileges.add(new SystemPrivilege("productType","view","��Ʒ���鿴"));
			
			privileges.add(new SystemPrivilege("buyer","enable","��վ�û�����"));
			privileges.add(new SystemPrivilege("buyer","delete","��վ�û�����"));
			privileges.add(new SystemPrivilege("buyer","view","��վ�û��鿴"));
			
			privilegeService.batchSave(privileges);
		}		
	}

}
