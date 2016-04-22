package equalsPractice;
/*
 * Description:  重写equals()方法
 */
public class EqualsOverwrite {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Cat cat1 = new Cat(1,1,1);
		Cat cat2 = new Cat(1,1,1);
		System.out.println(cat1.equals(cat2));

	}

}
class Cat{
	int color;
	int height;
	int weight;
	
	public Cat(int color, int height, int weight){
		this.color = color;
		this.height = height;
		this.weight = weight;
	}
	
		//重写equals()方法，使其能够比较两个对象的内容是否相同
				public boolean equals(Object obj){
					if(obj == null){
						return false;
					}
					else{
						if( obj instanceof Cat){
							Cat c = (Cat)obj;
							if(c.color == this.color && c.height == this.height  &&  c.weight == this.weight){
								return true;
							}
						}
					}
					return false;
				}

}
