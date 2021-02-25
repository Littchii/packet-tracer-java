package clients;

public class Computer extends Client {
	
	private static final String DEFAULT_TYPE = "Computer";
	
	public Computer(String ip, String name) {
		super(ip, name, DEFAULT_TYPE);
	}
	
	@Override
	public String toString() {
		return getIp();
	}

}
