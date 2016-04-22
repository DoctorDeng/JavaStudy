package polymorphic;
/*
 * Description:  练习多态
 * 
 */
class Animal{
	private String name;
	
	Animal(String name){
		this.name = name;
	}
	
	public void speak(){
		System.out.println("我是动物,我会叫");
	}
}

class Cat extends Animal{
	private String eyesColor;
	Cat(String name,String eyesColor){
		super(name);
		this.eyesColor = eyesColor;
	}
	
	public void speak(){
		System.out.println("我是猫,我会喵喵");
	}
}

class Dog extends Animal{
	private String hairColor;
	Dog(String name, String hairColor){
		super(name);
		this.hairColor = hairColor;
	}
	
	public void speak(){
		System.out.println("我是狗,我会嗷嗷");
	}
}
class Person{
	private String name;
	private Animal pet;
	
	Person(String name,Animal pet){
		this.name = name;
		this.pet = pet;
	}
	
	public void myPetSpeak(){
		pet.speak();
	}
}

public class Polymorphic {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Cat cuancuan = new Cat("川川","blue");
        Dog wangyimei = new Dog("汪一梅","yellow");
        Person doctor = new Person("Doctor",cuancuan);
        Person doctor1 = new Person("Doctor1",wangyimei);
        doctor.myPetSpeak();
        doctor1.myPetSpeak();
	}

}
