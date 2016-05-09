package HomeWork.poker;

import java.util.ArrayList;
import java.util.List;

/*
 * 玩家类
 */
public class Player {
	private String name;
	private int id;
	private List<Cards> cards = new ArrayList<Cards>();         //玩家手中牌的集合
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Cards> getCards() {
		return cards;
	}

}
