package practice;
/*
 * Description:  联系泛型和泛型接口
 */

class Chrous<E,F>{
	void makeChrous(E person,F yueqi){
		person.toString();
		yueqi.toString();
	}
}
class Person{
	public String toString(){
		System.out.println("我是歌手,我会唱歌!");
		return "";
	}
}

class YueQi{
	public String toString(){
		System.out.println("我是乐器,我会弹奏!");
	    return "";
	}
}

public class Generics {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//验证泛型
		Chrous<Person,YueQi> chrous= new Chrous<Person,YueQi>();
		Person person= new Person();
		YueQi yueQi= new YueQi();
		chrous.makeChrous(person, yueQi);
	}

}
