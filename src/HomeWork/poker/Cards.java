package HomeWork.poker;
/*
 * 牌对象
 */
public class Cards {
	private int tally;        //牌的点数
	private String style;     //牌的花色
	private String pokerName; //牌的名字
	
	public void setTally(int tally) {
		this.tally = tally;
	}
	public int getTally() {
		return this.tally;
	}
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	public String getPokerName() {
		return pokerName;
	}
	public void setPokerName(String pokerName) {
		this.pokerName = pokerName;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((style == null) ? 0 : style.hashCode());
//		result = prime * result + tally;
//		return result;
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cards))
			return false;
		Cards other = (Cards) obj;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
			return false;
		if (tally != other.tally)
			return false;
		return true;
	}
	
}
