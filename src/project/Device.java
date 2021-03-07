package project;

import java.util.ArrayList;
import java.util.List;

import servers.Interface;

public class Device {
	
	private String name;
	private String type;
	private List<Interface> interfaces;
	private boolean inNetwork;
	private String ip;
	
	public Device(String n, int inter, String t, String ip) {
		name = n;
		interfaces = new ArrayList<>();
		type = t;
		inNetwork = false;
		this.ip = ip;
		init(inter);
	}
	
	private void init(int n) {
		for(int i = 0; i < n; i++) {
			interfaces.add(new Interface(i));
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getType () {
        return type;
    }
	
	public List<Interface> getInterfaces() {
		return interfaces;
	}
	
	public boolean getInNetwork() {
		return inNetwork;
	}
	
	public String getIp() {
        return ip;
    }
	
	public void setInNetwork(boolean bool) {
		inNetwork = bool;
	}
	
	public String inter() {
		String str = "";
		for ( int i = 0; i < interfaces.size(); i++ ) {
			str += interfaces.get(i) + " ";
		}
		return str;
	}
	
	public Interface getInterfaceByIndex(int index) {
		return interfaces.get(index);
	}
	
	public int getNumberOfNotConnectedInterface() {
		int count = 0;
		for(int i = 0; i < interfaces.size(); i++) {			
			if(! getInterfaceByIndex(i).isUsed()) {
				count++;
			}
		}
		return count;
	}
	
	public int getNumberOfInterface() {
		return interfaces.size();
	}
	
    @Override
    public boolean equals(Object o) {
    	if(o instanceof Device) {
    		Device other = (Device) o;
    		boolean b = (name.equals(other.name) && type.equals(other.type));
    		if(ip.equals("")) {
    			return b;
    		} else {
    			return b || ip.equals(other.ip);
    		}
    	}
    	return false;
    }
	
	@Override
	public String toString() {
		return type + " - \"" + name + "\" avec " + interfaces.size() +" interfaces dont " + getNumberOfNotConnectedInterface() + " interface(s) disponible(s)";
	}
	
}
