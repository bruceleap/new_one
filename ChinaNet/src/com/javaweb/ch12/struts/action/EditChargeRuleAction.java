/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.javaweb.ch12.struts.action;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.javaweb.ch12.service.IAdminOperator;


/** 
 * ����:����ҵ���շ�
 * 
 */
public class EditChargeRuleAction extends BaseAction {
	/*
	 * Generated fields
	 */

	/** rule property */
	private String rule;

	/** iterm property */
	private String iterm;

	/** 
	 * Returns the rule.
	 * @return String
	 */
	public String getRule() {
		return rule;
	}

	/** 
	 * Set the rule.
	 * @param rule The rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}

	/** 
	 * Returns the iterm.
	 * @return String
	 */
	public String getIterm() {
		return iterm;
	}

	/** 
	 * Set the iterm.
	 * @param iterm The iterm to set
	 */
	public void setIterm(String iterm) {
		this.iterm = iterm;
	}
	
	public String execute(){
		//�õ�ҳ�洫�������շ�ϸ��,�����շ���������ж���,����������
		String[] chargestr=this.request().getParameterValues("item");	
		//����Spring�����ļ�����ʼ��IOC����
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("/applicationContext.xml");
		//�������ӹ�bean
		IAdminOperator admin=(IAdminOperator)context.getBean("adminservice");
		System.out.println("hello!");
		//����ҵ���ķ�������ҵ���շ�
		String message=admin.editChargeRule(rule, chargestr);
		System.out.println(message);
		if(!message.equals("")){
			this.request().setAttribute("message", message);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}
}