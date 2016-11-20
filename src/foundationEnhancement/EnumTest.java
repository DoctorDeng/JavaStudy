package foundationEnhancement;

import java.util.Enumeration;

/**
 * 练习 Java 枚举 Enum
 * Created by Doctor邓 on 2016/11/20.
 */
public class EnumTest {
    public static void main(String[] args) {
        WeekDay mon = WeekDay.Mon;
        WeekDay thur = WeekDay.Tues;
        //valueOf() 将指定字符串转换为枚举对象
        System.out.println(WeekDay.valueOf("Wed") == WeekDay.Wed);
    }
}

enum WeekDay {
    Mon, Tues, Wed, Thur, Fri, Sat, Sun
}
enum Sex {
    Men("男",1),Women("女",2);
    private String name;
    private Integer indtify;



}
