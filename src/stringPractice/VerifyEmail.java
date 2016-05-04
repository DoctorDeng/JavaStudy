package stringPractice;
/*
 * Description:  用于验证邮箱地址是否正确，同时练习String类方法的使用
 */
public class VerifyEmail {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        String fileName = "HelloWorld.java";
        
        String email = "laurenyang@imooc.com";
        
        int index = fileName.indexOf(".");
        
        String prefix = fileName.substring(index+1);
        System.out.println(prefix);
        if ( (fileName.contains("."))  && (index != 0) && 
        		(prefix.equals("java")) ) {
        	System.out.println("Java文件名正确");
        }
        else {
        	System.out.println("Java文件名无效");
        }
        
        int index2 = email.indexOf("@");
        
        int index3 = email.indexOf(".");
        
        if(index2 != -1 && index3 > index2) {
        	System.out.println("邮箱格式正确");
        }
        else {
        	System.out.println("邮箱格式无效");
        }
	}

}
