package project;

import java.util.ArrayList;

import servers.Interface;

public class Device {
	
	protected String name;
	public ArrayList<Interface> interfaces;
	
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
	
	public int getNumberOfNotConnectedInterface() {
		int count = 0;
		for(int i = 0; i < interfaces.size(); i++) {			
			if(! getInterfaceByIndex(i).used) {
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
    		return name.equals(other.name);
    	}
    	return false;
    }
	
    @Override
	public String toString() {
		return name;
	}
	
}
