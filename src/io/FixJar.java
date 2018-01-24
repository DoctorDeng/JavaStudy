package io;
import java.io.File;
import java.util.Scanner;

import com.nstc.ini.RemoveRepeatJars;

public class FixJar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("====== ������ģ�鲿��λ�ø�Ŀ¼·�� ======");
			if (sc.hasNext()) {
				String path = sc.nextLine();
				if (path == null || path.trim().length() == 0
						|| !(new File(path.trim()).exists())) {
					System.out.println("����:Ŀ¼������");
					continue;
				}
				fixJar(path);
				System.out.println("------------�������----------\r\n");
			}
		}

	}
	private static void fixJar(String path) {
        RemoveRepeatJars rrj = new RemoveRepeatJars();
        rrj.setRemove(true);
        rrj.setDir(path);
        try {
        	rrj.execute();
		} catch (Exception e) {
			System.out.println("���棺�ظ� jar ��������");
			e.printStackTrace();
		}
	}
}
