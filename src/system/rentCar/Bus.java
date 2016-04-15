package system.rentCar;

public class Bus extends Car implements IManned {
	public Bus(int carID,String carName,int carMoney,int manned){
		setCarID(carID);
		setCarName(carName);
		setCarMoney(carMoney);
		setManned(manned);
	}

	private int mannedNumber;

	@Override
	public int getManned() {
		// TODO 自动生成的方法存根
		return mannedNumber;
	}
	public void setManned(int mannedNumber){
		this.mannedNumber=mannedNumber;
	}
	
	public String toString(){
		return super.toString()+"载人："+getManned();
	}

}
