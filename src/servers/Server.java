package servers;

import java.util.ArrayList;
import java.util.List;

import project.Device;

public class Server extends Device {
	
	private List<Device> devices;
	
	public Server(String name, int nbInterfaces, String type) {
		super(name, nbInterfaces, type, "");
		devices = new ArrayList<>();
	}
	
	public List<Device> getDevices() {
		return devices;
	}

	public void addDevices(Device d) {
		devices.add(d);
	}
	
}
