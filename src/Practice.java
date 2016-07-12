import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 * Description:  练习专用类
 */

public class Practice {
	
	
	public static void main(String[] args) {
		String[] name={"姓名","号码"}; 
		String[] s1 = { "小王", "213213"}; 
		String[] s2 = { "小芳", "142321"}; 
		String data[][] = { s1, s2 }; 
		JFrame jf = new JFrame(); 
		JPanel jp = new JPanel();
		JTable table = new JTable(data,name); 
		JScrollPane JSP= new JScrollPane(table); 
		jf.setBounds(300, 300, 500, 500);
		jp.setBounds(0, 0, 400, 400);
		jp.add(JSP); 
		jf.add(jp);
		jf.setVisible(true); 
	}
   
}
