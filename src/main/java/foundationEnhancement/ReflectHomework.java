package foundationEnhancement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * Description: 反射练习
 *
 * @author doctordeng
 * @since 2016/11/26 15:19
 */
public class ReflectHomework {
    private static void listToFile() {
        try (InputStream ips = new FileInputStream("config.properties")){
            Properties props = new Properties();
            props.load(ips);

            String className = props.getProperty("className");
            Collection<String> collections = (Collection<String>) Class.forName(className).newInstance();
            collections.add("ssss");
            collections.add("aass");
            collections.add("aass");
            System.out.println(collections.size());
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        listToFile();
    }
}
