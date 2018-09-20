package annotation;

import foundationEnhancement.Color;
import foundationEnhancement.ColorAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 练习注解
 *
 * @author Doctor邓
 * @since 2016/11/27 14:59
 */

/**
 * Retention：指示注解类型的注解要保留多久。如果注解类型声明中不存在 Retention 注解，则保留策略默认为 RetentionPolicy.CLASS。
 * 只有元注解类型直接用于注解时，Target 元注解才有效。如果元注解类型用作另一种注解类型的成员，则无效。
 *

 @Target(ElementType.ANNOTATION_TYPE)
 public @interface MetaAnnotationType {
 ... 
 }
 此元注解指示该声明类型只可作为复杂注解类型声明中的成员类型使用。它不能直接用于注解： 
 @Target({})
 public @interface MemberType {
 ...
 }
 这是一个编译时错误，它表明一个 ElementType 常量在 Target 注解中出现了不只一次。例如，以下元注解是非法的： 
 @Target({ElementType.FIELD, ElementType.METHOD, ElementType.FIELD})
 public @interface Bogus {
 ...
 }

 */
@Retention(RetentionPolicy.RUNTIME) // 注解上的注解, 元注解
@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface AAnnotation {
    String color();
    int age() default 10; //为属性赋默认值, 这样在使用注解时不用强制填写其值
    String[] ids() default {"1","2","3"};
    Color enumColor() default  Color.GREEN;
    int value() default 0; // 使用 value, 在使用如下注解时不用使用 xxx = xxx;
    ColorAnnotation colorAccntation();
}
