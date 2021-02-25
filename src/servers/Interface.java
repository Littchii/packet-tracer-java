package servers;

import project.Device;

public class Interface {
	
	private int id;
	private Interface linkedInterface;
	private Device linkedDevice;
	
	public Interface(int n) {
		id = n;
		linkedInterface = null;
		linkedDevice = null;
	}
	
	public void connect(Device source, Device dest, int id) {
		linkedInterface = dest.getInterfaceByIndex(id);
		linkedDevice = dest;
		connect(source);
	}
	
	private void connect(Device source) {
		linkedInterface.linkedInterface = this;
		linkedInterface.linkedDevice = source;
	}
	
	public int getId() {
		return id;
	}
	
	public Interface getLinkedInterface() {
		return linkedInterface;
	}
	
	public Device getLinkedDevice() {
		return linkedDevice;
	}
	
	private String getStringOfInterface() {
		return "Fa0/" + id;
	}
	
	@Override
	public String toString() {
		if(linkedInterface != null && linkedDevice != null) {
			return getStringOfInterface() + " est connect�e � l'interface de " + linkedDevice.getName() + " -> " + linkedInterface.getStringOfInterface();
		}
		return getStringOfInterface();
	}
	
}
