package tankWar.gameView;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Description:  选择游戏等级的窗口
 */
public class RankView extends AGameView{
	
	private static JFrame rankView;     //游戏等级选择窗体
	private final int RANKVIEW_WIDTH = 100;
	private final int RANKVIEW_HEIGHT = 200;
	private final String RANKVIEW_NAME = "等级选择";
	
	public JFrame getRankView() {
		return rankView;
	}


	RankView(){
		this.setGameHight(RANKVIEW_HEIGHT);
		this.setGameWidth(RANKVIEW_WIDTH);
		this.setTitleName(RANKVIEW_NAME);
		initialization();
	}


	@Override
	public void initialization() {
		// TODO 自动生成的方法存根
		rankView = new JFrame(this.getTitleName());
		rankView.setSize(this.getGameWidth(),this.getGameHight());
		rankView.setLocation((SCREEN_WIDTH - this.getGameHight())/2,(SCREEN_HEIGHT - this.getGameWidth())/2);
		rankView.setIconImage(this.setIco("gameImage/gameLogo.png"));
		rankView.setResizable(false);
		//rankView.setLayout(null);
        rankView.setLayout(new FlowLayout());
        
        JButton rankOne = new JButton("等级一"),
                rankTwo = new JButton("等级二"),
        		rankThree = new JButton("等级三"),
                rankFour = new JButton("等级四");
        
        rankView.add(rankOne);
        rankView.add(rankTwo);
        rankView.add(rankThree);
        rankView.add(rankFour);
        
        addActionListener(rankOne);
        addActionListener(rankTwo);
        addActionListener(rankThree);
        addActionListener(rankFour);
        
        rankView.setVisible(true);
	}
	
	//为button添加监听事件
	public void addActionListener(JButton button){
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				rankView.dispose();
			}
        });
	}
	
	static public void dispose(){
		try{
			rankView.dispose();
		}
		catch(Exception e){
			
		}
	}
}
