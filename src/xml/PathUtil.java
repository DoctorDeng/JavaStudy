package xml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class PathUtil {
	private static PathUtil pathUtil = new PathUtil();
	private PathUtil(){}
	/**
	 * 根据路径获取指定文件的输入流
	 * @param path 文件路径从项目根路径开始
	 * @return
	 */
	public static InputStream getFileInputStream(String path){
		return pathUtil.getClass().getClassLoader().getResourceAsStream(path);
	}
	/**
	 * 获取指定文件指定 编码格式 的InputStreamReader 流
	 * @param path 
	 * @param encode  编码格式
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static InputStreamReader getFileInputStreamReder(String path, String encode) throws UnsupportedEncodingException {
		if (null == encode || "".equals(encode)) {
			encode = "utf-8";
		}
		
		return new InputStreamReader(getFileInputStream(path), encode);
	}
}
