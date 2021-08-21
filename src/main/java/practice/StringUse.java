package practice;
/* 
 * Description:   练习String字符串类 的使用
 * Author:        doctordeng
 * Time:          2016-4-10
 */
public class StringUse {
    
	
	public static void main(String[] args) {
		
		//字符串定义练习
		String userName=new String("邓博士");
		System.out.println(userName);
		String userAge="12";
		System.out.println(userAge);
		char[] charArray={'1','2','3'};
		String userId1=new String(charArray);
		String userId2=new String(charArray,1,2);
		System.out.println(userId1);
		System.out.println(userId2);
		
		//字符串长度练习和数组长度
		String[] stringArray={"2","2","等滑稽"};
		String stringLength="2 3 邓";
		System.out.println("字符串长度: "+stringLength.length());
        System.out.println("字符串数组长度: "+stringArray.length);
        
        //字符串的比较
        String s1="aaa";
        String s2="aaa";
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        String s3=new String("aaa");
        String s4=new String("aaa");
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
        
        //查询字符串
        String ss1="blue bridge";
        String ss2="Blue bridge";
        System.out.println(ss1.charAt(1));
        System.out.println(ss1.length());
        System.out.println(ss1.indexOf("bridge"));
        System.out.println(ss2.indexOf("Bridge"));
        
        //subString字串
        String sub="blue bridge";
        System.out.println(sub.substring(4));
        System.out.println(sub.substring(1,7));
        
        //trim():去除前导和后导
        String tr1="  aaa   aaa  ";
        String tr2=" bbb";
        String tr3="ccc   ";
        System.out.println("tr1去除空格前"+tr1);
        System.out.println("tr2去除空格前"+tr2);
        System.out.println("tr3去除空格前"+tr3);
        System.out.println("tr1去除空格后"+tr1.trim());
        System.out.println("tr2去除空格后"+tr2.trim());
        System.out.println("tr3去除空格后"+tr3.trim());
        
        //字符串替换replace()
        String repl=" .2.3.4jjaaa";
        System.out.println("替换前: "+repl);
        System.out.println("将.替换为+且j替换为J后:"+(repl.replace("j", "J")).replace(".", "+"));
	    
        //大小写用法
        String uplow="aaaBBBCcC";
        System.out.println("未转换大小写前: "+uplow);
        System.out.println("全部转换为大写: "+uplow.toUpperCase());
        System.out.println("全部转换为小写: "+uplow.toLowerCase());
        
        //split():分割字符串
        String spli = "枯藤老树昏鸦 小桥流水人家 古道西风瘦马 夕阳西下 断肠人在天涯";
        String[] spliArray=spli.split(" ");
        System.out.println("分割前的结果为: "+spli);
        System.out.println("分割后的并用数组输出的结果为:");
        for(String a:spliArray){
        	System.out.println(a);
        }
	}

}
