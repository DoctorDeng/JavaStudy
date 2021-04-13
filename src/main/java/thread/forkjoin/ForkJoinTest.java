package thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 *  练习 JDK 1.7 新增的 Fork/Join 框架
 * @author <a href="http://doctordeng.vip/">DoctorDeng</a>
 * @since 2018年11月23日
 */
public class ForkJoinTest {
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CountTask.class);
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// 生成一个计算任务，负责计算 1 + 2 + 3 + 4
		CountTask countTask = new CountTask(1, 4);
		// 执行一个任务
		Future<Integer> result = forkJoinPool.submit(countTask);
		
		if (countTask.isCompletedAbnormally()) {
			System.out.println(countTask.getException().getMessage());
			log.error(countTask.getException().getMessage(), countTask.getException());
		}
		
		try {
			System.out.println(result.get());
		} catch (InterruptedException | ExecutionException e) {
			//e.printStackTrace();
		}
	}

} 
// recursive : 回归的、递归的
class CountTask extends RecursiveTask<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2884067762236351099L;
	// threshold : 门槛、阀值
	private static final int THRESHOLD = 2;
	private int start;
	private int end;
	
	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Integer compute() {
		int sum = 0;
		
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				//sum += i;
				sum = sum + i;
				throw new RuntimeException("aaa");
			}
			
		} else {
			// 如果任务大于阀值, 就分裂成两个子任务计算
			int middle = (start + end) / 2;
			CountTask leftTask  = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);
			
			// 执行子任务
			leftTask.fork();
			rightTask.fork();
			
			// 等待子任务执行完成并得到结果
			int leftResult  = leftTask.join();
			int rightResult = rightTask.join();
			// 合并子任务
			sum = leftResult + rightResult;
		}
		
		return Integer.valueOf(sum);
	}
}
