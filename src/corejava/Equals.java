package corejava;

import java.util.Objects;

public class Equals {
	private String filed = "";
	@Override
	public boolean equals(Object otherObject) {
		// 检查 this 与 otherObject 是否引用同一个对象
		if (this == otherObject) return true;
		// 判断对象是否为 null, 为 null 肯定不相等
		if (otherObject == null) return false;
		// 判断两个对象是否属于同一个类
		if (this.getClass() != otherObject.getClass()) return false;
		// 如果所有的子类都拥有统一的语义, 就使用 instanceof 检查
		//if (!(otherObject instanceof ClassName)) return false;
		// 将 otherObject 转换为相应的类类型变量
		Equals other = (Equals) otherObject;
		// 逐个将两个对象的指定字段进行比较, 推荐使用 Objects.equals() 方法来比较两个字段是否相等
		return Objects.equals(this.filed, other.filed);
	}
}
