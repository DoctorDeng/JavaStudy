package io;
import java.io.File;
import java.util.Scanner;

import com.nstc.ini.RemoveRepeatJars;

public class FixJar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("====== 请输入模块部署位置根目录路径 ======");
			if (sc.hasNext()) {
				String path = sc.nextLine();
				if (path == null || path.trim().length() == 0
						|| !(new File(path.trim()).exists())) {
					System.out.println("警告:目录不存在");
					continue;
				}
				fixJar(path);
				System.out.println("------------处理完成----------\r\n");
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
			System.out.println("警告：重复 jar 包检测出错");
			e.printStackTrace();
		}
	}
}
