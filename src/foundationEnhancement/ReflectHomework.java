package foundationEnhancement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * Description: 反射练习,
 *
 * @author Doctor邓
 * @since：2016/11/26 15:19
 */
public class ReflectHomework {
    public static void listToFile() {
        try (InputStream ips = new FileInputStream("config.property")){
            Properties props = new Properties();
            props.load(ips);

            String className = props.getProperty("className");
            Collection collections = (Collection)Class.forName(className);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        listToFile();
    }
}
