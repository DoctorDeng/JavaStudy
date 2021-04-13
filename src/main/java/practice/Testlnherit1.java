package practice;
/* 
 * Description:     练习Object类的equals()
 */
public class Testlnherit1 {

	public static void main(String[] args) {
		
		FatherClass child1 = new FatherClass();
		child1.age = 15;
		FatherClass child2 = new FatherClass();
		child2.age = 15;
		if(child1.equals(child2)){
			System.out.println("两个对象是相同的！");
		}
		else{
			System.out.println("两个对象是不同的！");
		}

	}

}
