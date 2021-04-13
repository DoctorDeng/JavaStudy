package util;

public class Convert {
   
	/*
	 * int转换为byte数组
	 */
	public static byte[] int2Bytes(int id) {
		byte[] arr = new byte[4];
		arr[0] = (byte)((int)(id >> 0*8) & 0xff);
		arr[1] = (byte)((int)(id >> 1*8) & 0xff);
		arr[2] = (byte)((int)(id >> 2*8) & 0xff);
		arr[3] = (byte)((int)(id >> 3*8) & 0xff);
		return arr;
	}
	
	/*
	 * byte数组转换为int
	 */
	public static int byte2Int(byte[] arr){
		int rs0 = (int)((arr[0] & 0xff) << 0*8);
		int rs1 = (int)((arr[1] & 0xff) << 1*8);
		int rs2 = (int)((arr[2] & 0xff) << 2*8);
		int rs3 = (int)((arr[3] & 0xff) << 3*8);
		return rs0 + rs1 + rs2 + rs3;
	}
	
	public static void main(String[] args) {
		byte[] arr =Convert.int2Bytes(8143);
		for (byte temp : arr) {
			System.out.print(temp+ ",");
		}
		System.out.println("\n" + Convert.byte2Int(arr));
		
		//字符串与字节数组
		String describe = "我每天都练功，我要天下无敌......";
		byte[] barr = describe.getBytes();
		String des = new String(barr);
		System.out.println(des);
	}
}
