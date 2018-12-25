package jaxb.result;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "data") 
public class  DataResult <T> extends BaseResult {
	
//	@XmlElement(name = "recode")
	@XmlElementWrapper(name = "result") 
/*    @XmlElementRefs({
        @XmlElementRef(name = "recode", type = HurtDoctorVO.class),
    })*/
	@XmlAnyElement(lax=true)
	private List<T> result;
	
	public DataResult() {
		super();
	}

	public DataResult(List<T> result) {
		super();
		this.result = result;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
}
