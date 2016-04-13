package system.rentCar;

public class Van extends Car implements ICarGo {
    private double carGoNumber;
    
	@Override
	public double getCarGo() {
		// TODO 自动生成的方法存根
		return carGoNumber;
	}
	public void setCarGo(double carGoNumber){
		this.carGoNumber= carGoNumber;
	}
	
	public void response(){
	    System.out.printf("%-6d%-10s%-4d元/每天      载货: %-4.1f吨 \n",getCarID(),getCarName(),getCarMoney(),getCarGo());
	}

}
