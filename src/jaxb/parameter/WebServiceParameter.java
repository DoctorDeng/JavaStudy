package jaxb.parameter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name = "data")
public class WebServiceParameter<T> {
	
	@XmlAnyElement(lax=true)
	private T param;

	public WebServiceParameter() {
		super();
	}

	public WebServiceParameter(T param) {
		super();
		this.param = param;
	}

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}
}
