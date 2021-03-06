package clients;

import project.Device;

public class Client extends Device {
	
	public Client(String name, String ip, String type) {
		super(name, 2, type, ip);
	}
	
}
