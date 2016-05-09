package HomeWork.poker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/*
 * 游戏数据处理的模型,有如下功能:发牌、创建一副顺序随机的扑克牌、判定输赢、
 *                  
 *                  
 */
public class GameModule {
	private static int[] TALLY = {2,3,4,5,6,7,8,9,10,11,12,13,14};   //存储扑克牌的点数
	private static String[] STYLE = {"黑桃","红桃","梅花","方片"};          //存储扑克牌的花色
	private int POKER_NUM = 10;        //一副扑克的扑克数量
	private List<Cards> cardsList;     //存储一副扑克牌
	
	private Random random;
	private int counter;          //游戏发牌的计数器
	
	public GameModule() {
		random = new Random();
		cardsList = new ArrayList<Cards>();
		counter = -1;
	}
	
	//将牌点数为11~14装换为实际的J、Q、K、J名称的方法。
	public String turnName(int tally) {
		if(tally >=2 && tally <= 10){
			return String.valueOf(tally);
		}
		else if (tally == 11) {
			return "J";
		}
		else if (tally == 12) {
			return "Q";
		}
		else if (tally == 13) {
			return "K";
		}
		else if (tally == 14) {
			return "A";
		}
		else {
			return "error";
		}
		
	}
	
	//比较花色的大小
	public boolean compareStyle(String style1, String style2) {
		int level1 = 0;   //玩家一花色对应的级别，将花色转化为整数可以很好的计算花色大小
		int level2 = 0;   //玩家二花色对应的级别
		
		switch (style1) {
		case "黑桃" :
			level1 = 4;
		case "红桃" :
			level1 = 3;
		case "梅花" :
			level1 = 2;
		case "方片" :
			level1 = 1;
		}
		
		switch (style2) {
		case "黑桃" :
			level2 = 4;
		case "红桃" :
			level2 = 3;
		case "梅花" :
			level2 = 2;
		case "方片" :
			level2 = 1;
		}
		
		if (level1 > level2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//随机产生一张牌
	public Cards createCard() {
		Cards card = new Cards();
		int tallyI = random.nextInt(TALLY.length);  
		int styleI = random.nextInt(STYLE.length);        
		
		//随机为card对象填入点数、花色和名字
		card.setTally(TALLY[tallyI]);
		card.setStyle(STYLE[styleI]);
		card.setPokerName( STYLE[styleI] + turnName(TALLY[tallyI]));
		
		return card;
	}
	
	//产生一副顺序随机的扑克牌
	public void createPoker() {
		Cards temp;
		for (int i =0; i<POKER_NUM; i++) {
			temp = createCard();
			if ( cardsList.contains(temp) == false) {
				cardsList.add(temp);
			}
			else {
				i--;
			}
		}
	}
	
	//发牌
	public Cards sendCard() {
		counter++;
		return cardsList.get(counter);
	}
	
	
	//比较两张牌的大小
	private boolean comparePoker(Cards card1, Cards card2) {
		
		if( card1.getTally() == card2.getTally() ) {
			if ( compareStyle(card1.getStyle(), card2.getStyle())) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (card1.getTally() > card2.getTally() ) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//判断玩家手里最大的牌
	public Cards getPlayPokerMax(Player player) {
		Cards card1 = null;       //用于临时存储玩家手里的牌
		Cards card2 = null;       
		Cards carMax;            //用于储存玩家手里最大的牌
		
		List<Cards> car = player.getCards();
		if (car.isEmpty()) {
			
		}
		if ( car.isEmpty() == false) {
			card1 = car.get(0);
			if ( car.get(1) != null ) {
				card2 = car.get(1);
			}
			else {
				System.out.println("出现错误，玩家只有一张牌！请重新开始游戏！");
			}
		}
		else {
			System.out.print("出现错误，玩家没有牌！请重新开始游戏！");
		}
		if(comparePoker(card1,card2)){
			carMax = card1;
		}
		else{
			carMax = card2;
		}
		return carMax;
	}
	
	//裁定游戏胜负
	public String judgeResult(Player player1, Player player2) {
    
		//判断玩家手里最大的牌
		Cards card1Max = getPlayPokerMax(player1);
		Cards card2Max = getPlayPokerMax(player2);
		
		//判断两个玩家哪个牌最大，并返回游戏胜负结果
		if (comparePoker(card1Max, card2Max)) {
			return "玩家:" + player1.getName() + "获胜！";
		}
		else {
			return "玩家:" + player2.getName() + "获胜！";
		}
		
	}
	
	//显示玩家手牌
	public String showPoker(Player player) {
		
		StringBuffer temp = new StringBuffer(); //用于临时存储玩家手牌的字符串信息
		for( Cards card : player.getCards()) {
			temp.append(card.getPokerName() + "");
		}
		
		return "[" + temp + "]";
	}
	public List<Cards> getCardsList() {
		return cardsList;
	}
}
