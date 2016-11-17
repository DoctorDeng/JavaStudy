package xml.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMCDATA;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import util.PathUtil;


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

	public static void createXml() {
		//1.创建 Document 对象
		Document document = DocumentHelper.createDocument();
		//2.创建根节点
		Element rss = document.addElement("rss");
		//3.向节点中添加 属性
		rss.addAttribute("version", "2.0");
		//生成子节点及内容
		Element channel = rss.addElement("channel");
		Element title   = channel.addElement("title");
		CDATA cdata     = new DOMCDATA("<你好>");
		title.add(cdata);;
		Element name    = channel.addElement("name");
		name.setText("<安徒生童话>");
		
		//设置生成的 XML 文件相关属性
		//OutputFormat.createPrettyPrint() : 自动生成格式良好的 XML 文件
		OutputFormat format = OutputFormat.createPrettyPrint();
		//设置编码
		format.setEncoding("GBK");
		
		try {

			//---------------最后.生成 XML 文件-----------------------------//
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File("rssDom4j.xml")), format);
			
			//设置是否将特殊字符 转义, 默认为 True(转义)
			writer.setEscapeText(false);;
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//DOM4JUtil.printXml();
		DOM4JUtil.createXml();
	}
}
