package foundationEnhancement;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description: 练习使用 BeanInfo 来操作 JavaBean
 *
 * @author Doctor邓
 * @since 2016/11/26 20:31
 */
public class BeanInfoTest {
    public static Object getProperties(String parameterName, Object target) {
        Object result = null;

        if (null == parameterName || parameterName.length() == 0) {
            return result;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                if (pd.getName().equals(parameterName)) {
                    Method readMethod = pd.getReadMethod();
                    result = readMethod.invoke(target);
                    break;
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        Student student = new Student("Docotr", 20, true);
        System.out.println(getProperties("age",student));
    }
}
