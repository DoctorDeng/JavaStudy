package js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="http://doctordeng.vip/">doctordeng</a>
 * @version 1.0
 * @description 练习使用 Java 运行 JavaScript
 * @since 2017/11/22 19:44
 */
public class TestJavaScript {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        if (!(engine instanceof Invocable)) {
            System.out.println("Invoking method is not supported");
            return;
        }

        Invocable inv = (Invocable) engine;
        String scriptPath = "test.js";
        String scriptPath2 = "test2.js";
        String path = Thread.currentThread().getContextClassLoader().getResource("js").getPath();
        System.out.println(path);
        System.out.println(File.pathSeparator);
        engine.eval("load('" +path + "/" + scriptPath + "')");
        engine.eval("load('" + path + "/" + scriptPath2 + "')");

        Object calulator = engine.get("calculator2");
        int x = 3;
        int y = 4;
        Object addResult = inv.invokeMethod(calulator, "add", x, y);
        Object[] objects = new Object[2];
        objects[0] = 1;
        objects[1] = 2;
        Map<String, String> map = new HashMap<>();
        map.put("aaa","11221");
        System.out.println(inv.invokeFunction("testArray", map));

        int a = objects.length;
        System.out.println(addResult);
    }
}
