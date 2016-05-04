package practice.genericParadigm;
/*
 * Description:    练习Stack<E>泛型类练习
 *         说明:    此泛型是堆栈泛型,使用此泛型可以构建一个堆栈。
 */
import java.util.*;

public class StackPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(new Integer(1));
		stack.push(new Integer(1));
		int k =1;
		while(k <= 10) {
			for(int i=1; i<=2; i++){
				Integer F1 = stack.pop();
				int f1 = F1.intValue();
				Integer F2 = stack.pop();
				int f2 = F2.intValue();
				Integer temp = new Integer(f1+f2);
				System.out.println(""+temp.toString());
				stack.push(temp);
				stack.push(f2);
			}
			k++;
		}
	}

}
