package servers;

import project.Device;

public class Server extends Device {
	
	protected String type;
	
	public Server(String name, int nb, String t) {
		super(name, nb);
		type = t;
	}
	
	public String getType () {
        return type;
    }
	
	public String toString() {
		return type + " \"" + name + "\" avec " + getInterface() +" interfaces";
	}
	
    @Override
    public boolean equals(Object o) {
    	if(o instanceof Server) {
    		Server other = (Server) o;
    		return super.equals(other) && type.equals(other.type);
    	}
    	return false;
    }

}
