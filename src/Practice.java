import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
 * Description:  练习专用类
 */
public class Practice {
	
	int[] test ={1,2,3,4,5,6,7,8};
	
	public void sortArray() {
		for(int i=0; i<test.length; i++) {
			for(int j=0 ; j<test.length-i-1; j++) {
				if(test[j] < test[j+1]) {
					int temp = test[j];
					test[j] = test[j+1];
					test[j+1] = temp;
				}
			}
		}
		for(int a : test) {
			System.out.println(a);
		}
		
	}
	

	public static void main(String[] args) {
    Practice test = new Practice();
	test.sortArray();
    
	}
}
