package tankWar.gameView;

import java.awt.Image;

/*
 * Description:  坦克大战游戏界面的抽象父类
 * Function:     
 * Version:      1.0beta
 * Author:       Doctor邓
 */

import java.awt.Toolkit;

public abstract class AGameView {
	
	final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int gameWidth;
	private int gameHight;
	private String titleName;
	
	//初始化界面方法
	public abstract void initialization();
	
	//设置图标方法
	public Image setIco(String icoPath){
		return Toolkit.getDefaultToolkit().createImage(icoPath);
	}
	
	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}

	public void setGameHight(int gameHight) {
		this.gameHight = gameHight;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public int getGameWidth() {
		return gameWidth;
	}
	public int getGameHight() {
		return gameHight;
	}
	public String getTitleName() {
		return titleName;
	}
	
}

