package interfacePractice;

public class TestMain {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
       TestA a= new TestA();
       System.out.println("x为5,testA类中的方法f()的结果是:"+a.f(5));
       System.out.println("x为5,y为5,testA类中的方法g()的结果是:"+a.g(5,5));
       
       TestB b= new TestB();
       System.out.println("x为5,testB类中的方法f()的结果是:"+b.f(5));
       System.out.println("x为5,y为5,testB类中的方法g()的结果是:"+b.g(5,5));
       
       IComputable aI= new TestA();
       System.out.println("x为5,接口IComputable的对象aI引用TestA类中的方法f()的结果是:"+aI.f(5));
       System.out.println("x为5,y为5,接口IComputable的对象aI引用TestA类中的方法g()的结果是:"+aI.g(5,5));
	}

}
