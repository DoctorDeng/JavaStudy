package system.rentCar;

public class Bus extends Car implements IManned {
	
	private int mannedNumber;

	@Override
	public int getManned() {
		// TODO 自动生成的方法存根
		return mannedNumber;
	}
	public void setManned(int mannedNumber){
		this.mannedNumber=mannedNumber;
	}
	
	public void response(){
		System.out.printf("%-6d%-10s%-4d元/每天      载人:%2d\n",getCarID(),getCarName(),getCarMoney(),getManned());
	}
	

}
