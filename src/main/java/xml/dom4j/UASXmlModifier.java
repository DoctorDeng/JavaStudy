package xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import xml.dom4j.core.WebXmlCommonConst;
import xml.dom4j.core.filter.XmlFilterConfig;
import xml.dom4j.core.listener.XmlListenerConfig;
import xml.dom4j.core.resourceRef.XmlResourceRefConfig;
import xml.dom4j.core.servlet.XmlServletConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author denghuajie
 * 
 * @since 2018年1月22日 上午11:34:43
 * 
 */
public class UASXmlModifier extends WebXmlModifier {
	private static final String CLASS_RUNTIME_FILTER = "com.nstc.web.filter.RuntimeFilter";
	private static final String CLASS_UAS_FILTER   = "com.nstc.uas.client.UASFilter";
	private static final String CLASS_UAS_LISTENER = "com.nstc.uas.client.SessionListener";
	private static final String CLASS_UAS_SERVLET  = "com.nstc.uas.client.UASServlet";
	private static final String NAME_WEB_XML = "web.xml";
	private static final String NAME_RUNTIME_FILTER = "RuntimeFilter";
	private static final String NAME_UAS_FILTER  = "UASFilter";
	private static final String NAME_UAS_SERVLET = "uasSerlvet";
	private static final String NAME_RES_REF     = "nstc/jdbc/WebData";
	private static final String RES_TYPE         = "javax.sql.DataSource";
	private static final String RES_AUTH         = "Container";
	
	@Override
	public void xmlModifiy(File xmlFile) throws IOException {
		if (xmlFile == null || !xmlFile.exists()) {
			System.out.println("[警告]：文件不存在或不合法");
			return;
		}
		backupXml(xmlFile, null);
		SAXReader reader  = new SAXReader();
		Document document = null;
		try {
			document = reader.read(xmlFile);
		} catch (DocumentException e) {
			System.out.println("[警告]：读取 web.xml 文件失败, 请检查文件的有效性");
			e.printStackTrace();
		}
		if (document == null ) {
			return;
		}
		
		Element rootElement = document.getRootElement();
		checkUASFilter(rootElement);
		checkSessionListener(rootElement);
		checkUASServlet(rootElement);
		checkUASDataSource(rootElement);
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter output = new XMLWriter(new FileWriter(xmlFile), format);
		// 设置是否将特殊字符 转义, 默认为 True(转义)
		output.setEscapeText(false);
		try {
			output.write(document);
		} catch (IOException e) {
			throw e;
		} finally {
			output.close();
		}
	}
	@SuppressWarnings("unchecked")
	private void checkUASFilter(Element rootElement) {
		Iterator<Element> it  = rootElement.elementIterator(XmlFilterConfig.CONST_FILTER);
		boolean isAddUASFilter = true;
		for (; it.hasNext();) {
			Element filterElement = it.next();
			Element filterClass = filterElement.element(XmlFilterConfig.CONST_FILTER_CLASS);
			if (CLASS_UAS_FILTER.equals(filterClass.getStringValue().trim())) {
				System.out.println("[提示]: web.xml 文件中有 UASFilter 配置");
				return;
			}
			if(CLASS_RUNTIME_FILTER.equals(filterClass.getStringValue().trim())) {
				isAddUASFilter = false;
				filterClass.setText(CLASS_UAS_FILTER);
			}
		}
		if (isAddUASFilter) {
			addFilter(rootElement, NAME_RUNTIME_FILTER, CLASS_UAS_FILTER);
			addFilterMapping(rootElement, NAME_RUNTIME_FILTER, WebXmlCommonConst.CONST_URL_PATTERN_DO,  null);
			addFilterMapping(rootElement, NAME_RUNTIME_FILTER, WebXmlCommonConst.CONST_URL_PATTERN_SF,  null);
			addFilterMapping(rootElement, NAME_RUNTIME_FILTER, WebXmlCommonConst.CONST_URL_PATTERN_JSP, null);
		}
	}
	@SuppressWarnings("unchecked")
	private void checkSessionListener(Element rootElement) {
		Iterator<Element> it  = rootElement.elementIterator(XmlListenerConfig.CONST_LISTENER);
		for (; it.hasNext();) {
			Element listenerElement = it.next();
			Element listenerClass = listenerElement.element(XmlListenerConfig.CONST_LISTENER_CLASS);
			if (CLASS_UAS_LISTENER.equals(listenerClass.getStringValue().trim())) {
				System.out.println("[提示]: web.xml 文件中有 SessionListener 配置");
				return;
			}
		}
		addListener(rootElement, CLASS_UAS_LISTENER);
	}
	@SuppressWarnings("unchecked")
	private void checkUASServlet(Element rootElement) {
		Iterator<Element> it  = rootElement.elementIterator(XmlServletConfig.CONST_SERVLET);
		for (; it.hasNext();) {
			Element servletElement = it.next();
			Element servletClass = servletElement.element(XmlServletConfig.CONST_SERVLET_CLASS);
			if (CLASS_UAS_SERVLET.equals(servletClass.getStringValue().trim())) {
				System.out.println("[提示]: web.xml 文件中有 UASServlet 配置");
				return;
			}
		}
		addServlet(rootElement, NAME_UAS_SERVLET, CLASS_UAS_SERVLET, "5");
		addServletMapping(rootElement, NAME_UAS_SERVLET, WebXmlCommonConst.CONST_URL_PATTERN_UAS);
	}
	@SuppressWarnings("unchecked")
	private void checkUASDataSource(Element rootElement) {
		Iterator<Element> it  = rootElement.elementIterator(XmlResourceRefConfig.CONST_RESOURCE_REF);
		for (; it.hasNext();) {
			Element resourceRefElement = it.next();
			Element resRefName = resourceRefElement.element(XmlResourceRefConfig.CONST_RES_REF_NAME);
			if (NAME_RES_REF.equals(resRefName.getStringValue().trim())) {
				System.out.println("[提示]: web.xml 文件中有 WebData 数据源配置");
				return;
			}
		}
		addResourceRef(rootElement, NAME_RES_REF, RES_TYPE, RES_AUTH);
	}
	public static void main(String[] args) throws IOException {
		File testFile = new File("C:\\Users\\Administrator\\Desktop\\web.xml");
		WebXmlModifier webXmlModifier = new UASXmlModifier();
		webXmlModifier.xmlModifiy(testFile);
	}
}
