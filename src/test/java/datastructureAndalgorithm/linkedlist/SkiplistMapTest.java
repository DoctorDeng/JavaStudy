package datastructureAndalgorithm.linkedlist;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

public class SkiplistMapTest {

    @Test
    public void put() {
        SkiplistMap<Integer, String> skiplistMap = new SkiplistMap<>(Integer::compareTo);
        for (int i = 10; i < 100; i++) {
            skiplistMap.put(i, String.valueOf(i));
        }
        for (int i = 10; i < 100; i++) {
            Assert.assertEquals(String.valueOf(i), skiplistMap.get(i));
        }
        Assert.assertEquals(90, skiplistMap.size());
    }

    @Test
    public void remove() {
        SkiplistMap<Integer, String> skiplistMap = new SkiplistMap<>(Integer::compareTo);
        for (int i = 10; i <= 100; i++) {
            skiplistMap.put(i, String.valueOf(i));
        }
        for (int i = 10; i <= 100; i++) {
            Assert.assertEquals(String.valueOf(i), skiplistMap.remove(i));
        }
        Assert.assertTrue(skiplistMap.isEmpty());
    }

    @Test
    public void entrySet() {
        SkiplistMap<Integer, String> skiplistMap = new SkiplistMap<>(Integer::compareTo);
        for (int i = 10; i <= 100; i++) {
            skiplistMap.put(i, String.valueOf(i));
        }
        Iterator<Map.Entry<Integer, String>> iterator = skiplistMap.entrySet().iterator();
        int i = 10;
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            Integer key = entry.getKey();
            String  value = entry.getValue();
            Assert.assertEquals(Integer.valueOf(i), key);
            Assert.assertEquals(String.valueOf(i), value);
            System.out.println("key=" + entry.getKey() + " value=" + entry.getValue());
            i++;
        }
    }
}