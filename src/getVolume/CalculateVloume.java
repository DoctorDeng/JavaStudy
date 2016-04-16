package getVolume;
/*
 * Description:  计算柱体体积主类
 */

public class CalculateVloume {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Pillar pillar;
        Geometry tuxing;
        
        tuxing= new Lader(12,22,100);
        System.out.println("梯形的面积:"+tuxing.getArea());
        
        pillar= new Pillar(tuxing,58);
        System.out.println("梯形柱体的体积:"+pillar.getVolume());
        
        tuxing= new Circle(10);
        System.out.println("半径是10的圆的面积:"+tuxing.getArea());
        
        pillar.changeBottom(tuxing);
        System.out.println("圆形柱体的体积:"+pillar.getVolume());
	}

}
