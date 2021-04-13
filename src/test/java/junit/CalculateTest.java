package junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculateTest {

	private static Calculate calculate;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		calculate = new Calculate();
		System.out.println("----所有测试方法执行前----");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("----所有测试方法执行后----");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("----测试方法执行之前----");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("----测试方法执行之后----");
	}

	@Test
	public void addTest() {
		assertEquals(6, calculate.addInt(3, 3));
	}
	/**
	 * 预期抛出算数异常, 如果抛出异常则执行成功, 不抛出执行显示 Failure
	 */
	@Test(expected=ArithmeticException.class)
	public void testDivide() {
		assertEquals(6, calculate.divide(6, 1));
	}
	/**
	 * 设置方法执行时间, 超时将自动终止方法执行, 防止出现死循环
	 */
	@Ignore
	@Test(timeout=2000)//单位毫秒
	public void testLoop() {
		for (int i=0; true; i++) {
			System.out.println(i);
		}
	}
}
