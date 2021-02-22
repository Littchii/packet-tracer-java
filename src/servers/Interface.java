package servers;

public class Interface {
	
	private int number;
	
	public Interface(int n) {
		number = n;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String toString() {
		return "Fa0/" + number;
	}
	
}
