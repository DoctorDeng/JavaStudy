package xml.jdom;

import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import xml.PathUtil;

public class JDOMUtil {
	public static void printXml() {
		//1、创建一个 SaxBuilder 对象
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			//将指定文件的输入流 InputStream 加载到 SaxBuilder 中
			//注意: 返回的 Document 是 jdom 下的,不是 W3c 中的
			Document document    = saxBuilder.build(PathUtil.getFileInputStreamReder("xml/books.xml", "utf-8"));
			//获取 XML 文件根节点
			Element rootElement  = document.getRootElement();
			//获取根节点下的子节点的集合
			List<Element> childs = rootElement.getChildren();
			
			for (Element element : childs) {
				printElement(element);
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 打印一个 ELement 下的所有信息, 包括子节点
	 * @param element
	 */
	public static void printElement(Element element) {
		System.out.print(" 节点名：" + element.getName());
		
		if (element.hasAttributes()) {
			List<Attribute> attrs = element.getAttributes();
			for (Attribute attr : attrs) {
				System.out.print(" 属性名：" + attr.getName() + " 值：" + attr.getValue());
			}
		}
		
		List<Element> childs = element.getChildren();

		if (childs.size() == 0) {
			System.out.print(" 值：" + element.getValue());
			return;
		}
		System.out.println();
		for (int i = 0, size = childs.size(); i < size; i++) {
			printElement(childs.get(i));
		}
		
	}
	
	public static void main(String[] args) {
		JDOMUtil.printXml();
	}
}
