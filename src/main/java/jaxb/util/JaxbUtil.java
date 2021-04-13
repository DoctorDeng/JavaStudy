package jaxb.util;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;

/**
 * 利用 JAXB 提供 xml 和 bean 互相转换服务工具类
 * @author denghuajie
 * @since 2018年12月24日
 */
public class JaxbUtil {
	/**
	 * 默认输出 xml 编码
	 */
	private static final String DEFAULT_XML_ENCODING = "UTF-8";
	/**
	 * 默认不格式化输出  xml
	 */
	private static final boolean DEFAULT_FORMAT_OUTPUT = false;
	private static final CharacterEscapeHandler DEFAULT_CHARACTER_ESCAPE_HANDLER = new CharacterEscapeHandler() {
        @Override
        public void escape(char[] ac, int i, int j, boolean flag,
                Writer writer) throws IOException {
            writer.write(ac, i, j);
        }
    };
    
    private static final MarshallerStringListener STRING_LISTENER = new MarshallerStringListener();
    
	/**
	 * 将 xml 形式的 String 类型字符串转换成对应的 Java Bean 类型
	 * @param xmlString 形如：
	 * <blockquote>
	 *    <pre>
	 *	&lt;?xml version=&quot;1.0&quot;?&gt;
	 *	&lt;data&gt;
	 *	  &lt;userName&gt;1&lt;/userName&gt;
	 *	&lt;/data&gt;
	 *    </pre>
	 * </blockquote>
	 * xml 格式的字符串
	 * @param classesToBeBound {@link JAXBContext#newInstance(Class... )}
	 * @return the newly created root object of the java content tree
	 * @throws JAXBException IllegalArgumentException 如果参数为空
	 */
	@SuppressWarnings({"unchecked" })
	public static final <T> T xmlStringToBean(String xmlString, Class<?>... classesToBeBound) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(classesToBeBound);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (T) unmarshaller.unmarshal(new StringReader(xmlString));
	}
	
	/**
	 * 将 Java Bean 换成成为 xml 格式的字符串
	 * @param jaxbElement 需要转换的 Java Bean 对象,不能为 null
	 * @param classesToBeBound {@link JAXBContext#newInstance(Class... )}
	 * @return  形如：
	 * <blockquote>
	 *    <pre>
	 *	&lt;?xml version=&quot;1.0&quot;?&gt;
	 *	&lt;data&gt;
	 *	  &lt;userName&gt;1&lt;/userName&gt;
	 *	&lt;/data&gt;
	 *    </pre>
	 * </blockquote>
	 * xml 格式的字符串
	 * @throws JAXBException IllegalArgumentException 如果参数为空
	 */
	public static String beanToXmlString(Object jaxbElement, Class<?>...classesToBeBound) throws JAXBException {
		return beanToXmlString(jaxbElement, DEFAULT_FORMAT_OUTPUT, DEFAULT_XML_ENCODING, classesToBeBound);
	}
	
	/**
	 * 将 Java Bean 换成成为 xml 格式的字符串
	 * @param jaxbElement 需要转换的 Java Bean 对象 ,不能为 null
	 * @param classesToBeBound {@link JAXBContext#newInstance(Class... )}
	 * @param formatOutput 是否格式输出, true 是, false 否
	 * @param encoding 输出 编码,不能为 null
	 * @return  形如：
	 * <blockquote>
	 *    <pre>
	 *	&lt;?xml version=&quot;1.0&quot;?&gt;
	 *	&lt;data&gt;
	 *	  &lt;userName&gt;1&lt;/userName&gt;
	 *	&lt;/data&gt;
	 *    </pre>
	 * </blockquote>
	 * xml 格式的字符串
	 * @throws JAXBException IllegalArgumentException 如果参数为空
	 */
	public static String beanToXmlString(Object jaxbElement, boolean formatOutput, String encoding, Class<?>...classesToBeBound) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);  
		Marshaller marshaller = jaxbContext.createMarshaller();  
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatOutput);  
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
		marshaller.setProperty(CharacterEscapeHandler.class.getName(), DEFAULT_CHARACTER_ESCAPE_HANDLER);
		marshaller.setListener(STRING_LISTENER); 
		StringWriter writer = new StringWriter();  
		marshaller.marshal(jaxbElement, writer);  
		return writer.toString();
	}
	/**
	 * 当 实体类中的 String 类型变量为 null 时依然进行输出而不是默认的忽略
	 */
	static class MarshallerStringListener extends Marshaller.Listener {
		public static final String BLANK_CHAR = "";

		@Override
		public void beforeMarshal(Object source) {
			super.beforeMarshal(source);
			Field[] fields = source.getClass().getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				try {
					if (f.getType() == String.class && f.get(source) == null) {
						f.set(source, BLANK_CHAR);
					}
				} catch (IllegalAccessException e) {
					
				}
			}
		}
	} 
}
