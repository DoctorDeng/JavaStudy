package practice;
/*
 * 练习静态内部类
 */
public class InnerClassStatic {
    private int a=99;
    static int b=1;
    
    public static class StaticInner{
    	int b=2;
    	public void test(){
    		System.out.println("访问外部类中的b:"+InnerClassStatic.b);
    		System.out.println("访问内部类中的b:"+b);
    		InnerClassStatic hello=new InnerClassStatic();
    		System.out.println("访问外部类中的a"+hello.a);
    	}
    }

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        StaticInner hello=new StaticInner();
        hello.test();
	}

}
