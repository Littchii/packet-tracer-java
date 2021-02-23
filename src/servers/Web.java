package servers;

import java.util.ArrayList;
import java.util.List;

import clients.Client;
import project.Network;

public class Web {
	
	private List<Network> networks;
	private List<Client> clients;
	
	public Web() {
		networks = new ArrayList<>();
		clients = new ArrayList<>();
	}
	
	public List<Network> getNetworks() {
		return networks;
	}
	
	public List<Client> getClients() {
		return clients;
	}
	
	public void addNetwork(String ip, String mask) {	
		String m;
		Network tmp;
		if(mask.isEmpty()) {
			m = "24";
			tmp = new Network(ip);
		} else {
			m = mask;
			tmp = new Network(ip, mask);
		}
		
		if(! networks.contains(tmp)) {
			networks.add(tmp);
			System.out.println("\n## Le r�seau " + ip+"/"+m + " a bien �t� cr�e ! ##\n");
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
	
}
