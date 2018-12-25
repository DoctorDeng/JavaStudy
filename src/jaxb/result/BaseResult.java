package jaxb.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseResult {
	@XmlElement(required = true, name = "resultCode") 
	private String resultCode;
	
	public BaseResult() {
		super();
	}
	public BaseResult(String resultCode) {
		super();
		this.resultCode = resultCode;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
