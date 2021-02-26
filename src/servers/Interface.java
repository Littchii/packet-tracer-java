package servers;

import project.Device;

public class Interface {
	
	private int id;
	private Interface linkedInterface;
	private Device linkedDevice;
	public boolean used;
	
	public Interface(int n) {
		id = n;
		linkedInterface = null;
		linkedDevice = null;
		used = false;
	}
	
	public void connect(Device source, Device dest, int id) {
		linkedInterface = dest.getInterfaceByIndex(id);
		linkedDevice = dest;
		connect(source);
	}
	
	private void connect(Device source) {
		linkedInterface.linkedInterface = this;
		linkedInterface.linkedDevice = source;
		used = true;
		linkedInterface.used = true;
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
	
	public String getUsed() {
		if(used) {
			return "Cette interface est déjà utilisée.";
		}
		return "Cette interface n'est pas utilisée.";
	}
	
	@Override
	public String toString() {
		if(linkedInterface != null && linkedDevice != null) {
			return linkedInterface.linkedDevice.getName() + " -> " + getStringOfInterface() + " est connectée à l'interface de " + linkedDevice.getName() + " -> " + linkedInterface.getStringOfInterface();
		}
		return getStringOfInterface();
	}
	
}
