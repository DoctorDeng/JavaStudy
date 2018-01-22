package xml.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import xml.dom4j.core.WebXmlCommonConst;
import xml.dom4j.core.filter.XmlFilterConfig;
import xml.dom4j.core.listener.XmlListenerConfig;
import xml.dom4j.core.servlet.XmlServletConfig;
import xml.dom4j.core.servlet.XmlServletMappingConfig;

public class WebXmlModifier {
	public static void main(String[] args) throws IOException {
		File testFile = new File("E:\\environment\\yuntou\\bs\\SmartPage\\WEB-INF\\web.xml");
		SAXReader reader  = new SAXReader();
		Document document = null;
		try {
			document = reader.read(testFile);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (document == null ) {
			return;
		}
		Element rootElement = document.getRootElement();
		@SuppressWarnings("unchecked")
		Iterator<Element> it  = rootElement.elementIterator("filter");
		for (; it.hasNext();) {
			Element filterElement = it.next();
			Element filterClass = filterElement.element("filter-class");
			if ("com.nstc.uas.client.UASFilter".equals(filterClass.getStringValue())) {
				System.out.println("包含 UASFilter");
				filterClass.setText("aaa");
				//return;
			}
			if("com.nstc.web.filter.RuntimeFilter".equals(filterClass.getStringValue())) {
				filterClass.setText("aaa");
			}
			//printElement(it.next());
			/*if (hasAttribute(it.next(), ""));*/
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter output = new XMLWriter(new FileWriter(new File("test11.xml")), format);
		// 设置是否将特殊字符 转义, 默认为 True(转义)
		output.setEscapeText(false);
		output.write(document);
		output.close();
	}
	public static void addUASFilterElement(Element rootElement) {
		if (rootElement == null || !rootElement.isRootElement()) {
			return;
		}
		//addFilter(rootElement, "RuntimeFilter","com.nstc.uas.client.UASFilter");
		/*Element filter = rootElement.addElement("filter");
		filter.addElement("filter-name").setText("RuntimeFilter");
		filter.addElement("filter-class").setText("com.nstc.uas.client.UASFilter");*/
		Element filterMappingDo = rootElement.addElement("filter-mapping");
	}
	
	public Element addFilter(Element rootElement,String filterName, String filterClass) {
		Element filterElement = rootElement.addElement(XmlFilterConfig.CONST_FILTER);
		filterElement.addElement(XmlFilterConfig.CONST_FILTER_NAME).setText(filterName);
		filterElement.addElement(XmlFilterConfig.CONST_FILTER_CLASS).setText(filterClass);
		return filterElement;
	}
	public Element addListener(Element rootElement,String listenerClass) {
		Element listenerElement = rootElement.addElement(XmlListenerConfig.CONST_LISTENER);
		listenerElement.addElement(XmlListenerConfig.CONST_LISTENER_CLASS).setText(listenerClass);
		return listenerElement;
	}
	public Element addServlet(Element rootElement, String servletName, String servletClass, String startup) {
		Element servletElement = rootElement.addElement(XmlServletConfig.CONST_SERVLET);
		servletElement.addElement(XmlServletConfig.CONST_SERVLET_NAME).setText(servletName);
		servletElement.addElement(XmlServletConfig.CONST_SERVLET_CLASS).setText(servletClass);
		servletElement.addElement(XmlServletConfig.CONST_STARTUP).setText(startup);
		return servletElement;
	}
	public Element addServletMapping(Element rootElement, String servletName, String urlPattern) {
		Element servletMappingElement = rootElement.addElement(XmlServletConfig.CONST_SERVLET_MAPPING);
		servletMappingElement.addElement(XmlServletConfig.CONST_SERVLET_NAME).setText(servletName);
		servletMappingElement.addElement(WebXmlCommonConst.CONST_URL_PATTERN).setText(urlPattern);
		return servletMappingElement;
	}
	public Element addFilterMapping(Element rootElement, String filterName, String urlPattern, String[] dispatchers) {
		Element filterMappingElement = rootElement.addElement("filter-mapping");
		filterMappingElement.addElement("filter-name").setText(filterName);
		filterMappingElement.addElement("url-pattern").setText(urlPattern);
		if (dispatchers == null || dispatchers.length == 0) {
			filterMappingElement.addElement("dispatcher").setText("REQUEST");
			filterMappingElement.addElement("dispatcher").setText("FORWARD");
		}else {
			for (int i = 0, len = dispatchers.length; i < len; i++) {
				filterMappingElement.addElement("dispatcher").setText(dispatchers[i]);
			}
		}
		return filterMappingElement;
	}
	public static Element checkUASFilter(Element rootElement, String servletName, String servletClass, String startup) {
		Element servletElement = rootElement.addElement("servlet");
		servletElement.addElement("servlet-name").setText(servletName);
		servletElement.addElement("servlet-class").setText(servletClass);
		servletElement.addElement("load-on-startup").setText(startup);
		return servletElement;
	}
	@SuppressWarnings("unchecked")
	public static boolean hasAttribute(Element targetElment, String attributeName, String attributeValue) {
		if (targetElment == null 
				|| attributeName == null 
				|| attributeName.length() == 0) {
			return false;
		}
		List<Attribute> attrs = targetElment.attributes();
		if (attrs.size() == 0) {
			return false;
		}
		for (int i = 0, len = attrs.size(); i < len; i++) {
			Attribute attr = attrs.get(i);
			if (attr.getName().equals(attributeName)) {
				return true;
			}
		}
		
		return false;
	}
	public static void printElement(Element element) {
		System.out.print("节点名：" + element.getName());
		
		List<Attribute> attrs = element.attributes();
		
		if(attrs.size() > 0) {
			for (Attribute attr : attrs) {
				System.out.print(" 属性名: " + attr.getName() + " 值: " + attr.getValue());
			}
		}
		
		if (element.elements().size() == 0) {
			System.out.print(" 节点值: " + element.getStringValue());
		}
		System.out.println();
		
		for (Iterator<Element> it = element.elementIterator(); it.hasNext();) {
			printElement(it.next());
		}
	}
}
