package foundationEnhancement.classLoaderTest;

import java.io.*;
import java.util.Date;

/**
 * Description: 练习 自定义类加载器
 *
 * @author DoctorDeng
 * @since 2016/11/30 21:31
 */
public class CustomClassLoader extends ClassLoader{
    private String classDir;
    // 覆盖父类的加载类的方法
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classFileName = classDir + "\\" + name + ".class";
        try(FileInputStream fis = new FileInputStream(classFileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // 解密加密的 .class 文件
            cpyher(fis, baos);
            byte[] bytes = baos.toByteArray();
            return defineClass(bytes, 0, bytes.length); // 将一个 解密后的 byte 数组转换为 Class 类的实例。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(classFileName);
    }
    public CustomClassLoader() {}
    public CustomClassLoader(String classDir) {
        this.classDir = classDir;
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
        //加密文件
        //encryptFile("bin/foundationEnhancement/classLoaderTest/ClassLoaderAttachment.class",
          //     "src/foundationEnhancement/classLoaderTest/test/ClassLoaderAttachment.class");
        // 解密并使用自定义的类加载器加载类
        try {
            // 使用类加载器加载类, 获取 Class 对象
            Class cla = new CustomClassLoader("bin/foundationEnhancement/classLoaderTest").loadClass("ClassLoaderAttachment");
            /**
             * 通过 Class 对象创建对象实例, 由于这里的测试类的 class 文件被加密文件覆盖所以无法直接创建测试类对象
             * 所以由父类引用指向子类对象
             */
            Date date = (Date) cla.newInstance();
            System.out.println(date.toString());
            ClassLoaderTest.printClassLoaderName(date);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
