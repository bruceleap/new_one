/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.javaweb.ch12.struts.action;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.javaweb.ch12.model.Customer;
import com.javaweb.ch12.service.IOperator;
/** 
 * 功能:增加客户
 */
public class AddCustomerAction extends BaseAction {
	
	/*
	 * Generated Methods
	 *//*
	 * Generated fields
	 */

	/** customerBirtyday property */
	private String customerBirtyday;

	/** customerAddress property */
	private String customerAddress;

	/** customerSex property */
	private String customerSex;

	/** customerName property */
	private String customerName;

	/** idNumber property */
	private String idNumber;

	/** idType property */
	private String idType;

	/** 
	 * Returns the customerBirtyday.
	 * @return String
	 */
	public String getCustomerBirtyday() {
		return customerBirtyday;
	}

	/** 
	 * Set the customerBirtyday.
	 * @param customerBirtyday The customerBirtyday to set
	 */
	public void setCustomerBirtyday(String customerBirtyday) {
		this.customerBirtyday = customerBirtyday;
	}

	/** 
	 * Returns the customerAddress.
	 * @return String
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/** 
	 * Set the customerAddress.
	 * @param customerAddress The customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/** 
	 * Returns the customerSex.
	 * @return String
	 */
	public String getCustomerSex() {
		return customerSex;
	}

	/** 
	 * Set the customerSex.
	 * @param customerSex The customerSex to set
	 */
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	/** 
	 * Returns the customerName.
	 * @return String
	 */
	public String getCustomerName() {
		return customerName;
	}

	/** 
	 * Set the customerName.
	 * @param customerName The customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/** 
	 * Returns the idNumber.
	 * @return String
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/** 
	 * Set the idNumber.
	 * @param idNumber The idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/** 
	 * Returns the idType.
	 * @return String
	 */
	public String getIdType() {
		return idType;
	}

	/** 
	 * Set the idType.
	 * @param idType The idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	
	public String execute(){
		//通过收集从页面传过来的form表单构造客户对象
		Customer customer=new Customer();
		customer.setCustomerAddress(customerAddress);
		customer.setCustomerBirtyday(customerBirtyday);
		customer.setCustomerName(customerName);
		customer.setCustomerSex(customerSex);
		customer.setIdNumber(idNumber);
		customer.setIdType(idType);
		/*这个地方是用spring的关键所在
		 * 通过ClassPathXmlApplicationContext类得到spring配置文件
		 * 用getBean(类对象名)方法即可得到"具体干活的类"
		 * 直接用接口类就可以调用相关方法.
		 */
		//加载Spring配置文件，初始化IOC容器
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("/applicationContext.xml");
		//从容器接管bean
		IOperator dooperator=(IOperator)context.getBean("operatorservice");
//		调用业务层方法判断客户是否存在
		Customer mycustomer=dooperator.isCustomerExist(customer);
		if(mycustomer==null){
			//如果客户不存在,调用业务层方法增加客户.
			mycustomer = dooperator.addCustomer(customer);
			//直接调用实现了Customer类的子类的方法处理业务(增加客户)
			if(mycustomer!=null){
				//将客户信息写入session
				this.session().setAttribute("customer", mycustomer);
				return SUCCESS;
			}else{
				this.request().setAttribute("message", "操作失败!请重试!");
				return "false";
			}
		}else{
			//将客户信息写入session
			this.session().setAttribute("customer", mycustomer);
			return SUCCESS;
		}
	}
}