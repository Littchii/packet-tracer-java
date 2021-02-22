package servers;

public class Switch extends Server {
	
	private static final String DEFAULT_TYPE = "Switch";
	
	public Switch(String name) {
		super(name, DEFAULT_TYPE, 10);
	}
	
	public Switch(String name, int nb) {
		super(name, DEFAULT_TYPE, nb);
	}
	
}
