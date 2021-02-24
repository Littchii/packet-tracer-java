package servers;

import java.util.ArrayList;
import java.util.List;

import clients.Client;
import project.Network;

public class Web {
	
	private List<Network> networks;
	private List<Client> clients;
	private List<Server> servers;
	
	public Web() {
		networks = new ArrayList<>();
		clients = new ArrayList<>();
		servers = new ArrayList<>();
	}
	
	public List<Network> getNetworks() {
		return networks;
	}
	
	public List<Client> getClients() {
		return clients;
	}
	
	public List<Server> getServers() {
		return servers;
	}
	
	public void addNetwork(String ip, String mask) {	
		Network tmp;
		if(mask.isEmpty()) {
			tmp = new Network(ip);
		} else {
			tmp = new Network(ip, mask);
		}
		
		if(! networks.contains(tmp)) {
			networks.add(tmp);
			System.out.println("\n## Le r�seau " + ip + "/" + tmp.getMasque() + " a bien �t� cr�e ! ##\n");
		} else {
			System.out.println("\n## Le r�seau existe d�j� ! ##");
		}
	}
	
	public void addLaptop(String name, String ip) {
		if(! (ip.isEmpty() || name.isEmpty())) {
			Client laptop = new Client(name, ip);
			if(! clients.contains(laptop)) {
				clients.add(laptop);
				System.out.println("\n## Le laptop " + name + " avec l'ip " + ip + " a bien �t� cr�e ! ##\n");
			} else {
				System.out.println("Le laptop " + name+ "/" + ip + " existe d�j� !");
			}
		} else {
			System.out.println("Veuillez renseigner les deux param�tres demand�s !");
		}
	}
	
	public void addSwitch(String name, int interfaces) {
		Switch tmp;
		if(interfaces == 0) {
			tmp = new Switch(name);
		} else {
			tmp = new Switch(name, interfaces);
		}
		
		if(! servers.contains(tmp)) {
			servers.add(tmp);
			System.out.println("\n## Le Switch " + name + " avec " + tmp.getInterface() + " interfaces a �t� cr�e ##\n");
		} else {
			System.out.println("\n## Le Switch " + name + " existe d�j� ! ##");
		}
	}
	
}
