import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
 * Description:  练习专用类
 */
public class Practice {
	

	public static void test4(){
		Map map=new LinkedHashMap();
		map.put("1", "aaa");
		map.put("2", "bbb");
		map.put("3", "ccc");
		 
		//传统方式2
		Set set=map.entrySet();
		Iterator it=set.iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry) it.next();
			String key=(String) entry.getKey();
			String valu=(String) entry.getValue();
			System.out.println(key + "=" + valu);
		}
		
	}


	public static void main(String[] args) {

		test4();
	}
}
