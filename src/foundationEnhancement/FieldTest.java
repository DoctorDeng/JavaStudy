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
	
	public FieldTest(int x, String y) {
		super();
		this.x = x;
		this.y = y;
	}

	public static void main(String[] args) {
		FieldTest fieldTest = new FieldTest(2, "3");
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

}
