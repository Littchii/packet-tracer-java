package servers;

public class Router extends Server{

	private static final String DEFAULT_TYPE = "Routeur";
	
	public Router(String name) {
		super(name, DEFAULT_TYPE, 2);
	}
	
	public Router(String name, int nb) {
		super(name, DEFAULT_TYPE, nb);
	}
	
}
