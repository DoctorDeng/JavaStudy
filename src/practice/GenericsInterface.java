package practice;
/*
 * Description:  练习Interface(接口)与泛型(Generics)的使用
 */
interface IHumanOne<E,F>{
	public abstract void speak(E e,F f);
}

class People<E,F> implements IHumanOne<E,F>{
	public void speak(E e,F f){
		System.out.println(e.toString());
		System.out.println(f.toString());
	}
}

class ChineseOne{
	public String toString(){
		return "我是中国人,我会说汉语！";
	}
}

class Amercian{
	public String toString(){
		return "我是美国人,我会说英语！";
	}
}


public class GenericsInterface {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        People<ChineseOne,Amercian> people= new People<ChineseOne,Amercian>();
        ChineseOne chinese= new ChineseOne();
        Amercian amercian= new Amercian();
        people.speak(chinese, amercian);
	}

}
