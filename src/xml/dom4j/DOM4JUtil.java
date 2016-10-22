package xml.dom4j;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import xml.PathUtil;

public class DOM4JUtil {
	public static  void printXml() {
		//创建 SAXReader
		SAXReader reader = new SAXReader();
		try {
			//通过 reader 对象的 read() 方法, 加载 XML 文件
			Document document   = reader.read(PathUtil.getFileInputStreamReder("xml/books.xml", "utf-8"));
			//获取根节点
			Element rootElement = document.getRootElement();

			for (Iterator<Element> it = rootElement.elementIterator(); it.hasNext();) {
				printElement(it.next());
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
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
	
	public static void main(String[] args) {
		DOM4JUtil.printXml();
	}
}
