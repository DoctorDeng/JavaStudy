package HomeWork;
import java.util.Arrays;
public class Exam {
	public void sortScore(int[] scores){
		Arrays.sort(scores);
		for(int i=scores.length-1, j=0;j<3;j++){			
			System.out.println(scores[i]);
			i--;
		}
		
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        int[] socre={89,-23,64,91,119,52,73};
        Exam sort=new Exam();
        System.out.println("考试成绩前三名为:");
        sort.sortScore(socre);             
	}

}
