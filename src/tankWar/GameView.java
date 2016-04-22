package tankWar;

import javax.swing.*;
import java.awt.*;

public class GameView {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        JFrame gameView = new JFrame("坦克大战");
        
        gameView.setBounds(450,200,500,400);
        gameView.setVisible(true);
        
        JMenuBar menbar = new JMenuBar();
        JMenu game = new JMenu("游戏");
        JMenuItem start = new JMenuItem("开始游戏");
        JMenuItem end = new JMenuItem("结束游戏");
        
	}

}
