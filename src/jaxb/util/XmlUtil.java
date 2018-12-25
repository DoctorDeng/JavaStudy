package jaxb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
/**
 * xml 相关工具类
 * @author denghuajie
 * @since 2018年12月24日
 */
public class XmlUtil {
	
	/**
	 * 将形如  
	 *  &lt;data&gt; &lt;userName&gt;1&lt;/userName&gt; &lt;/data&gt; 这样的 xml 形式字符串转换为 Map&lt;String, String&gt; 形式
	 *  <p style=color:red>注意：xml 形式字符串参数有且仅有两个层级</p>
	 * @param xmlString xml 形式的字符串参数
	 * @return Map&lt;String, String&gt; key 为节点名称, value 为节点包含  text
	 * @throws DocumentException
	 */
	public static final Map<String, String> xmlString2Map(String xmlString) throws DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = DocumentHelper.parseText(xmlString);
		Element root = doc.getRootElement();

		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		for (Element element : elements) {
			String key   = element.getName();
			String value = element.getText();
			map.put(key, value);
		}
		return map;
	}
	/**
	 * 将形如  
	 *  &lt;data&gt; &lt;userName&gt;1&lt;/userName&gt; &lt;/data&gt; 这样的 xml 形式字符串某一节点下的所有子节点转换为 Map&lt;String, String&gt; 形式
	 * @param xmlString 形式的字符串参数
	 * @param elementName 需要将其子节点信息进行转换的节点名称 , 不能为空
	 * @return Map&lt;String, String&gt; key 为节点名称, value 为节点包含  text
	 * @throws DocumentException
	 */
	public static final Map<String, String> xmlString2Map(String xmlString, String elementName) throws DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = DocumentHelper.parseText(xmlString);
		Element root = doc.getRootElement();

		
		@SuppressWarnings("unchecked")
		List<Element> elements = root.element(elementName).elements();
		for (Element element : elements) {
			String key   = element.getName();
			String value = element.getText();
			map.put(key, value);
		}
		return map;
	}
	
	public static final String map2XmlString(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		Set<String> keySet = map.keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			String value = MapUtils.getString(map, key);
			if (value == null) {
				value = "";
			}
			
			sb.append("<").append(key).append(">");
			sb.append(value);
			sb.append("</").append(key).append(">");
		}
		return sb.toString();
	}
	
	
	
	public static void main(String[] args) throws DocumentException {
		//System.out.println(xmlString2Map(" <data><userName><![CDATA[1]]></userName></data>"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "你好");
		map.put("value", 33.33);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map.put("name", "对对对<>");
		map.put("value", "sdfsdf");
		/*System.out.println(map2XmlString(map));*/
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		//dataList.add(map);
		
		//System.out.println(buildResult("2", dataList));
		
		//String data = "<data><datainfo><name>1</name></datainfo></data>";
		
		//System.out.println(xmlString2Map(data, "datainfo"));
		//System.out.println(buildResult("2",""));
	}
}
