package jaxb.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "data") 
public class MessageResult extends BaseResult {
	/**
	 * 返回消息
	 */
	@XmlElement(name = "result") 
	private String result;

	public MessageResult() {
		super();
	}
	public MessageResult(String result) {
		super();
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
