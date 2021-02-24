package servers;

public class Switch extends Server {
	
	private static final String DEFAULT_TYPE = "Switch";
	
	public Switch(String name) {
		super(name, 10, DEFAULT_TYPE);
	}
	
	public Switch(String name, int nb) {
		super(name, nb, DEFAULT_TYPE);
	}
	
}
