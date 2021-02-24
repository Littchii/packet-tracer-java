package project;

import java.util.ArrayList;

import servers.Interface;

public class Device {
	
	protected String name;
	protected ArrayList<Interface> interfaces;
	
	public Device(String n, int inter) {
		name = n;
		interfaces = new ArrayList<>();
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
	
	public int getInterface() {
		return interfaces.size();
	}
	
	public String toString() {
		return name;
	}
	
    @Override
    public boolean equals(Object o) {
    	if(o instanceof Device) {
    		Device other = (Device) o;
    		return name.equals(other.name);
    	}
    	return false;
    }
	
}
