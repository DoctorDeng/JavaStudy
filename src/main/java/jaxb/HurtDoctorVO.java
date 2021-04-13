package jaxb;

import jaxb.adapter.CDATAAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "recode") 
public class HurtDoctorVO {
	@XmlElement(name = "userName")
	private String userName;
	
	@XmlElement(name = "idcard")
	private String idcard;
	
	@XmlElement(name = "sex")
	private String sex;
	
	@XmlElement(name = "behavior")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	private String behavior;
	
	@XmlElement(name = "execution")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	private String execution;
	
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	@XmlElement(name = "punish")
	private String punish;
	
	public HurtDoctorVO() {
		super();
	}
	
	public HurtDoctorVO(String userName, String idcard, String sex, String behavior, String execution, String punish) {
		super();
		this.userName = userName;
		this.idcard = idcard;
		this.sex = sex;
		this.behavior = behavior;
		this.execution = execution;
		this.punish = punish;
	}
	public String getUserName() {
		return userName;
	}
	public String getIdcard() {
		return idcard;
	}
	public String getSex() {
		return sex;
	}
	public String getBehavior() {
		return behavior;
	}
	public String getExecution() {
		return execution;
	}
	public String getPunish() {
		return punish;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	public void setExecution(String execution) {
		this.execution = execution;
	}
	public void setPunish(String punish) {
		this.punish = punish;
	}

	@Override
	public String toString() {
		return "HurtDoctorVO [userName=" + userName + ", idcard=" + idcard + ", sex=" + sex + ", behavior=" + behavior
				+ ", execution=" + execution + ", punish=" + punish + "]";
	}
}
