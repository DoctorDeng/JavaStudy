package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 应用程序工具类.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/12/27 18:25
 */
public abstract class ApplicationUtils {

	private ApplicationUtils() {
	}

	public static boolean isProcessExited(Process process) {
		try {
			process.exitValue();
		} catch (IllegalThreadStateException e) {
			return false;
		}
		return true;
	}

	public static void restartApplication(String commandLineArgs, int waitSeconds) {
		if (commandLineArgs == null || commandLineArgs.length() == 0) {
			return;
		}
		String userDir = System.getProperty("user.dir");
		try {
			Process process = Runtime.getRuntime().exec(commandLineArgs, null, new File(userDir));
			printInputStreamAsync(process, process.getInputStream());
			printInputStreamAsync(process, process.getErrorStream());
			// 调用该方法线程会一直阻塞.
//			process.waitFor();
			TimeUnit.SECONDS.sleep(waitSeconds);
		} catch (IOException | InterruptedException e) {
			System.out.println("Restart Application Failed, the command line args is: " + commandLineArgs);
			e.printStackTrace();
		} finally {
			// 不管通过命令行重启结果如何最后都需要退出当前 JVM.
			Runtimes.exit(0, 15);
		}
	}

	public static void restartApplication(int waitSeconds) {
		String args = getCommandLineArgs();
		restartApplication(args, waitSeconds);
	}

	public static String getCommandLineArgs() {
		StringBuilder cmdline = new StringBuilder();
		List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
		cmdline.append("java ");
		for (String s : inputArguments) {
			cmdline.append(s).append(" ");
		}
		String classPath = ManagementFactory.getRuntimeMXBean().getClassPath();
		if (classPath.endsWith(".jar")) {
			cmdline.append("-jar ");
		} else {
			cmdline.append("-cp ").append(classPath).append(" ");
		}
		cmdline.append(System.getProperty("sun.java.command"));
		return cmdline.toString();
	}

	private static void printInputStream(Process process, InputStream inputStream) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			while (!isProcessExited(process)) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}
		}
	}

	private static void printInputStreamAsync(Process process, InputStream inputStream) {
		Runnable inputStreamPrinter = () -> {
			try {
				printInputStream(process, inputStream);
			} catch (IOException ignore) {
				// ignore.
			}
		};
		new Thread(inputStreamPrinter).start();
	}
}
