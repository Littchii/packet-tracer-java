package clients;

public class Laptop extends Client {
	
	private static final String DEFAULT_TYPE = "Laptop";
	
	public Laptop(String name, String ip) {
		super(name, ip, DEFAULT_TYPE);
	}
	
}
