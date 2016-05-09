package HomeWork.poker;
import java.util.Scanner;

/*
 * 游戏主类
 */
public class GameSystem {
	private Player player1;           //玩家一
	private Player player2;           //玩家二
	private GameModule module = new GameModule();;        //游戏数据处理模型
	
	Scanner input = new Scanner(System.in);
	
	//获取用户输入信息
	public void gameMenu() {
		player1 = new Player();
		player2 = new Player();
		
		while(true) {
			System.out.println("请输入玩家一的ID：");
			if ( input.hasNextInt() ) {
				player1.setId(input.nextInt());
			}
			else {
				System.out.println("玩家的ID为整数,请重新输入");
				continue;
			}
			System.out.println("请输入玩家一的名字：");
			if ( input.hasNext() ) {
				player1.setName( input.next() );
				break;
			}
			else {
				System.out.println("输入名字出现错误啦！！！");
				continue;
			}
		}
		
		while(true) {
			System.out.println("请输入玩家二的ID：");
			if ( input.hasNextInt() ) {
				player2.setId(input.nextInt());
			}
			else {
				System.out.println("玩家的ID为整数,请重新输入");
				continue;
			}
			System.out.println("请输入玩家二的名字：");
			if ( input.hasNext() ) {
				player2.setName( input.next() );
				break;
			}
			else {
				System.out.println("输入名字出现错误啦！！！");
				continue;
			}
		}
		System.out.println("----欢迎玩家：" + player1.getName());
		System.out.println("----欢迎玩家：" + player2.getName());
	}
	
	//为玩家发牌
	public void sendCard() {
		System.out.println("---------------------开始发牌----------------------");
		module.createPoker();
		for (int i=0; i<2; i++) {
			System.out.println("----玩家：" + player1.getName() + "--拿牌");
			player1.getCards().add( module.sendCard());
			System.out.println("----玩家：" + player2.getName() + "--拿牌");
			player2.getCards().add( module.sendCard());
		}
		System.out.println("---------------------发牌结束----------------------");
	}
	
	//游戏主题
	public void game() {
		System.out.println("---------------------开始游戏----------------------");
		System.out.println("玩家: " + player1.getName() + "最大手牌为:" + module.getPlayPokerMax(player1).getPokerName());
		System.out.println("玩家: " + player2.getName() + "最大手牌为:" + module.getPlayPokerMax(player2).getPokerName());
		System.out.println("----------------" +module.judgeResult(player1, player2)+ "--------------------");
		System.out.println("玩家各自的手牌为:");
		System.out.print(player1.getName() + ":" + module.showPoker(player1));
		System.out.print(player2.getName() + ":" + module.showPoker(player2));
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		GameSystem system = new GameSystem();
		system.gameMenu();
		system.sendCard();
		system.game();
	}

}
