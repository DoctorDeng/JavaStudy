package util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * code from：https://stackoverflow.com/questions/6164167/get-mac-address-on-local-machine-with-java
 * 
 * @since 1.1.5
 */
public class NetUtil {
	public static final String ADDRESSTYPE_IP  = "ip";
	public static final String ADDRESSTYPE_MAC = "mac";
	
	public static String getAddress(String addressType) {
		String address = "";
		InetAddress lanIp = null;
		try {
			String ipAddress = null;
			Enumeration<NetworkInterface> net = null;
			net = NetworkInterface.getNetworkInterfaces();

			while (net.hasMoreElements()) {
				NetworkInterface element = net.nextElement();
				Enumeration<InetAddress> addresses = element.getInetAddresses();
				if (element.getHardwareAddress() != null) {
					System.out.println(element.getHardwareAddress().toString());
				}
				//System.out.println(String.valueOf(element.getHardwareAddress()));
				while (addresses.hasMoreElements() 
						&& element.getHardwareAddress().length > 0
						&& !isVMMac(element.getHardwareAddress())) {
					InetAddress ip = addresses.nextElement();
					if (ip instanceof Inet4Address) {
						if (ip.isSiteLocalAddress()) {
							ipAddress = ip.getHostAddress();
							lanIp     = InetAddress.getByName(ipAddress);
						}
					}
				}
			}

			if (lanIp == null) {
				return null;
			}

			if (ADDRESSTYPE_IP.equals(addressType)) {
				address = lanIp.toString().replaceAll("^/+", "");
			} else if (ADDRESSTYPE_MAC.equals(addressType)) {
				address = getMacAddress(lanIp);
			} else {
				throw new Exception("Specify \"ip\" or \"mac\"");
			}
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (SocketException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return address;
	}

	private static String getMacAddress(InetAddress ip) {
		String address = null;
		try {
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			address = sb.toString();

		} catch (SocketException ex) {
			ex.printStackTrace();
		}

		return address;
	}

	private static boolean isVMMac(byte[] mac) {
		if (null == mac) {
			return false;
		}
		
		byte invalidMacs[][] = { { 0x00, 0x05, 0x69 }, // VMWare
				{ 0x00, 0x1C, 0x14 }, // VMWare
				{ 0x00, 0x0C, 0x29 }, // VMWare
				{ 0x00, 0x50, 0x56 }, // VMWare
				{ 0x08, 0x00, 0x27 }, // Virtualbox
				{ 0x0A, 0x00, 0x27 }, // Virtualbox
				{ 0x00, 0x03, (byte) 0xFF }, // Virtual-PC
				{ 0x00, 0x15, 0x5D } // Hyper-V
		};

		for (byte[] invalid : invalidMacs) {
			if (invalid[0] == mac[0] && invalid[1] == mac[1] && invalid[2] == mac[2])
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(NetUtil.getAddress(ADDRESSTYPE_MAC));
	}
}
