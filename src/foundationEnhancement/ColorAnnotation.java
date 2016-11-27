package foundationEnhancement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description:
 *
 * @author Doctor邓
 * @since 2016/11/27 17:45
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ColorAnnotation {
    String value() default "注解 Color ";
}
