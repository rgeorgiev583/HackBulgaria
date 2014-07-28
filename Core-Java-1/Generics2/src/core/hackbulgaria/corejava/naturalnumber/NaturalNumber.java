package core.hackbulgaria.corejava.naturalnumber;

public class NaturalNumber<T extends Number> {
	private T number;
	
	public NaturalNumber(T number) {
		this.number = number;
	}
	
	public boolean isEven() {
		return number.longValue() % 2 == 0;
	}
	
	public static void main(String[] args) {
		NaturalNumber<Long> n1 = new NaturalNumber<>(12341541L);
		NaturalNumber<Integer> n2 = new NaturalNumber<>(123);
		NaturalNumber<Short> n3 = new NaturalNumber<>((short) 122);

		System.out.println(n1.isEven()); //false;
		System.out.println(n2.isEven()); //false;
		System.out.println(n3.isEven()); //true;

		//NaturalNumber<String> n4 = new NaturalNumber<>("1231516"); //compile-time error!
	}
}
