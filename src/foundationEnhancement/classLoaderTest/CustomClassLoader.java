package foundationEnhancement.classLoaderTest;

import java.io.*;

/**
 * Description: 练习 自定义类加载器
 *
 * @author DoctorDeng
 * @since 2016/11/30 21:31
 */
public class CustomClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        return super.findClass(name);
    }

    /**
     * 加密、解密
     * @param ips
     * @param ops
     */
    private static void cpyher(InputStream ips, OutputStream ops){
        int b = -1;
        try {
            while ((b = ips.read()) != -1){
                ops.write(b ^ 0xff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  void main(String[] args) {
        encryptFile("src/foundationEnhancement/classLoaderTest/ClassLoaderAttachment.java",
                "src/foundationEnhancement/classLoaderTest/test/ClassLoaderAttachment.java");
    }

    public static void encryptFile(String srcPath, String tartgetPath) {
        try (FileInputStream fis  = new FileInputStream(new File(srcPath));
             FileOutputStream fos = new FileOutputStream(tartgetPath)){
            cpyher(fis,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream getInputrem(String path) {
        return CustomClassLoader.class.getClassLoader().getResourceAsStream(path);
    }
}
