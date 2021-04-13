package guava;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 练习 Guava Option 的使用
 * @author <a href="http://doctordeng.vip/">DoctorDeng</a>
 * @since 2018年11月19日
 */
public class OptionalTest {
	
	/**
	 * 练习 Option API
	 */
	public static void apiTest() {
		// 创建指定引用的Optional实例，若引用为 null 则快速失败(抛出 java.lang.NullPointerException)
		//Optional<String> a = Optional.of(null);
		Optional<String> b = Optional.of("2");
		b.asSet();
	}
	
	/**
	 * 使用 Option 来处理 null
	 * 可以看出与原代码相比更加简洁
	 */
	public static void handlNull() {
		final List<Integer> list = Lists.newArrayList(1, 2, null, 4);
		int sum = 0;
		for (Integer temp : list) {
			sum += Optional.fromNullable(temp).or(0);
		}
		// 不用 Option Java 代码,
/*		for (Integer temp : list) {
			if (temp != null) {
				sum += temp;
			}
		}*/
		
		System.out.println(sum);
	}

	public static void main(String[] args) {
		OptionalTest.handlNull();
		OptionalTest.apiTest();
		//Optional<Integer> possible = Optional.of(5);
		//Optional.absent();
	}
}
