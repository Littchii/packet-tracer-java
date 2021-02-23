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
			System.out.println("\n## Le réseau " + ip+"/"+m + " a bien été crée ! ##\n");
		} else {
			System.out.println("\n## Le réseau existe déjà ! ##");
		}
	}
	
	public void addLaptop(String name, String ip) {
		if(! (ip.isEmpty() || name.isEmpty())) {
			Client laptop = new Client(name, ip);
			if(! clients.contains(laptop)) {
				clients.add(laptop);
				System.out.println("\n## Le laptop " + name + " avec l'ip " + ip + " a bien été crée ! ##\n");
			} else {
				System.out.println("Le laptop " + name+ "/" + ip + " existe déjà !");
			}
		} else {
			System.out.println("Veuillez renseigner les deux paramètres demandés !");
		}
	}
	
}
