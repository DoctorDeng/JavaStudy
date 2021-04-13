package practice;
/*
 * Description: 测试接口和匿名内部类
 */
interface IHuman{
	public void speak();
}
class Chinese{
	void f(IHuman man){
		man.speak();
	}
}
public class InterfaceNimingClass {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Chinese chin= new Chinese();
        chin.f(new IHuman(){
        	public void speak(){
        		System.out.println("我是中国人,我会说汉语");
        	}
        });
	}

}
