package immutable;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description 练习 final 修饰 MemberVariable(成员变量)
 * @since 2018/9/19 11:18
 */
public class FinalMemberVariable {
    // static final 类型可以在声明时初始化比如： static final char value[] = {'1','2'}; 或 static {} 语句块中初始化
    // 不加 final 可以在构造方法或 {} 语句块中初始化, 无法在 static {} 块中初始化
    private final char value[];
    private static final char valueStatic[];

    static {
        valueStatic = new char[]{'1','2','3'};
        // 如下代码错误，使用这种方式必须是在定义数组时即 char valueStatic[] = {'1','2'}
        // valueStatic = {'1'};
    }

/*    {
        this.value = new char[]{'1','2','3'};;
        System.out.println("{}");
    }*/

    public FinalMemberVariable(char value[]) {
        this.value = value;
        System.out.println("FinalMemberVariable()");
    }
    // 在构造方法中可以访问同类型参数的私有方法
    public FinalMemberVariable(FinalMemberVariable finalMemberVariable) {
        this.value = finalMemberVariable.value;
    }


    public static void main(String[] args) {
        char[] a = {'1','2','3'};
        FinalMemberVariable aFinal = new FinalMemberVariable(a);
    }

}
