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
	
}
