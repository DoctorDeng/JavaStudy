package coreJavaPractice;

import java.util.Arrays;

/**
 * 练习 Java 类型转换
 * @author Doctor邓
 */
public class CastTest {
	String a = "";
	String b = "";
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CastTest other = (CastTest) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}

	public void castTest() {
		Object obj = new Object();
		if (obj instanceof String) {
			String a = (String) obj;
		}
	}
	
	public static void main(String[] args) {
		if ("a" instanceof java.lang.String) {
			
		}
	}

}
