package controller;

import java.util.ArrayList;
import java.util.List;

import clients.Client;
import clients.Computer;
import project.Device;
import project.Network;
import servers.Router;
import servers.Server;
import servers.Switch;

public class Web {
	
	private List<Network> networks;
	private List<Device> devices;
	
	public Web() {
		networks = new ArrayList<>();
		devices = new ArrayList<>();
	}
	
	public List<Network> getNetworks() {
		return networks;
	}
	
	public List<Client> getClients() {
		List<Client> tmp = new ArrayList<>();
		for(Device element : devices) {
			if(element instanceof Client) {				
				tmp.add((Client) element);
			}
		}
		return tmp;
	}
	
	public List<Server> getServers() {
		List<Server> tmp = new ArrayList<>();
		for(Device element : devices) {
			if(element instanceof Server) {				
				tmp.add((Server) element);
			}
		}
		return tmp;
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
			System.out.println("\n## Le réseau " + ip + "/" + tmp.getMasque() + " a bien été crée ! ##\n");
		} else {
			System.out.println("\n## Le réseau existe déjà ! ##");
		}
	}
	
	public void addLaptop(String ip, String name) {
		if(! (name.isEmpty() || ip.isEmpty())) {
			addClient(new Computer(ip, name));
		} else {
			System.out.println("Veuillez renseigner les deux paramètres demandés !");
		}
	}
	
	public void addSwitch(String name, int interfaces) {
		addServer(interfaces == 0 ? new Switch(name) : new Switch(name, interfaces));
	}
	
	public void addRouter(String name, int interfaces) {
		addServer(interfaces == 0 ? new Router(name) : new Router(name, interfaces));
	}
	
	public void connectDevice(Device source, Device dest, int i) {
		
	}
	
	private void addServer(Server tmp) {
		if(! devices.contains(tmp)) {
			devices.add(tmp);
			displayServerSuccess(tmp);
		} else {
			displayServerError(tmp);
		}
	}
	
	private void addClient(Client tmp) {
		if(! devices.contains(tmp)) {
			devices.add(tmp);
			displayClientSuccess(tmp);
		} else {
			displayClientError(tmp);
		}
	}
	
	private void displayServerSuccess(Server s) {
		System.out.println("\n## Le " + s.getType() + " " + s.getName() + " avec " + s.getNumberOfInterface() + " interfaces a été crée ##\n");
	}
	
	private void displayServerError(Server s) {
		System.out.println("\n"+ s.getType() + " " + s.getName() + " existe déjà !\n");
	}
	
	private void displayClientSuccess(Client c) {
		System.out.println("\n## " + c.getType() + " " + c.getName() + " avec l'ip " + c.getIp() + " a bien été crée ! ##\n");
	}
	
	private void displayClientError(Client c) {
		System.out.println("\n" + c.getType() + " " + c.getName() + " avec l'ip " + c.getIp() + " existe déjà !\n");
	}
	
}
