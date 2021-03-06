package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemTool {
	private static String macAddressCache = null;
	/**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.
	 * 
	 * @return mac地址
	 */
	private static String getUnixMACAddress() {
		String mac = getUnixMAXAddress("UTF-8");
		if (null == mac || mac.length() == 0) {
			mac = getUnixMAXAddress("GBK");
		}
		if (null != mac && mac.length() > 0) {
			mac = mac.replace(":","-");
		}
		return mac;
	}

	private static String getUnixMAXAddress(String charsetName) {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			// ifconfig eth0
			process = Runtime.getRuntime().exec("ifconfig");// linux下的命令，一般取eth0作为本地主网卡
			// 显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), charsetName));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]
				if (index >= 0) {// 找到了
					mac = line.substring(index + "hwaddr".length() + 1).trim();// 取出mac地址并去除2边空格
					break;
				}

				index = line.toLowerCase().indexOf("硬件地址");
				if (index >= 0) {// 找到了
					mac = line.substring(index + "硬件地址".length() + 1).trim();// 取出mac地址并去除2边空格
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}
	private static String getWindownsMACAddress(String charsetName){
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), charsetName));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical
				// address]
				// 英文操作系统
				if (index >= 0) {// 找到了
					index = line.indexOf(":");// 寻找":"的位置
					if (index >= 0) {
						mac = line.substring(index + 1).trim();// 取出mac地址并去除2边空格
					}
					break;
				}
				// 中文操作系统
				index = line.toLowerCase().indexOf("物理地址");
				if (index >= 0) {// 找到了
					index = line.indexOf(":");// 寻找":"的位置
					if (index >= 0) {
						mac = line.substring(index + 1).trim();// 取出mac地址并去除2边空格
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return  mac;
	}
	/**
	 * 获取widnows网卡的mac地址.
	 * 
	 * @return mac地址
	 */
	private static String getWindowsMACAddress() {
		String mac = getWindownsMACAddress("GBK");
		if (null == mac || mac.length() == 0) {
			mac = getWindownsMACAddress("UTF-8");
		}
		return mac;
	}
	public static String getMacAddress() {
		if (macAddressCache == null || macAddressCache.length() == 0) {
			final String os = getOSName();
			String mac = null;
			if (os.startsWith("windows")) {
				mac = getWindowsMACAddress();
			} else {
				mac = getUnixMACAddress();
			}
			cacheMacAddress(mac);
			return mac;
		}
		
		return macAddressCache;
	}
	private static synchronized  void cacheMacAddress(String macAddress) {
		if (macAddressCache == null || macAddressCache.length() == 0) {
			macAddressCache = macAddress;
		} 
	}
	/**
	 * 测试用的main方法.
	 * 
	 * @param argc
	 *            运行参数.
	 */
	public static void main(String[] argc) {
/*		System.out.println(System.getProperty("file.encoding"));
		System.out.println(Charset.defaultCharset());*/
		String os = getOSName();
		System.out.println(os);
		if (os.startsWith("windows")) {
			// 本地是windows
			String mac = getWindowsMACAddress();
			System.out.println(mac);
		} else {
			// 本地是非windows系统 一般就是unix
			String mac = getUnixMACAddress();
			System.out.println(mac);
		}
	}
}