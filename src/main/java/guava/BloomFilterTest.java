package guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description 练习 Guava 内建布隆过滤器
 * @since 2018/7/3 20:12
 */
public class BloomFilterTest {
    private static Logger log = LoggerFactory.getLogger(BloomFilterTest.class);
    private BloomFilter<Person> persons = BloomFilter.create(new Funnel<Person>() {
        @Override
        public void funnel(Person from, PrimitiveSink into) {
            into.putInt(from.getId());
        }
    }, 500, 0.01);

    public boolean containsPerson(Person person) {
        if (person == null) {
            log.warn("person is null");
            return false;
        }

        boolean exists = persons.mightContain(person);
        // 将不存在布隆过滤器的元素添加到布隆过滤器中
        if (!exists) {
            persons.put(person);
        }

        return exists;
    }

    public static void main(String[] args) {
        BloomFilterTest bloomFilterTest = new BloomFilterTest();
        Person person1 = new Person(1, "张三");
        Person person2 = new Person(2, "李四");
        System.out.println(bloomFilterTest.containsPerson(person1));
        System.out.println(bloomFilterTest.containsPerson(person2));
        System.out.println(bloomFilterTest.containsPerson(person1));
        System.out.println(bloomFilterTest.containsPerson(person2));
    }
}
class  Person {
    private String name;
    private Integer id;
    /**
     * @param id must not be null
     * @param name must not be empty
     */
    Person (Integer id, String name) {
        Assert.notNull(id);
        Assert.hasLength(name);
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
