package guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.springframework.util.Assert;

/**
 * 练习  Guava 常见 Object 方法
 * @author <a href="http://doctordeng.vip/">DoctorDeng</a>
 * @since 2018年12月15日
 */
public class ObjectsTest {
	private static void equalsTest() {
		Assert.isTrue(Objects.equal("a", "a"));
		Assert.isTrue(Objects.equal(null, null));
		Assert.isTrue(Objects.equal("a", null) == Objects.equal(null, "a"));
		
		
	}
	
	private static void hashCodeTest() {
		ObjectsDemo obj1 = new ObjectsDemo("张三", 22);
		ObjectsDemo obj2 = new ObjectsDemo("李四", 33);
		
		System.out.println(Objects.hashCode(obj1.getAge(), obj1.getName()));
		System.out.println(Objects.hashCode(obj2.getAge(), obj2.getName()));
		System.out.println(java.util.Objects.hash(obj1.getAge(), obj1.getName()));
	} 
	
	private static void toStringHelperTest() {
		ObjectsDemo obj1 = new ObjectsDemo("张三", 22);
		String str = MoreObjects.toStringHelper(obj1.getClass())
											.add("name", obj1.getName())
											.addValue('1').toString();
		System.out.println(str);
	}
	
	private static void compareToTest() {
		class Person implements Comparable<Person> {
			private String lastName;
			private String firstName;
			private int zipCode;

			public int compareTo(Person other) {
				// 未使用 Guava 之前
/*				int cmp = lastName.compareTo(other.lastName);
				if (cmp != 0) {
					return cmp;
				}
				cmp = firstName.compareTo(other.firstName);
				if (cmp != 0) {
					return cmp;
				}
				return Integer.compare(zipCode, other.zipCode);*/
				
				// 使用 Guava 之后
				return ComparisonChain.start()
						.compare(lastName, other.lastName)
						.compare(firstName, other.firstName)
						.compare(zipCode, other.zipCode)
						.result();
				
			}
		}
	}
	
	public static void main(String[] args) {
		equalsTest();
		hashCodeTest();
		toStringHelperTest();
	}
}

class ObjectsDemo {
	String name;
	Integer age;
	
	public ObjectsDemo() {
		super();
	}
	
	public ObjectsDemo(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public Integer getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
