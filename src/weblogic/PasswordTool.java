package weblogic;

import java.util.Scanner;

import weblogic.security.internal.SerializedSystemIni;
import weblogic.security.internal.encryption.ClearOrEncryptedService;
import weblogic.security.internal.encryption.EncryptionService;

public class PasswordTool {
	private static EncryptionService es = null;
	private static ClearOrEncryptedService ces = null;

	static {
		es = SerializedSystemIni.getExistingEncryptionService();
		ces = new ClearOrEncryptedService(es);
	}

	private PasswordTool () {}
	
	private static String decrypt(String despassword) {
		return ces.decrypt(despassword);
	}

	private static String encrypt(String password) {
		return ces.encrypt(password);
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("[提示] 使用此工具前请将 SerializedSystemIni.dat 文件放入 security 目录下");
			System.out.println("1.加密");
			System.out.println("2.解密");
			if (sc.hasNext()) {
				String input = sc.nextLine();
				System.out.println("------> 请输入 (加密/解密) 密码  <------");
				String password = sc.nextLine();
				if (password == null || password.trim().length() == 0) {
					System.out.println("[警告] 密码不能为空");
					continue;
				}
				
				if ("1".equals(input)) {
					System.out.println("加密后：" + encrypt(password));
				} 
				
				if ("2".equals(input)) {
					System.out.println("解密后：" + decrypt(password));
				}
				System.out.println("========================================================================");
			}
		}
	}
}