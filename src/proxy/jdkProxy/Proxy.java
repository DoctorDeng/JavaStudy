package proxy.jdkProxy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

import proxy.InvocationHandler;

public class Proxy {
	public static Object newProxyInstance(Class infce,InvocationHandler h) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Windows地下换行符
		String rt = "\r\n";
		
		String methodStr = "";
		
		for (Method m:infce.getMethods()) {
			methodStr +=" @Override "+ rt+
			" public void "+m.getName()+"() { "+ rt+
			"try{" + rt +
			"Method md =" + infce.getName() + ".class.getMethod(\""+ m.getName() +"\");" +rt+
			" h.invoke(this,md);" + rt +
			"}catch(Exception e) { e.printStackTrace();}" +rt+
			" } ";
		}
		String str = 
	        "package proxy;"+ rt+
	        "import proxy.InvocationHandler;"+rt+
	        "import java.lang.reflect.Method;" +rt+
			"public class $Proxy implements "+infce.getName()+ "{ "+ rt+
			"private InvocationHandler  h; "+ rt+
			"public $Proxy(InvocationHandler h) { "+ rt+
				"super(); "+ rt+
				"this.h = h; "+ rt+
			"} "+ rt+
			 methodStr +rt+
			"} ";
		/**
		 * 获取当前项目根路径System.getProperty("user.dir")
		 */
		String finalname = System.getProperty("user.dir") + "/bin/proxy/$Proxy.java";
		File file = new File(finalname);
		/**
		 * 产生代理类的java文件
		 */
		FileUtils.writeStringToFile(file, str);
		/**
		 * 获取当前系统编译器
		 */
		JavaCompiler complier = ToolProvider.getSystemJavaCompiler();
		//文件管理者
		StandardJavaFileManager fileMgr = complier.getStandardFileManager(null, null, null);
		//获取文件
		Iterable units = fileMgr.getJavaFileObjects(finalname);
		//编译任务	
		CompilationTask t = complier.getTask(null, fileMgr, null, null, null, units);
		//进行编译
		t.call();
		fileMgr.close();
		//将class加载到内存中
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Class c = cl.loadClass("proxy.$Proxy");
		//使用构造器创建一个对象
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		return ctr.newInstance(h);
	}
}
