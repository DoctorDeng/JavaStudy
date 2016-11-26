package foundationEnhancement;

import java.lang.reflect.Field;

/**
 * 练习 反射的 Field 类
 * @author Doctor邓
 *
 */
public class FieldTest {
	private int x;
	public String y;
	private String z;
	
	public FieldTest(int x, String y, String z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public static void main(String[] args) {
		changeStringValue(new FieldTest(2, "5s55ss66", "abcdesf"));
	}
	//将对象中所有为 String 类型的成员变量中的 s 该为 AA
	public static void changeStringValue(Object obj) {
		Class<? extends Object> clas = obj.getClass();
		Field[] fields = clas.getDeclaredFields();
		
		for (Field field : fields) {
			//获取成员变量名称
			//System.out.println(field.getName());
			// 获取成员变量类型的类类型
			Class<?> fieldTypeClass = field.getType();
			// 当 成员变量为 String 类型时, 将 其中的 s 都替换为 AA
			// 这里使用 == 比 equals() 更为准确, 因为都是同一份 String.class
			if (String.class == fieldTypeClass) {
				try {
					//获取成员变量值
					String oldValue = (String)field.get(obj);
					//获取改变后的值
					String newValue = oldValue.replace("s", "AA");
					//将改变后的值设置到赋值给成员变量
					field.set(obj, newValue);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				} 
			}
		}
		System.out.println(obj.toString());
	}
	@Override
	public String toString() {
		return "FieldTest [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	public static void fieldTest() {
		FieldTest fieldTest = new FieldTest(2, "3","你好sb");
		Class<? extends FieldTest> cla = FieldTest.class;
		try {
			// 获取单个 Field, Field 不是对象的变量, 而是 Class 类上, 要用它取获取某个对象上对应的值
			// getField(): 返回一个 Field 对象，它反映此 Class 对象所表示的类或接口的指定公共成员字段(共有)
			Field  publicField = cla.getField("y");
			// 使用 Field 从某个具体的对象上获取值获取某个对象上的值
			System.out.println(publicField.get(fieldTest));
			//返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段(所有)
			Field allField  = cla.getDeclaredField("x"); 
			//将此对象的 accessible 标志设置为指示的布尔值。
			//值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
			//值为 false 则指示反射的对象应该实施 Java 语言访问检查。 即可以方法 private 修饰的变量
			allField.setAccessible(true);
			System.out.println(allField.get(fieldTest));
			
			// 获取所有 Field
			Field[] fields = cla.getFields();
			for (int i =0, len = fields.length; i < len; i++) {
				System.out.println(fields[i].toString());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
