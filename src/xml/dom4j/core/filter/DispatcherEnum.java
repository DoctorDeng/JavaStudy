package xml.dom4j.core.filter;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author denghuajie
 * 
 * @since 2018年1月23日 上午9:32:31
 * 
 */
public enum DispatcherEnum {
	REQUEST("REQUEST"), FORWARD("FORWARD");
	private String value;
	
	DispatcherEnum(String value){
		this.value = value;
	}
	@Override
	public String toString() {
		return this.value;
	}
}
