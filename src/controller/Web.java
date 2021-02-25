package controller;

import java.util.ArrayList;
import java.util.List;

import cables.Cable;
import clients.Client;
import clients.Computer;
import project.Device;
import project.Network;
import servers.Interface;
import servers.Router;
import servers.Server;
import servers.Switch;

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
	
	public void connectDevice(Interface i1, Interface i2, Device d1, Device d2) {
		Cable cbl;
		cbl = new Cable(i1, i2, d1, d2);
		System.out.println(cbl);
	}
	
	private void addServer(Server tmp) {
		if(! servers.contains(tmp)) {
			servers.add(tmp);
			displayServerSuccess(tmp);
		} else {
			displayServerError(tmp);
		}
	}
	
	private void addClient(Client tmp) {
		if(! clients.contains(tmp)) {
			clients.add(tmp);
			displayClientSuccess(tmp);
		} else {
			displayClientError(tmp);
		}
	}
	
	private void displayServerSuccess(Server s) {
		System.out.println("\n## Le " + s.getType() + " " + s.getName() + " avec " + s.getInterface() + " interfaces a été crée ##\n");
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
