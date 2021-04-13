package corejava.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 
 * @author Doctor邓
 *
 */
public class ObjectAnalyzer {
	// 存储打印过的对象, 防止重复打印和死循环
	private ArrayList<Object> visited = new ArrayList<>(); 
	/**
	 * 打印目标对象的所有成员变量,如果为基础数据类型，则打印其值
	 * @param targetObject 目标对象
	 * @return String
	 */
	public String toString(Object targetObject) {
		if (null == targetObject) return "null";
		
		if (visited.contains(targetObject)) return "...";
		visited.add(targetObject);
		
		Class<?> clazz = targetObject.getClass();
		if (clazz == String.class) return (String) targetObject;
		
		StringBuilder returnString = new StringBuilder();
		
		// 打印数组
		if (clazz.isArray()) {
			// getComponentType():返回表示数组组件类型的 Class
			returnString.append(clazz.getComponentType() + "[]{");
			
			for (int i = 0, len = Array.getLength(targetObject); i < len; i++) {
				if (i > 0) returnString.append(",");
				
				Object value = Array.get(targetObject, i);
				// isPrimitive(): 判定指定的 Class 对象是否表示一个基本类型。
				if (clazz.getComponentType().isPrimitive()) returnString.append(value);
				else returnString.append(toString(value));
			}
			return returnString.append("}").toString();
		}
		
		returnString.append(clazz.getName());
		do {
			returnString.append("[");
			Field[] fields = clazz.getDeclaredFields();
			// 使用 AccessibleObject 将所有 Field 设置为可访问
			AccessibleObject.setAccessible(fields, true);
			// 获取所有属性名称
			for (Field field : fields) 
			{
				if (!Modifier.isStatic(field.getModifiers())) 
				{
					if (!returnString.toString().endsWith("[")) returnString.append(",");
					returnString.append(field.getName() + "=");
					try {
						// getType():返回一个 Class 对象，它标识了此 Field 对象所表示字段的声明类型。
						Class typeClazz = field.getType();
						Object value = field.get(targetObject);
						if (typeClazz.isPrimitive()) returnString.append(value);
						else returnString.append(toString(value) + "\r\n");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			returnString.append("]");
			clazz = clazz.getSuperclass();
		} while (null != clazz);
		
		return returnString.toString();
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> squares = new ArrayList<>();
		for (int i = 0; i < 5; i++) squares.add(i);
		System.out.println(new ObjectAnalyzer().toString(squares));
	}
}
