/*
 * Description:  练习import和package的使用
 */
import tom.jiafei.Trangle;
import java.util.Date;

public class SunRise {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Trangle trangle = new Trangle(12,23,23);
        trangle.getArea();
        trangle.changeSide(22, 1, 1000);
        trangle.getArea();
        Date date = new Date();
        System.out.println(date);
	}

	
}
