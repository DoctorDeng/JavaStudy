package system.rentCar;

public class Car {
	
	private String carName;      //车名
	private int carMoney;        //车每日租金
	private int carID;           //车的序号
	
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getCarMoney() {
		return carMoney;
	}
	public void setCarMoney(int carMoney) {
		this.carMoney = carMoney;
	}
	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID = carID;
	}
}
