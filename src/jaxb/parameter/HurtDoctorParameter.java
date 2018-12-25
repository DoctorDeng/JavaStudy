package jaxb.parameter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "datainfo") 
public class HurtDoctorParameter {
	/**
	 * 注册账号
	 */
	@XmlElement(name = "userName")
	private String userName;
	/**
	 * 登陆密码
	 */
	@XmlElement(name = "password")
	private String password;
	/**
	 * 被查询人姓名
	 */
	@XmlElement(name = "caseName")
	private String caseName;
	/**
	 * 被查询人身份证号
	 */
	@XmlElement(name = "idCard")
	private String idCard;
	/**
	 * 组织机构编码
	 */
	@XmlElement(name = "orgCode")
	private String orgCode;
	/**
	 * 服务类型, 固定为 {@value HurtDoctorConstants#SERVICE_TYPE}
	 */
	@XmlElement(name = "serviceType")
	private String serviceType;
	
	public HurtDoctorParameter() {
		super();
	}
	public HurtDoctorParameter(String userName, String password, String caseName, String idCard, String orgCode,
			String serviceType) {
		super();
		this.userName = userName;
		this.password = password;
		this.caseName = caseName;
		this.idCard = idCard;
		this.orgCode = orgCode;
		this.serviceType = serviceType;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getCaseName() {
		return caseName;
	}
	public String getIdCard() {
		return idCard;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	@Override
	public String toString() {
		return "HurtDoctorParameter [userName=" + userName + ", password=" + password + ", caseName=" + caseName
				+ ", idCard=" + idCard + ", orgCode=" + orgCode + ", serviceType=" + serviceType + "]";
	}
}
