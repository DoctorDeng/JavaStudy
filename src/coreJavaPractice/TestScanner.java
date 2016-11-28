package coreJavaPractice;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import util.PathUtil;

/**
 * <blockquote><pre>{@code
 *     Scanner sc = new Scanner(System.in);
 *     int i = sc.nextInt();
 * }</pre></blockquote>
 * @author Doctor邓
 *
 */
public class TestScanner {
	
	public void test() {
		TestScanner aaa = this;
	}
	
	public static void testScanner() {
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		String firstName = in.next();
		//String lastName  = in.next();
		System.out.println(name + "----");
		System.out.println(firstName + "----");
		//System.out.println(lastName + "----");
	}
	
	public static void testConsole() {
		Console cons    = System.console();
		
		if (null == cons) {
			System.out.println("无法获取 Console 对象");
			System.exit(0);
		}
		
		String userName = cons.readLine("名称是: ");
		char[] passwd   = cons.readPassword("密码是: ");
		System.out.println("用户名: " + userName);
		System.out.println("密码: " + passwd.toString());
	}
	/**
	 * 练习使用 Scanner 读取文件内容
	 */
	public static void testScannerFile() throws IOException {
		Scanner scanner = new Scanner(PathUtil.getFileInputStream("coreJavaPractice/111.sql"), "UTF-8");
		while(scanner.hasNext()) {
			System.out.println(scanner.nextLine());
		}
	}
	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		try {
			testScannerFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
