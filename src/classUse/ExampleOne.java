package classUse;
/*
 * Description:            练习Class类的使用
 * String getName()        返回类的名字
 * Constructor[] getDeclaredConstructors()  返回类的全部构造方法
 * Field[] getDeclaredFields()              返回类的全部成员变量
 * Method[] getDeclaredMethods()            返回类的全部方法
 */
import java.lang.reflect.*;       //Java反射(reflect)   

class A{
	int x;
	float y;
	double z;
	
	A(){
		x = 12;
		y = 12.901f;
		z= 0.123456; 
	}
	
	A(int x, float y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getSum(){
		return x + y + z;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setZ(int z){
		this.z = z;
	}
}

public class ExampleOne {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        A a = new A(1, 23.3f, 34.32423);
        Class cs = a.getClass();
        String className = cs.getName();
        Constructor[] con = cs.getDeclaredConstructors();
        Field[]  field = cs.getDeclaredFields();
        Method[] method = cs.getDeclaredMethods();
        
        System.out.println("类的名字: "+className);
        System.out.println("类中有如下构造方法: ");
        for(int i= 0;i< field.length;i++){
        	System.out.println(field[i].toString());
        }
        
        System.out.println("类中有如下方法: ");
        for(int i= 0; i< method.length; i++){
        	System.out.println(method[i].toString());
        }
        
        System.out.println("类中有如下构造方法: ");
        for(int i= 0; i<con.length; i++){
        	System.out.println(con[i].toString());
        }
	}

}
