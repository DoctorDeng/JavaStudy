package system.rentCar;

public class Picard extends Car implements IManned, ICarGo {
	private double carGoNumber;
	private int mannedNumber;
	@Override
	public double getCarGo() {
		// TODO 自动生成的方法存根
		return carGoNumber;
	}
	
	public void setCarGo(double carGoNumber){
		this.carGoNumber=carGoNumber;
	}

	@Override
	public int getManned() {
		// TODO 自动生成的方法存根
		return mannedNumber;
	}
	public void setManned(int mannedNumber){
		this.mannedNumber=mannedNumber;
	}
	
	public void response(){
		System.out.printf("%-6d%-10s%-4d元/每天     载人:%-2d 载货:%-4.1f吨 \n",getCarID(),getCarName(),getCarMoney(),getManned(),getCarGo());
	}

}
