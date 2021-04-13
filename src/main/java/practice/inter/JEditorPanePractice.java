package practice.inter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JEditorPanePractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new WinOne();
		
	}

}

class WinOne extends JFrame implements ActionListener, Runnable {
	JButton button;
	URL url;
	JTextField text;
	JEditorPane editPane;
	byte b[] = new byte[118];
	Thread thread;
	
	public WinOne() {
		text = new JTextField(20);
		editPane = new JEditorPane();
		editPane.setEditable(false);
		button = new JButton("确定");
		button.addActionListener(this);
		thread = new Thread(this);
		JPanel p = new JPanel();
		p.add(new JLabel("输入网址:"));
		p.add(text);
		p.add(button);
		Container container = getContentPane();
		container.add(new JScrollPane(editPane), BorderLayout.CENTER);
		container.add(p, BorderLayout.NORTH);
		setBounds(60, 60, 500, 500);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!(thread.isAlive())) {
			thread = new Thread(this);
		}
		
		try {
			thread.start(); 
		} catch (Exception ee) {
			text.setText("我正在读取" + url);
		}
	}
	
	public void run() {
		try {
			int n = -1;
			editPane.setText(null);
			url = new URL(text.getText().trim());
			editPane.setPage(url);
		} catch (MalformedURLException e1) {
			text.setText("" + e1);
			return;
		} catch (IOException ee) {
			text.setText("" + ee);
			return;
		} 
	}
}