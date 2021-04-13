package xml.sax;

import com.sun.xml.internal.bind.util.AttributesImpl;
import org.xml.sax.SAXException;
import util.PathUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SAXUtil {
	public static void printXml() {
		//获取一个 SAXParserFactory 的实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			//通过 factory 获取 SAXParser 实例
			SAXParser parser = factory.newSAXParser();
			//创建  SAXParserHandler 对象
			SAXParserHandler handler = new SAXParserHandler();
			parser.parse(PathUtil.getFileInputStream("xml/books.xml"), handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SAXUtil.createXml();
	}
	
	public static void createXml() {
		//1.创建一个 TransformerFactory 类的对象
		SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		try {
			//2.通过 SAXTransformerFactory 创建  TransformerHandler
			TransformerHandler handler = tff.newTransformerHandler();
			//3.通过 TransformerHandler 对象创建 Transformer 对象
			Transformer tf = handler.getTransformer();
			
			//4.通过 Transformer 对生成的 XML 文件属性进行设置
			//注意: 设置属性必须在 Handler 设置 Result 之前, 否则无效
			tf.setOutputProperty(OutputKeys.ENCODING, "utf-8"); //编码设置
			tf.setOutputProperty(OutputKeys.INDENT, "yes"); //换行设置
			
			try {
			/*	File file = new File("saxbooks.xml");
				if (!file.exists()) {
					file.createNewFile();
				}*/
				//5.创建一个 Result 对象
				Result result = new StreamResult(new FileOutputStream(new File("saxbooks.xml")));
				//6.讲 Result 对象域 Handler 对象关联
				handler.setResult(result);
				
				
				//--------------使用 Handler 对 XML 文件进行编写----------------------//
				handler.startDocument();
				
				AttributesImpl attr = new AttributesImpl();
				handler.startElement("", "", "books", attr);
				
				//清除 AttributesImpl
				attr.clear();
				attr.addAttribute("", "", "id", "String", "1");
				handler.startElement("", "", "book", attr);
				
				handler.startElement("", "", "name", null);
				handler.startCDATA();
				handler.characters("安徒生童话".toCharArray(), 0, 5);
				handler.endCDATA();
				handler.endElement("", "", "name");
				
				handler.startElement("", "", "author", null);
				handler.characters("安徒生".toCharArray(), 0, 3);
				handler.endElement("", "", "author");
				
				handler.endElement("", "", "book");
				handler.endElement("", "", "books");
				handler.endDocument();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (@SuppressWarnings("hiding") IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
	}
}
