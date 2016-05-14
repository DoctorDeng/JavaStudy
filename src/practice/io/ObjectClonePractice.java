package practice.io;
/**
 * 练习对象的克隆clone
 */
import java.io.*;

class Goods implements Serializable {
	String name = null;
	
	Goods (String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

class Shop implements Serializable {
	Goods[] goods;

	public Goods[] getGoods() {
		return goods;
	}

	public void setGoods(Goods[] goods) {
		this.goods = goods;
	}
}


public class ObjectClonePractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Shop shop = new Shop();
		Goods[] s1 ={new Goods("TV"), new Goods("PC")};
		shop.setGoods(s1);
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			objectOut.writeObject( shop);
			ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
			ObjectInputStream objectIn = new ObjectInputStream(in);
			
			Shop shop2 = (Shop)objectIn.readObject();
			Goods[] good1 = shop.getGoods();
			Goods[] good2 = shop2.getGoods();
			System.out.println("shop中的商品: ");
			for(Goods good : good1) {
				System.out.println(good.getName());
			}
			
			System.out.println("shop2是shop的一个克隆,shop2中的商品: ");
			for(Goods good : good2) {
				System.out.println(good.getName());
			}
			
			Goods[] s2 = {new Goods("棉花"), new Goods("西服"), new Goods("篮球")};
			shop2.setGoods(s2);
			
			good1 = shop.getGoods();
			good2 = shop2.getGoods();
			System.out.println("目前,shop2中的商品: ");
			for(Goods good : good2) {
				System.out.println(good.getName());
			}
			
			System.out.println("目前,shop中的商品: ");
			for(Goods good : good1) {
				System.out.println(good.getName());
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
