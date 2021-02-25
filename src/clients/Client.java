package clients;

import project.Device;

public class Client extends Device {
	
	private String ip;
	
	public Client(String name, String ip) {
		super(name, 2);
		this.ip = ip;
	}
	
	public String getIp() {
        return ip;
    }

	public void setIp (String i) {
        ip = i;
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
