package string;
/*
 * Description:   将用户在键盘输入的字符串加密，然后输出密文
 */

import java.util.Scanner;
public class StringEncryption {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
        String s = reader.nextLine();
        char a[] = s.toCharArray();
        for(int i=0; i<a.length; i++){
        	a[i] =(char)(a[i]^'W');
        }
        
        String secret = new String(a);
        System.out.println("密文:"+secret);
        for(int i=0;i<a.length;i++){
        	a[i]=(char)(a[i]^'W');
        }
        
        String code = new String(a);
        System.out.println("原文："+code);
        System.out.println("111");
        reader.close();
        
	}

}
