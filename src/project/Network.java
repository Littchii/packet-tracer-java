package project;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private String ip;
    private List<Device> devices;
    private static final String DEFAULT_MASK = "24";
    private String masque;

    
    public Network(String network) {	
        ip = network;
        devices = new ArrayList<>();
        masque = DEFAULT_MASK;
    }

    public Network(String network, String mask) {	
        ip = network;
        devices = new ArrayList<>();
        masque = mask;
    }

    public String getIp() {
        return ip;
    }

    public void addHost(Device host) {
		devices.add(host);
	}
    
    public void removeHost(Device host) {
    	devices.remove(host);
    }
    
    public String getMasque() {
        return masque;
    }
    
    public List<Device> getDevices() {
    	return devices;
    }
    
    @Override
    public boolean equals(Object o) {
    	if(o instanceof Network) {
    		Network other = (Network) o;
    		return ip.equals(other.ip) && masque.equals(other.masque);
    	}
    	return false;
    }

    @Override
    public String toString() {
        return ip + "/" + masque + " avec " + devices.size() + " device(s)";
    }

}
