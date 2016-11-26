package foundationEnhancement;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description: 练习 Java Bean
 *
 * @author Doctor邓
 * @since 2016/11/26 17:13
 */
public class JavaBeanTest {

    private static void beanTest() {
        Student student = new Student("Doctor邓", 20, true);
        System.out.println(getProperties("name", student));
        setProperties("name", "DoctorDeng", student);
        System.out.println(student.getName());
    }

    private static void setProperties(String parameterName, Object setValue, Object target) {
        try {
            // 通过 Class 和 属性名称 构造一个 PropertyDescriptor
            PropertyDescriptor pd = new PropertyDescriptor(parameterName, target.getClass());
            // 从 PropertyDescriptor 中获得应该用于写入属性值的方法
            Method writeMethod = pd.getWriteMethod();
            // 执行写入属性方法设置属性的值
            writeMethod.invoke(target, setValue);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static Object getProperties(String parameterName, Object target) {
        Object result = null;
        try {
            // 通过 Class 和 属性名称 构造一个 PropertyDescriptor
            PropertyDescriptor pd = new PropertyDescriptor(parameterName, target.getClass());
            // 从 PropertyDescriptor 中获得应该用于读取属性值的方法
            Method readMethod  = pd.getReadMethod();
            // 执行读取属性方法, 获取属性值
            result = readMethod.invoke(target);
        } catch (IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        beanTest();
    }
}

