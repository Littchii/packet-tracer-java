package project;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private String ip;
    private List<String> tabhost;
    private static final String DEFAULT_MASK = "24";
    private String masque;

    
    public Network(String network) {	
        ip = network;
        tabhost = new ArrayList<>();
        masque = DEFAULT_MASK;
    }

    public Network(String network, String mask) {	
        ip = network;
        tabhost = new ArrayList<>();
        masque = mask;
    }

    public String getIp() {
        return ip;
    }

    public void addHost(String host) {
		tabhost.add(host);
	}
    
    public String getMasque() {
        return masque;
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
		StringBuilder s = new StringBuilder();
		tabhost.forEach(n -> s.append(n + " "));
        return ip + "/" + masque + " avec " + tabhost.size() + " machine(s).";
    }

}
