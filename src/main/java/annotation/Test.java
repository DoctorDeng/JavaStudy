package annotation;

public class Test {
	
	@SuppressWarnings("deprecation")
	public void sing() {
		Person p = new Child();
		p.sing();
	}
	
}
