package datastructureAndalgorithm.sort;

/**
 * 排序接口.
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/4/3 19:48
 * @since 1.0.0
 */
public interface Sort {
    /**
     * 对指定 int 类型数组进行排序.
     * @param array 待排序数组
     */
    void sort(int[] array);

    /**
     * 获取排序算法名称
     * @return 排序算法名称
     */
    String getName();
}
