package servers;

import project.Device;

public class Server extends Device {
	
	public Server(String name, int nb, String t) {
		super(name, nb, t);
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
