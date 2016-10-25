package junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculateTest {

	private static Calculate calculate;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		calculate = new Calculate();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addTest() {
		// System.out.println(calculate.addInt(1, 2));
		assertEquals(6, new Calculate().addInt(3, 3));
	}

}
