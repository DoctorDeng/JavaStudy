package generics;
/**
 * 练习 JAVA 泛型
 * @author Administrator
 *
 */
public class TestGenerics {


	    public static void main(String[] args) {

	        Box<String> name = new Box<String>("corn");
	        Box<Integer> age = new Box<Integer>(712);
	        Box<Number> number = new Box<Number>(314);

	        getData(name);
	        getData(age);
	        getData(number);
	    }

	    public static void getData(Box<?> data) {
	        System.out.println("data :" + data.getData());
	    }
}
class Box<T> {

    private T data;

    public Box() {

    }

    public Box(T data) {
        setData(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
