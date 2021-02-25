package clients;

public class Computer extends Client {
	
	private static final String DEFAULT_TYPE = "Computer";
	
	public Computer(String name, String ip) {
		super(name, ip, DEFAULT_TYPE);
	}

}
