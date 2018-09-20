package annotation;

public interface Person {
	public String getName();
	
	public int getAge();
	
	@Deprecated
	public void sing();
}
