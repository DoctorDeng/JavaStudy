package practice;
/*
 * Description:  验证方法内部类
 */
public class InnerClassMethod {
	
	public void innerMethod(){
		class MInner{
			private int a=1;
			public void show(){
				System.out.println("我是方法内部类的方法show()");
			}
			
			public int getA(){
				return a;
			} 
		}
		
	    MInner inner= new MInner();
	    inner.show();
	    System.out.println("我是内部类MInner的私有属性A: 我的值是:"+inner.getA());
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        InnerClassMethod inner= new InnerClassMethod();
        inner.innerMethod();
	}

}
