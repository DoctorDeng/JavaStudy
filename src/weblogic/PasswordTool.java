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
			System.out.println("[��ʾ] ʹ�ô˹���ǰ�뽫 SerializedSystemIni.dat �ļ����� security Ŀ¼��");
			System.out.println("1.����");
			System.out.println("2.����");
			if (sc.hasNext()) {
				String input = sc.nextLine();
				System.out.println("------> ������ (����/����) ����  <------");
				String password = sc.nextLine();
				if (password == null || password.trim().length() == 0) {
					System.out.println("[����] ���벻��Ϊ��");
					continue;
				}
				
				if ("1".equals(input)) {
					System.out.println("���ܺ�" + encrypt(password));
				} 
				
				if ("2".equals(input)) {
					System.out.println("���ܺ�" + decrypt(password));
				}
				System.out.println("========================================================================");
			}
		}
	}
}