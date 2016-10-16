package xml.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMUtil {
	
	public static void systemOutXmlData() {
		//创建了一个 DocumentBuilderFactory 对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//创建一个 DocumentBuilder 的对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			DOMUtil domUtil   = new DOMUtil();
			//通过 DocumentBuilder 对象的 parse 方法解析 XML 文件
			Document document = db.parse(domUtil.getClass().getClassLoader().getResourceAsStream("xml/books.xml"));
			//获取文档根元素
			Element rootElement = document.getDocumentElement();
			//获取 根元素下 所有子节点(不包括子节点的子节点)
			NodeList nodeList = rootElement.getChildNodes();
			Node node =  rootElement;
			
			outNodes(node);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
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
		System.out.print("节点名:"+node.getNodeName() + "    ");
		
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
		 * 判断节点是否由属性,防止因为是 Text 节点而造成的空指针异常
		 */
		if (node.hasChildNodes()) {
			NodeList nodes = node.getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nodeTmep = nodes.item(i);
				outNodes(nodeTmep);
			}
		} else {
			System.out.print("  值:" + node.getTextContent() + "");
		}
	}
	
	public static void main(String[] args) {
		DOMUtil.systemOutXmlData();
	}

}
