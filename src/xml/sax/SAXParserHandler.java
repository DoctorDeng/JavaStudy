package xml.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import xml.entity.Book;

public class SAXParserHandler extends DefaultHandler {
	
	private String value     = null;
	private List<Book> books = new ArrayList<>();
	private Book book        = null;
	/**
	 * 用于遍历 XML 开始标签
	 */
	@Override
	public void startElement(String uri, String localName, String nodeName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, nodeName, attributes);
		System.out.print("节点名:" + nodeName);
		int attrsSize = attributes.getLength();
		
		if ("book".equals(nodeName)) {
			book = new Book();

			for (int i = 0; i < attrsSize; i++) {
				String attrName = attributes.getQName(i);
				System.out.print("    属性名:" + attrName + "    值:" + attributes.getValue(i));
				
				if ("id".equals(attrName)) {
					book.setId(attributes.getValue(i));
				}
			}
		}
	}
	/** 
	 * 用来遍历 XML 文件的结束标签
	 */
	@Override
	public void endElement(String uri, String localName, String nodeName) throws SAXException {
		super.endElement(uri, localName, nodeName);
		System.out.println("----" + nodeName + "----");
		if (null != book) {
			switch (nodeName) 
			{
			case "name":
				book.setName(value);
				break;
			case "author":
				book.setAuthor(value);
				break;
			case "year":
				book.setYear(value);
				break;
			case "price":
				book.setPrice(value);
				break;
			case "language":
				book.setLanguage(value);
				break;
			}
			value = null;
		}
		if ("book".equals(nodeName)) {
			books.add(book);
			book  = null;
		}
	}
	/**
	 * 用来标识整个解析开始
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("----SAX解析开始----");
		super.startDocument();
	}
	/**
	 * 用于标识整个解析结束					
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println(books);
		System.out.println("----SAX解析结束----");
	}
	/**
	 * 获取节点值
	 */
	@Override
	/**
	 * ch 为 整个 XML 文档内容, start: 节点开始位置, length: 节点长度
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		//System.out.print("开始: " + start + " 长度：" + length );
		super.characters(ch, start, length);
		/**
		 * SAX 也会将标签之间的空格或换行当做节点,这里将其排除
		 */
		value = new String(ch, start, length);
		if (!"".equals(value.trim())) {
			System.out.print("    节点值是：" + value);
			System.out.println();
		}
	}
}
