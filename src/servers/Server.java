package servers;

import project.Device;

public class Server extends Device {
	
	protected String type;
	
	public Server(String name, String t, int nb) {
		super(name, nb);
		type = t;
	}
	
	public String getType () {
        return type;
    }
	
	public String toString() {
		return type + " qui se nomme \"" + name + "\" j'ai " + getInterface() +" interfaces qui sont : " + inter();
	}

}
