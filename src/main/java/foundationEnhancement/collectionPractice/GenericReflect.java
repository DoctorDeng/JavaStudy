package foundationEnhancement.collectionPractice;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Vector;

/**
 * Description: 泛型练习
 *     使用反射的方式获取泛型的具体类型
 *
 * @author doctordeng
 * @since 2016/11/29 21:45
 */
public class GenericReflect {
    public static void applyVector(Vector<Date> vector) {

    }

    /**
     * 通过反射是无法直接获取泛型的实际参数类型的, 这是因为在泛型只发生在编译阶段,
     * 编译后泛型的相关信息被抹除了, 但是我们可以根据使用到泛型的具体方法的参数列表
     * 来获取实际的参数类型
     */
    public static void getGenericType() {
        try {
            // 获取指定 Method 对象
            Method applyMethod = GenericReflect.class.getMethod("applyVector", Vector.class);
            /**
             *  按照声明顺序返回 Type 对象的数组，这些对象描述了此 Method 对象所表示的方法的形参类型的。
             *  如果底层方法不带参数，则返回长度为 0 的数组。
             *
             *  如果形参类型是参数化类型，则为其返回的 Type 对象必须实际反映源代码中使用的实际类型参数。
             *  如果形参类型是类型变量或参数化类型，则创建它。否则将解析它。
             */
            Type[] types =  applyMethod.getGenericParameterTypes();
            /**
             * 从参数 形参类型列表 中获取第一个参数的[参数化类型]
             */
            ParameterizedType pType = (ParameterizedType)types[0];
            /**
             * getRawType()：返回 Type 对象，表示声明此类型的类或接口。
             */
            System.out.println(pType.getRawType()); //输出获取参数类型
            /**
             * getActualTypeArguments()：返回表示此类型实际类型参数的 Type 对象的数组。
             *     即：Vector 集合中所装载的实际类型参数
             */
            System.out.println(pType.getActualTypeArguments()[0]); // 输出此类型实际类型参数
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        getGenericType();
    }
}
