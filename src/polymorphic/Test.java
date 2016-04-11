package polymorphic;

public class Test {

	public static void main(String[] args) {
		Father child1 =new Father();
		
		//父类的引用指向子类对象，称之为多态
		Father child2 =new Children();
		//父类的引用无法访问子类独有的方法，故以下会有错误
		
		child1.eat();
		child2.eat();
		
		Father child3 =new CatChildren();
		child3.eat();
        
		Children child4 = new Children();
		Father father = child4;//自动类型提升，向上类型转换
		
		if(father instanceof Children){
			Children child5=(Children)father;//向下类型转换(强制类型转换)
			System.out.println("可以进行强制类型转换！转换成Children类型！");
		}
		else{
			System.out.println("无法进行强制类型转换！转换成Children类型！");
		}
		
		if(father instanceof CatChildren){
			System.out.println("可以进行强制类型转换！转换成Children类型！");
	        CatChildren cat=(CatChildren)father;//1、编译时CatChildren类型 2、运行时Children类型
		}
		else{
			System.out.println("无法进行类型转换！转换成CatChildren类型!");
		}
	}

}
