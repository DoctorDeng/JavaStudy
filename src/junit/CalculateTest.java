package junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalculateTest {

	private static Calculate calculate;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		calculate = new Calculate();
		System.out.println("----���в��Է���ִ��ǰ----");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("----���в��Է���ִ�к�----");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("----���Է���ִ��֮ǰ----");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("----���Է���ִ��֮��----");
	}

	@Test
	public void addTest() {
		assertEquals(6, calculate.addInt(3, 3));
	}
	/**
	 * Ԥ���׳������쳣, ����׳��쳣��ִ�гɹ�, ���׳�ִ����ʾ Failure
	 */
	@Test(expected=ArithmeticException.class)
	public void testDivide() {
		assertEquals(6, calculate.divide(6, 1));
	}
	/**
	 * ���÷���ִ��ʱ��, ��ʱ���Զ���ֹ����ִ��, ��ֹ������ѭ��
	 */
	@Ignore
	@Test(timeout=2000)//��λ����
	public void testLoop() {
		for (int i=0; true; i++) {
			System.out.println(i);
		}
	}
}
