package junit;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	private static ApplicationContext ac = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("junit/applicationContext.xml");
	}

	@Test
	public void test() {
		Date date = (Date) ac.getBean("date");
		System.out.println(date.getTime());
	}

}
