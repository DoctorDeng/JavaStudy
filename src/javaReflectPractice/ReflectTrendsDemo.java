package javaReflectPractice;

public class ReflectTrendsDemo {

	public static void main(String[] args) {
		try
		{
			//动态加载类，在运行时刻加载类
			Class c = Class.forName(args[0]);
			//通过类类型，创建该类对象
			OfficeAble oa = (OfficeAble)c.newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
