package system.rentCar;

public class Picard extends Car implements IManned, ICarGo {
	
	public Picard(int carID,String carName,int carMoney,int manned,double carGo){
		setCarID(carID);
		setCarName(carName);
		setCarMoney(carMoney);
		setManned(manned);
		setCarGo(carGo);
	}
	
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
	
	public String toString(){
		return super.toString()+"载人："+getManned()+"  载货："+getCarGo();
	}

}
