package controller;

import java.util.ArrayList;
import java.util.List;

import clients.Client;
import clients.Computer;
import clients.Laptop;
import project.Device;
import project.Network;
import servers.Interface;
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
	
	/**
	 * M�thode pour retourner la liste de tous les networks
	 * @return
	 */
	public List<Network> getNetworks() {
		return networks;
	}
	
	/**
	 * M�thode pour retourner tous les clients
	 * @return
	 */
	public List<Client> getClients() {
		List<Client> tmp = new ArrayList<>();
		for(Device element : devices) {
			if(element instanceof Client) {				
				tmp.add((Client) element);
			}
		}
		return tmp;
	}
	
	/**
	 * M�thode pour retourner tous les serveurs
	 * @return
	 */
	public List<Server> getServers() {
		List<Server> tmp = new ArrayList<>();
		for(Device element : devices) {
			if(element instanceof Server) {				
				tmp.add((Server) element);
			}
		}
		return tmp;
	}
	
	/**
	 * M�thode pour retourner tous les devices
	 * @return
	 */
	public List<Device> getDevices() {
		return devices;
	}
	
	/**
	 * M�thode pour ajouter un r�seau
	 * @param ip
	 * @param mask
	 */
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
	
	/**
	 * M�thode pour ajouter un ordinateur ou un laptop
	 * @param name
	 * @param ip
	 * @param type
	 */
	public void addComputer(String name, String ip, String type) {
		if(! (name.isEmpty() || ip.isEmpty())) {
			addClient(type == "Computer" ? new Computer(name, ip) : new Laptop(name, ip));
		} else {
			System.out.println("Veuillez renseigner les deux param�tres demand�s !\n");
		}
	}
	
	/**
	 * M�thode pour ajouter un switch
	 * @param name
	 * @param interfaces
	 */
	public void addSwitch(String name, int interfaces) {
		addServer(interfaces == 0 ? new Switch(name) : new Switch(name, interfaces));
	}
	
	/**
	 * M�thode pour ajouter un routeur
	 * @param name
	 * @param interfaces
	 */
	public void addRouter(String name, int interfaces) {
		addServer(interfaces == 0 ? new Router(name) : new Router(name, interfaces));
	}
	
	/**
	 * M�thode pour connecter 2 devices ensemble
	 * @param d1
	 * @param d2
	 * @param nb
	 * @param i1
	 */
	public void connectDevice(Device d1, Device d2, int nb, Interface i1) {
		if(! (d1 instanceof Client && d2 instanceof Client)) {	
			if((d1 instanceof Server && d2 instanceof Server)) {
				((Server) d1).addDevices(d2);
				((Server) d2).addDevices(d1);
			} else {				
				if(d1 instanceof Server) {
					((Server) d1).addDevices(d2);
				} else {
					((Server) d2).addDevices(d1);
				}
			}
			i1.connect(d1, d2, nb);
			System.out.println(i1);
			System.out.println(d2.getInterfaceByIndex(nb));
		} else {
			System.out.println("Vous ne pouvez pas connecter 2 clients ensemble !");
		}
	}
	
	/**
	 * M�thode pour ajouter un device dans un r�seau
	 * @param n
	 * @param d
	 */
	public void addDeviceInNetwork(Network n, Device d) {
		deviceInNetwork("add", n, d);
		System.out.println("## Le device " + d.getName() + " a �t� ajout� au r�seau " + n.getIp() + "/" + n.getMasque() + " ! ##\n");
	}
	
	/**
	 * M�thode pour voir la liste de tous les devices d'un r�seau pr�cis
	 * @param n
	 */
	public void showAllDevicesInNetwork(Network n) {
		if(! n.getDevices().isEmpty()) {			
			System.out.println(n.getDevices());
		} else {
			System.out.println("Il n'y a pas de device dans ce r�seau !");
		}
	}
	
	/**
	 * M�thode pour supprimer un device de son r�seau
	 * @param n
	 * @param d
	 */
	public void removeDeviceInNetwork(Network n, Device d) {
		deviceInNetwork("remove", n, d);
		System.out.println("## Le device " + d.getName() + " a �t� supprim� du r�seau " + n.getIp() + "/" + n.getMasque() + " ! ##\n");
	}
	
	/**
	 * M�thode pour ajouter un device dans un r�seau
	 * @param type
	 * @param n
	 * @param d
	 */
	public void deviceInNetwork(String type, Network n, Device d) {
		if(type == "add") {
			n.addHost(d);
			d.setInNetwork(true);
		} else if(type == "remove") {
			n.removeHost(d);
			d.setInNetwork(false);		
		}
	}
	
	/**
	 * M�thode pour ajouter un serveur
	 * @param tmp
	 */
	private void addServer(Server tmp) {
		if(! devices.contains(tmp)) {
			devices.add(tmp);
			displayServerSuccess(tmp);
		} else {
			displayServerError(tmp);
		}
	}
	
	/**
	 * M�thode pour ajouter un client
	 * @param tmp
	 */
	private void addClient(Client tmp) {
		if(! devices.contains(tmp)) {
			devices.add(tmp);
			displayClientSuccess(tmp);
		} else {
			displayClientError(tmp);
		}
	}
	
	private void displayServerSuccess(Server s) {
		System.out.println("\n## Le " + s.getType() + " " + s.getName() + " avec " + s.getNumberOfInterface() + " interfaces a �t� cr�e ##\n");
	}
	
	private void displayServerError(Server s) {
		System.out.println("\n"+ s.getType() + " " + s.getName() + " existe d�j� !\n");
	}
	
	private void displayClientSuccess(Client c) {
		System.out.println("\n## " + c.getType() + " " + c.getName() + " avec l'ip " + c.getIp() + " a bien �t� cr�e ! ##\n");
	}
	
	private void displayClientError(Client c) {
		System.out.println("\nL'ip " + c.getIp() + " existe d�j� !\n");
	}
	
}
