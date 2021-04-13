package xml.dom4j;

import org.apache.commons.io.FileUtils;
import org.dom4j.Element;
import xml.dom4j.core.WebXmlCommonConst;
import xml.dom4j.core.filter.DispatcherEnum;
import xml.dom4j.core.filter.XmlFilterConfig;
import xml.dom4j.core.listener.XmlListenerConfig;
import xml.dom4j.core.resourceRef.XmlResourceRefConfig;
import xml.dom4j.core.servlet.XmlServletConfig;

import java.io.File;
import java.io.IOException;

public abstract class WebXmlModifier {
	private static final String FILE_BACKUP_SUFFIX = ".backup";
	public abstract void xmlModifiy(File xmlFile) throws IOException;
	
	protected Element addFilter(Element rootElement,String filterName, String filterClass) {
		Element filterElement = rootElement.addElement(XmlFilterConfig.CONST_FILTER);
		filterElement.addElement(XmlFilterConfig.CONST_FILTER_NAME).setText(filterName);
		filterElement.addElement(XmlFilterConfig.CONST_FILTER_CLASS).setText(filterClass);
		return filterElement;
	}
	protected Element addListener(Element rootElement,String listenerClass) {
		Element listenerElement = rootElement.addElement(XmlListenerConfig.CONST_LISTENER);
		listenerElement.addElement(XmlListenerConfig.CONST_LISTENER_CLASS).setText(listenerClass);
		return listenerElement;
	}
	protected Element addServlet(Element rootElement, String servletName, String servletClass, String startup) {
		Element servletElement = rootElement.addElement(XmlServletConfig.CONST_SERVLET);
		servletElement.addElement(XmlServletConfig.CONST_SERVLET_NAME).setText(servletName);
		servletElement.addElement(XmlServletConfig.CONST_SERVLET_CLASS).setText(servletClass);
		servletElement.addElement(XmlServletConfig.CONST_STARTUP).setText(startup);
		return servletElement;
	}
	protected Element addServletMapping(Element rootElement, String servletName, String urlPattern) {
		Element servletMappingElement = rootElement.addElement(XmlServletConfig.CONST_SERVLET_MAPPING);
		servletMappingElement.addElement(XmlServletConfig.CONST_SERVLET_NAME).setText(servletName);
		servletMappingElement.addElement(WebXmlCommonConst.CONST_URL_PATTERN).setText(urlPattern);
		return servletMappingElement;
	}
	protected Element addFilterMapping(Element rootElement, String filterName, String urlPattern, DispatcherEnum[] dispatchers) {
		Element filterMappingElement = rootElement.addElement(XmlFilterConfig.CONST_FILTER_MAPPING);
		filterMappingElement.addElement(XmlFilterConfig.CONST_FILTER_NAME).setText(filterName);
		filterMappingElement.addElement(WebXmlCommonConst.CONST_URL_PATTERN).setText(urlPattern);
		if (dispatchers == null || dispatchers.length == 0) {
			filterMappingElement.addElement(XmlFilterConfig.CONST_DISPATCHER).setText(DispatcherEnum.REQUEST.toString());
			filterMappingElement.addElement(XmlFilterConfig.CONST_DISPATCHER).setText(DispatcherEnum.FORWARD.toString());
		}else {
			for (int i = 0, len = dispatchers.length; i < len; i++) {
				filterMappingElement.addElement(XmlFilterConfig.CONST_DISPATCHER).setText(dispatchers[i].toString());
			}
		}
		return filterMappingElement;
	}
	protected Element addResourceRef(Element rootElement, String resRefName, String resType, String resAuth) {
		Element resourceRefElement = rootElement.addElement(XmlResourceRefConfig.CONST_RESOURCE_REF);
		resourceRefElement.addElement(XmlResourceRefConfig.CONST_RES_REF_NAME).setText(resRefName);
		resourceRefElement.addElement(XmlResourceRefConfig.CONST_RES_TYPE).setText(resType);
		resourceRefElement.addElement(XmlResourceRefConfig.CONST_RES_AUTH).setText(resAuth);
		return resourceRefElement;
	}
	protected void backupXml(File targetXml, String backupXmlPath) throws IOException {
		if(targetXml == null || !targetXml.exists()) {
			System.out.println("[警告]：要备份的 web.xml 不存在！");
			return;
		}
		File backupFile = null;
		if(backupXmlPath == null || backupXmlPath.length() == 0) {
			backupFile = new File(targetXml.getAbsolutePath() + FILE_BACKUP_SUFFIX);
		} else {
			backupFile = new File(backupXmlPath);
		}
		if (!backupFile.exists()) {
			backupFile.createNewFile();
		}
		FileUtils.copyFile(targetXml, backupFile);
	}
//	@SuppressWarnings("unchecked")
//	public static boolean hasAttribute(Element targetElment, String attributeName, String attributeValue) {
//		if (targetElment == null 
//				|| attributeName == null 
//				|| attributeName.length() == 0) {
//			return false;
//		}
//		List<Attribute> attrs = targetElment.attributes();
//		if (attrs.size() == 0) {
//			return false;
//		}
//		for (int i = 0, len = attrs.size(); i < len; i++) {
//			Attribute attr = attrs.get(i);
//			if (attr.getName().equals(attributeName)) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//	public static void printElement(Element element) {
//		System.out.print("节点名：" + element.getName());
//		
//		List<Attribute> attrs = element.attributes();
//		
//		if(attrs.size() > 0) {
//			for (Attribute attr : attrs) {
//				System.out.print(" 属性名: " + attr.getName() + " 值: " + attr.getValue());
//			}
//		}
//		
//		if (element.elements().size() == 0) {
//			System.out.print(" 节点值: " + element.getStringValue());
//		}
//		System.out.println();
//		
//		for (Iterator<Element> it = element.elementIterator(); it.hasNext();) {
//			printElement(it.next());
//		}
//	}
}
