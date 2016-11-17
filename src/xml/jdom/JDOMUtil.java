package xml.jdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import util.PathUtil;


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
	
	public static void createXml() {
		//1.生成一个根节点
		Element rootElement = new Element("rss");
		//2.生成一个 Document
		Document document = new Document(rootElement);
		
		//为节点添加属性
		rootElement.setAttribute("version", "2.33.3");
		
		//添加子节点
		Element channel = new Element("channel");
		rootElement.addContent(channel);
		
		Element title   = new Element("title");
		title.setText("安徒生童话");
		channel.addContent(title);
		
		//使用 CDATA 不让特殊字符转义
		Element name   = new Element("name");
		title.setContent(new CDATA("<!--测试转义-->"));
		channel.addContent(name);
		
		
		try {
			//-------------生成 XML 文件--------------------------//
			XMLOutputter output = new XMLOutputter();
			
			//设置 生成的 XML 文件相关属性
			//Format.getPrettyFormat() : 自动生成格式良好的 XML 文件
			Format format = Format.getPrettyFormat();
			//设置编码
			format.setEncoding("GBK");
			//设置换行符,一般不需要设置
			format.setIndent("\r\n");
			output.setFormat(format);
			
			output.output(document, new FileOutputStream(new File("jdom.xml")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//JDOMUtil.printXml();
		JDOMUtil.createXml();
	}
}
