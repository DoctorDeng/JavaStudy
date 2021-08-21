package annotation;

import foundationEnhancement.ColorAnnotation;

import java.util.Arrays;

/**
 * Description: 练习 Java 注解
 *
 * @author doctordeng
 * @since 2016/11/27 10:08
 */
@AAnnotation(color = "Doctor",ids = "1",colorAccntation = @ColorAnnotation("你好")) // 在数组只有一个值时可以不加 {} 号
public class AnnotationTest {
    // 使用反射获取注解的内容
    public static void reflectAnnotation() {
        Class<?> clas = AnnotationTest.class;
        //  如果指定类型的注解存在于此 Class 上，则返回 true，否则返回 false。
        if (clas.isAnnotationPresent(AAnnotation.class)) {
            AAnnotation aAnnotation = clas.getAnnotation(AAnnotation.class);
            System.out.println(aAnnotation.color());
            System.out.println(Arrays.toString(aAnnotation.ids()));
            System.out.println(aAnnotation.age());
            System.out.println(aAnnotation.enumColor());
            System.out.println(aAnnotation.colorAccntation());
        }
    }
    // 忽略警告
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        AnnotationA.sayHello();
        reflectAnnotation();
    }
}
class AnnotationA {
    // 声明方法已被废弃
    @Deprecated
    public static void sayHello() {
    }
    // 重写方法
    @Override
    public String toString() {
        return super.toString();
    }
}
