package servers;

public class Router extends Server{

	private static final String DEFAULT_TYPE = "Routeur";
	
	public Router(String name) {
		super(name, 2, DEFAULT_TYPE);
	}
	
	public Router(String name, int nb) {
		super(name, nb, DEFAULT_TYPE);
	}
	
}
