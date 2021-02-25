package cables;

import project.Device;
import servers.Interface;

public class Cable {

	private Interface port1;
	private Interface port2;
	private Device device1;
	private Device device2;
	
	public Cable(Interface p1, Interface p2, Device d1, Device d2) {
		port1 = p1;
		port2 = p2;
		device1 = d1;
		device2 = d2;
	}

	public Interface getPort1() {
        return port1;
	}

	public Interface getPort2() {
        return port2;
	}

	public Device getDevice1() {
        return device1;
	}

	public Device getDevice2() {
        return device2;
	}

	@Override
	public String  toString() {
		return "Le cable connecte le port " + port1 + " (" + device1.getName() + ")" + " et " + port2 + " (" + device2.getName() + ")";
	}

}
