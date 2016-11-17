package xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import util.PathUtil;


public class DOMUtil {
	
	public static void systemOutXmlData() {
		try {
			//通过 DocumentBuilder 对象的 parse 方法解析 XML 文件
			Document document = getDocumentBudiler().parse(new InputSource(PathUtil.getFileInputStreamReder("xml/books.xml", "utf-8")));
			//获取文档根元素
			Element rootElement = document.getDocumentElement();
			//获取 根元素下 所有子节点(不包括子节点的子节点)
			NodeList nodeList = rootElement.getChildNodes();
			Node node =  rootElement;
			
			outNodes(node);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void outNodes(Node node) {
		/**
		 * 由于 XML 文件中两个标签中间的空格组成的空白也是一个节点,故这里要排除这些节点
		 */
		if (Node.TEXT_NODE == node.getNodeType() && "".equals(node.getTextContent().trim())) {
			return;
		}
		//获取属性名
		System.out.print("\n\r节点名:"+node.getNodeName() + "    ");
		
		if (node.hasAttributes()) {
			NamedNodeMap attrs = node.getAttributes();
			
			System.out.print("属性:");
			for (int j = 0; j < attrs.getLength(); j++) {
				//通过 item(i) 获取节点,注意 NodeList 下标从零开始
				//注意: Node 通过强制转换可以装换为 Element 
				Node attributeNode = attrs.item(j);
				//获取属性值
				System.out.print("  " + attributeNode.getNodeName() + ":" +attributeNode.getNodeValue() + ";");
			}
		}
		/**
		 * 注意在通过 getChildNodes() 方法获取的子节点中包含 Text 节点
		 * 即 XML 文档中两个便签之间的空白,也是节点之一,所以这里要使用方法
		 * 判断节点是否有一个子节点,防止因为是 Text 节点而造成的空指针异常
		 */
		if (node.hasChildNodes() && node.getChildNodes().getLength() > 1) {
			NodeList nodes = node.getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nodeTmep = nodes.item(i);
				outNodes(nodeTmep);
			}
		} else {
			System.out.print("  值:" + node.getTextContent() + "");
		}
	}
	/**
	 * 生成 XML 
	 */
	public static void createXml() {
		DocumentBuilder db  = getDocumentBudiler();
		Document document   = db.newDocument();
		/**
		 * 去除生成 xml 文件 头部的 standalone 属性即:
		 * <pre>
		 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
		 * </pre>
		 * standalong：说明 xml 文件是否有 dtd 或 schema 文件, yes 为没有，默认且不显示
		 */
		document.setXmlStandalone(true);
		
		Element rootElement = document.createElement("books");
		Element child = document.createElement("book");
		child.setAttribute("id", "1");
		
		Element name  = document.createElement("name");
		name.setTextContent("安徒生童话");
		child.appendChild(name);
		/**
		 * 为节点添加 Text 节点等类似于节点Element.setTextContent(),
		 */
		Element time  = document.createElement("time");
		Text text = document.createTextNode("2016-10-20");
		time.appendChild(text);
		child.appendChild(time);
		/**
		 * 注意添加节点的顺序,先将子节点添加到父节点上,最后添加根节点(节点顺序没必要特别注意,好像没什么问题,)
		 */
		//根节点下添加子节点
		rootElement.appendChild(child);
		//添加根节点
		document.appendChild(rootElement);
		
		
		//创建 TransformerFactory 对象
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			Transformer tf = tff.newTransformer();
			/**
			 * 输出 XML 文件时,开启换行
			 */
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(new DOMSource(document), new StreamResult(new File("book1.xml")));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	
	public static DocumentBuilder getDocumentBudiler() {
		//创建了一个 DocumentBuilderFactory 对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//创建一个 DocumentBuilder 的对象
		DocumentBuilder db = null;
		
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		return db;
	}
	
	public static void main(String[] args) {
		//DOMUtil.systemOutXmlData();
		DOMUtil.createXml();
	}

}
