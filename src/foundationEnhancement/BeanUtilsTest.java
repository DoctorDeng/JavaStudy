package foundationEnhancement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 练习使用 BeanUtils 工具包来操作 JavaBean
 *
 * @author Doctor邓
 * @since 2016/11/26 21:02
 */
public class BeanUtilsTest {

    public static void beanUtilsTest() {
        Student student = new Student("Doctor邓", 20, true);
        try {
            // 获取属性值
            System.out.println(BeanUtils.getProperty(student, "age"));
            //设置属性值
            BeanUtils.setProperty(student, "age", 30);
            System.out.println(student.getAge());
            // 使用 BeanUtils 设置复合属性
            // 设置 Student 中的 Date 成员变量的 time 属性
            BeanUtils.setProperty(student, "birth.time", "222");
            System.out.println(BeanUtils.getProperty(student, "birth.time"));

            // BeanUtils 支持 Map 设置 value
            Map map = new HashMap();
            map.put("name","Doctor");
            BeanUtils.setProperty(map,"name","Doctor邓");
            System.out.println(map.get("name"));

            // PropertyUtils 不会进行类型转换
            PropertyUtils.setProperty(student, "age", 40);
            System.out.println(student.getAge());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        beanUtilsTest();
    }
}
