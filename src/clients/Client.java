package clients;

import project.Device;

public class Client extends Device {
	
	private String ip;
	
	public Client(String name, String ip, String t) {
		super(name, 2, t);
		this.ip = ip;
	}
	
	public String getIp() {
        return ip;
    }
	
    @Override
    public boolean equals(Object o) {
    	if(o instanceof Client) {
    		Client other = (Client) o;
    		return super.equals(other) || ip.equals(other.ip);
    	}
    	return false;
    }
	
}
