package clients;

import project.Device;

public class Client extends Device {
	
	private String ip;
	
	public Client(String name, String ip, String type) {
		super(name, 2, type);
		this.ip = ip;
	}
	
	public String getIp() {
        return ip;
    }
	
    @Override
    public boolean equals(Object o) {
    	if(o instanceof Client) {
    		Client other = (Client) o;
    		return ip.equals(other.ip);
    	}
    	return false;
    }
	
}
