package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemTool {
	private static String macAddressCache = null;
	/**
	 * ��ȡ��ǰ����ϵͳ����. return ����ϵͳ���� ����:windows xp,linux ��.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * ��ȡunix������mac��ַ. ��windows��ϵͳĬ�ϵ��ñ�������ȡ.���������ϵͳ����������µ�ȡmac��ַ����.
	 * 
	 * @return mac��ַ
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
			process = Runtime.getRuntime().exec("ifconfig");// linux�µ����һ��ȡeth0��Ϊ����������
			// ��ʾ��Ϣ�а�����mac��ַ��Ϣ
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), charsetName));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("hwaddr");// Ѱ�ұ�ʾ�ַ���[hwaddr]
				if (index >= 0) {// �ҵ���
					mac = line.substring(index + "hwaddr".length() + 1).trim();// ȡ��mac��ַ��ȥ��2�߿ո�
					break;
				}

				index = line.toLowerCase().indexOf("Ӳ����ַ");
				if (index >= 0) {// �ҵ���
					mac = line.substring(index + "Ӳ����ַ".length() + 1).trim();// ȡ��mac��ַ��ȥ��2�߿ո�
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
			process = Runtime.getRuntime().exec("ipconfig /all");// windows�µ������ʾ��Ϣ�а�����mac��ַ��Ϣ
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), charsetName));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("physical address");// Ѱ�ұ�ʾ�ַ���[physical
				// address]
				// Ӣ�Ĳ���ϵͳ
				if (index >= 0) {// �ҵ���
					index = line.indexOf(":");// Ѱ��":"��λ��
					if (index >= 0) {
						mac = line.substring(index + 1).trim();// ȡ��mac��ַ��ȥ��2�߿ո�
					}
					break;
				}
				// ���Ĳ���ϵͳ
				index = line.toLowerCase().indexOf("�����ַ");
				if (index >= 0) {// �ҵ���
					index = line.indexOf(":");// Ѱ��":"��λ��
					if (index >= 0) {
						mac = line.substring(index + 1).trim();// ȡ��mac��ַ��ȥ��2�߿ո�
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
	 * ��ȡwidnows������mac��ַ.
	 * 
	 * @return mac��ַ
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
	 * �����õ�main����.
	 * 
	 * @param argc
	 *            ���в���.
	 */
	public static void main(String[] argc) {
/*		System.out.println(System.getProperty("file.encoding"));
		System.out.println(Charset.defaultCharset());*/
		String os = getOSName();
		System.out.println(os);
		if (os.startsWith("windows")) {
			// ������windows
			String mac = getWindowsMACAddress();
			System.out.println(mac);
		} else {
			// �����Ƿ�windowsϵͳ һ�����unix
			String mac = getUnixMACAddress();
			System.out.println(mac);
		}
	}
}