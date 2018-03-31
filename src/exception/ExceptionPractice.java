package exceptionPractice;
/*
 * Description:   练习异常处理
 * Author:        Doctor邓
 * Time:          2016-4-18
 */

class NoLowerLetter extends Exception{
	public void print(){
		System.out.printf("%c",'#');
	}
}

class NoDigit extends Exception{
	public void print(){
		System.out.printf("%c", '*');
	}
}

class People{
	void printLetter(char c) throws NoLowerLetter{
		if(c < 'a' || c > 'z'){
			NoLowerLetter noLowerLetter = new NoLowerLetter();
			throw(noLowerLetter);
		}
		else{
			System.out.print(c);
		}
	}
	
	void printDigit(char c) throws NoDigit{
		if(c < '1' || c > '9'){
			NoDigit noDigit = new NoDigit();
			throw(noDigit);
		}
		else{
			System.out.print(c);
		}
	}
}

public class ExceptionPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        
		People people = new People();
		for(int i = 0;i< 128; i++){
			try{
				people.printLetter((char)i);
			}
			catch (NoLowerLetter e){
				e.print();
			}
		}
		
		for(int i= 0; i< 128; i++){
			try{
				people.printDigit((char)i);
			}
			catch (NoDigit e){
				e.print();
			}
		}
	}

}
