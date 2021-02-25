package clients;

import project.Device;

public class Client extends Device {
	
	private String ip;
	protected String type;
	
	public Client(String name, String ip, String t) {
		super(name, 2);
		this.ip = ip;
		type = t;
	}
	
	public String getIp() {
        return ip;
    }

	public String getType () {
        return type;
    }
	
    @Override
    public boolean equals(Object o) {
    	if(o instanceof Client) {
    		Client other = (Client) o;
    		return super.equals(other) || ip.equals(other.ip);
    	}
    	return false;
    }
    
	@Override
	public String toString() {
		return type + " \"" + name + "\" avec " + getNumberOfInterface() +" interfaces";
	}
	
}
