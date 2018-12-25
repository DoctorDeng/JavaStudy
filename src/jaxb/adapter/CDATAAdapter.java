package jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter 类, 修饰类字段, 达到自动添加 CDATA 标记的目标
 * @author denghuajie
 * @since 2018年12月22日
 */
public class CDATAAdapter extends XmlAdapter<String, String> {
	@Override
	public String unmarshal(String value) throws Exception {
		return value;
	}

	@Override
	public String marshal(String value) throws Exception {
		return new StringBuilder("<![CDATA[").append(value).append("]]>").toString();
	}
}
