package getVolume;
/*
 * Description:  计算各种柱体体积
 */
public class Pillar {
	Geometry bottom;         //将Geometry对象作为成员
    double height;           //柱体高
    
    Pillar(Geometry bottom,double height){
    	this.bottom= bottom;
    	this.height= height;
    }
    
    //改变柱体对象
    void changeBottom(Geometry bottom){
    	this.bottom=bottom;
    }
    
    public double getVolume(){
    	return bottom.getArea()*height;          //返回柱体体积
    }
    
}
