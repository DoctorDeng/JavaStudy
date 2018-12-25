package jaxb.result;

import java.util.List;

import javax.xml.bind.JAXBException;

import jaxb.util.JaxbUtil;

/**
 * 构建 WebService 服务通用返回结果 Builder
 * @author denghuejie
 * @since 2018年12月24日
 */
public class WebServiceResultBuilder {
	/**
	 * xml 编码
	 */
	private String encoding = "UTF-8";
	
	/**
	 * 是否格式化输出
	 */
	private boolean formatOutput = false;

	
	public WebServiceResultBuilder() {
		super();
	}
	
	public String getEncoding() {
		return encoding;
	}
	public boolean isFormatOutput() {
		return formatOutput;
	}
	public WebServiceResultBuilder setEncoding(String encoding) {
		this.encoding = encoding;
		return this;
	}
	public WebServiceResultBuilder setFormatOutput(boolean formatOutput) {
		this.formatOutput = formatOutput;
		return this;
	}

	public <T> String build(String resultCode, List<T> data, Class<T> dataClass) {
		DataResult<T> result = new DataResult<T>(data);
		result.setResultCode(resultCode);
		
		try {
			return JaxbUtil.beanToXmlString(result, formatOutput, encoding, DataResult.class, dataClass);
		} catch (JAXBException e) {
			throw new RuntimeException("Java Bean 转换 Xml 失败", e);
		}
	}
}
