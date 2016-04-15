package system.rentCar;

public class Van extends Car implements ICarGo {

	public Van(int carID,String carName,int carMoney,double carGo){
		setCarID(carID);
		setCarName(carName);
		setCarMoney(carMoney);
		setCarGo(carGo);
	}
	
	private double carGoNumber;
    
	@Override
	public double getCarGo() {
		// TODO 自动生成的方法存根
		return carGoNumber;
	}
	public void setCarGo(double carGoNumber){
		this.carGoNumber= carGoNumber;
	}
	
	@Override
	public String toString() {
		return super.toString()+"载货："+getCarGo();
	}

}
