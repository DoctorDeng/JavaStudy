package practice.inter;
/**
 * 练习：从ＵＲＬ中读取资源　openStream()
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;


public class ReadURLPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new NetWin();
	}

}

class NetWin extends JFrame implements ActionListener, Runnable {
	JButton button;
	URL url;
	JTextField text;
	JTextArea area;
	char b[] = new char[1];
	Thread thread;
	public NetWin() {
		text = new JTextField(20);
		area = new JTextArea(12,12);
		button = new JButton("确定");
		button.addActionListener(this);
		thread = new Thread(this);
		JPanel p = new JPanel();
		p.add(new JLabel("输入网址:"));
		p.add(text);
		p.add(button);
		add(new JScrollPane(area), BorderLayout.CENTER);
		add(p, BorderLayout.NORTH);
		setBounds(60, 60, 500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!(thread.isAlive())) {
			thread = new Thread(this);
		}
		try {
			thread.start();
		} catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	public void run() { 
		try {
			int n = -1;
			area.setText(null);
			url = new URL(text.getText().trim());
			InputStream inputStream = url.openStream();
			
			InputStreamReader reader = new InputStreamReader(inputStream);
			
			while ((n = reader.read(b, 0, b.length)) != -1) {
				System.out.println(n);
				String  s = new String(b, 0, n);
				
				if (s.contains(">")) {
					area.append(s + "\r\n");
				}
				else if(s.contains("}")) {
					area.append(s + "\r\n");
				}
				else {
					area.append(s);
				}
				System.out.println(n);
				
			}
		} catch(MalformedURLException e1) {
			text.setText("" + e1);
			return;
		} catch(IOException e1) {
			text.setText("" + e1);
		}
	}
	
}