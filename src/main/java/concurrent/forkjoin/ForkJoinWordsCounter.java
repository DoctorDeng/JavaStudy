package concurrent.forkjoin;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 练习使用 Fork/Join 统计单词数量.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/8/14 16:22
 */
public class ForkJoinWordsCounter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoinWordsCounter.class);

    /**
     * 统计字符串数组中各个单词出现的次数.
     * @param lines 指定字符串数组, 其中的每个元素表示一行文本信息
     * @return 单词出现次数统计信息. key 为单词, value 为该单词出现次数
     */
    private static Map<String, Long> count(String[] lines) {
        if (ArrayUtils.isEmpty(lines)) {
            return Maps.newHashMap();
        }
        // 1. 创建 ForkJoin 线程池.
        ForkJoinPool pool = new ForkJoinPool(3);
        // 2. 创建任务.
        WordsCounter counter = new WordsCounter(lines, 0, lines.length);
        // 3. 启动任务计算结果.
        return pool.invoke(counter);
    }

    public static void main(String[] args) {
        String[] lines = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"};
        Map<String, Long> result = count(lines);
        LOGGER.info("word count result is: {}", result);
    }

    static class WordsCounter extends RecursiveTask<Map<String, Long>> {

        private final String[] lines;

        private final int start;

        private final int end;

        public WordsCounter(String[] lines, int start, int end) {
            this.lines = lines;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Map<String, Long> compute() {
            if (ArrayUtils.isEmpty(lines)) {
                return Maps.newHashMap();
            }

            if (end - start == 1) {
                return count(lines[start]);
            }
            // 可以使用 task1.fork(); task2.fork();  task2.join(); task1.join(); 但是要注意 fork 和 join 顺序否则会导致性能下降.
            int mid = (start + end) / 2;
            WordsCounter task1 = new WordsCounter(lines, start, mid);
            task1.fork();

            WordsCounter task2 = new WordsCounter(lines, mid, end);
            return merge(task2.compute(), task1.join());
        }

        private Map<String, Long> merge(Map<String, Long> a1, Map<String, Long> a2) {
            Map<String, Long> mergeResult = new HashMap<>( Math.max(a1.size(), a2.size()));
            mergeResult.putAll(a1);
            a2.forEach((k, v) -> {
                merge(mergeResult, k, v);
            });
            return mergeResult;
        }

        private Map<String, Long> count(String line) {
            if (StringUtils.isEmpty(line)) {
                return Maps.newHashMap();
            }
            // 分隔单词.
            String[] words = line.split("\\s+");
            Map<String, Long> result = new HashMap<>(words.length);
            for (String word: words) {
                merge(result, word);
            }
            return result;
        }

        private void merge(Map<String, Long> result, String word) {
            merge(result, word, 1L);
        }

        private void merge(Map<String, Long> countResult, String word, Long count) {
            countResult.merge(word, count, Long::sum);
        }
    }

}
