package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import clients.Client;
import project.Device;
import project.Network;
import servers.Interface;
import servers.Server;

public class Controller {
	
	private Web web;
	private Scanner scan;
	private String ip;
	
	public Controller() {
		web = new Web();
	}
	
	/**
	 * M�thode pour d�marrer le programme
	 */
	public void start() {
		System.out.println("########################################################\n"
						 + "######## Bienvenue sur un Packet Tracer light ! ########\n"
						 + "########################################################");
		System.out.println("\nQue voulez-vous faire ?\n");
		System.out.println("1. Cr�er un nouveau r�seau");
		System.out.println("2. Cr�er un nouvel ordinateur");
		System.out.println("3. Cr�er un nouvel ordinateur portable");
		System.out.println("4. Cr�er un nouveau switch");
		System.out.println("5. Cr�er un nouveau routeur");
		System.out.println("6. Connecter 2 appareils par un c�ble");
		System.out.println("7. Ajouter un device dans un r�seau");
		System.out.println("8. Supprimer un device de son r�seau");
		System.out.println("9. Voir tous les devices d'un r�seau");
		System.out.println("-----------------------------");
		System.out.println("10. Voir la liste des r�seaux");
		System.out.println("11. Voir la liste des clients");
		System.out.println("12. Voir la liste des serveurs");
		System.out.println("-----------------------------");
		System.out.println("13. Rentrer dans le terminal d'un device\n");
		System.out.print("Choisir un chiffre : ");
		scan = new Scanner(System.in);
		if(scan.hasNextInt()) {
			int v = scan.nextInt();
			switch(v) {
				case 1:
					addNetwork();
					break;
				case 2:
					addComputer("Computer");
					break;
				case 3:
					addComputer("Laptop");
					break;
				case 4:
					addSwitch();
					break;
				case 5:
					addRouter();
					break;
				case 6:
					connectDevice();
					break;
				case 7:
					addDeviceInNetwork();
					break;
				case 8:
					removeDeviceInNetwork();
					break;
				case 9:
					showAllDevicesInNetwork();
					break;
				case 10:
					showNetworks();
					break;
				case 11:
					showClients();
					break;
				case 12:
					showServers();
					break;
				case 13:
					promptDevice();
					break;
			}
			options();
		} else {
			System.out.println("\nVous devez rentrer un chiffre !\n");
			start();
		}
	}
	
	
	/**
	 * M�thode appel�e apr�s que l'utilisateur a effectu� une action de start()
	 */
	private void options() {
		System.out.println("Voulez-vous continuer le programme ?\n");
		System.out.println("1. Oui");
		System.out.println("2. Nan");
		System.out.print("\nChoisir un chiffre : ");
		scan = new Scanner(System.in);
		if(scan.hasNextInt()) {
			int v = scan.nextInt();
			switch(v) {
				case 1:
					start();
					break;
				case 2:
					System.exit(400);
			}
		} else {
			System.out.println("\nVous devez rentrer un chiffre !\n");
			options();
		}
	}
	
	/**
	 * M�thode pour ajouter un nouveau network
	 */
	private void addNetwork() {
		System.out.print("Quel est l'IP du r�seau ? ");
		scan = new Scanner(System.in);
		ip = scan.nextLine();
		System.out.print("Quel est le masque du r�seau ? (par d�faut /24) ");
		String mask = scan.nextLine();
		web.addNetwork(ip, mask);
	}
	
	/**
	 * M�thode pour ajouter un nouveau computer ou laptop
	 * @param type
	 */
	private void addComputer(String type) {
		System.out.print("Quel est le nom de votre " + type + " ? ");
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.print("Quel est l'IP de votre " + type + " ? ");
		ip = scan.nextLine();
		web.addComputer(name, ip, type);
	}
	
	/**
	 * M�thode pour ajouter un switch
	 */
	private void addSwitch() {
		System.out.print("Quel est le nom de votre switch ? ");
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.print("Nombre d'interfaces pour votre switch ? (par d�faut 10) ");
		String interfaces = scan.nextLine();
		String s = interfaces.isEmpty() ? "0" : interfaces;
		web.addSwitch(name, Integer.parseInt(s));
	}
	
	/**
	 * M�thode pour ajouter un routeur
	 */
	private void addRouter() {
		System.out.print("Quel est le nom de votre routeur ? ");
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.print("Nombre d'interfaces pour votre routeur ? (par d�faut 2) ");
		String interfaces = scan.nextLine();
		String s = interfaces.isEmpty() ? "0" : interfaces;
		web.addRouter(name, Integer.parseInt(s));
	}
	
	/**
	 * M�thode pour connecter 2 devices ensemble avec leurs interfaces
	 */
	private void connectDevice() {
		scan = new Scanner(System.in);
		if(! web.getDevices().isEmpty() && web.getDevices().size() > 1) {
			System.out.println("\nQuel device voulez-vous connecter ?");
			
			boucleOnDevice(web.getDevices(), null);
			
			int nb = scan.nextInt();
			Device d1 = web.getDevices().get(nb);
			System.out.println("\nQuelle interface voulez-vous connecter ?");
			
			boucleOnInterface(d1);
			
			nb = scan.nextInt();
			Interface i1 = d1.getInterfaceByIndex(nb);
			System.out.println("\nSur quel autre device voulez-vous vous connecter ? ");
			
			boucleOnDevice(web.getDevices(), d1);
			
			nb = scan.nextInt();
			Device d2 = web.getDevices().get(nb);
			System.out.println("\nSur quelle interface du deuxi�me device voulez-vous vous connecter ? ");
			
			boucleOnInterface(d2);
			
			nb = scan.nextInt();
			web.connectDevice(d1, d2, nb, i1);
		} else {
			System.out.println("Il n'y a aucun device cr�e ou il n'y a qu'un seul device !");
		}
	}
	
	/**
	 * M�thode pour ajouter un device dans un network
	 */
	private void addDeviceInNetwork() {
		if(web.getNetworks().size() > 0 && web.getDevices().size() > 0) {			
			scan = new Scanner(System.in);
			System.out.println("Dans quel r�seau voulez-vous ajouter votre device ?");
			boucleOnNetwork(false);
			int nb = scan.nextInt();
			Network n = web.getNetworks().get(nb);
			
			System.out.println("Quel device voulez-vous ajouter dans ce r�seau ?");
			for(int i = 0; i < web.getDevices().size(); i++) {	
				if(! web.getDevices().get(i).getInNetwork()) {						
					System.out.println(i + " - " + web.getDevices().get(i));
				}
			}
			nb = scan.nextInt();
			Device d = web.getDevices().get(nb);
			
			web.addDeviceInNetwork(n, d);
		} else {
			System.out.println("Il n'y a aucun r�seau ou device !");
		}
	}
	
	/**
	 * M�thode pour voir tous les devices connect�s � un network
	 */
	public void showAllDevicesInNetwork() {
		scan = new Scanner(System.in);
		System.out.println("Quel network voulez-vous ?");
		boucleOnNetwork(true);
		int nb = scan.nextInt();
		Network n = web.getNetworks().get(nb);
		
		web.showAllDevicesInNetwork(n);
	}
	
	/**
	 * M�thode pour supprimer un device de son r�seau
	 */
	public void removeDeviceInNetwork() {
		if(web.getNetworks().size() > 0 && web.getDevices().size() > 0) {
			scan = new Scanner(System.in);
			System.out.println("Dans quel r�seau voulez-vous supprimer votre device ?");
			boucleOnNetwork(true);
			int nb = scan.nextInt();
			Network n = web.getNetworks().get(nb);
			
			System.out.println("Quel device voulez-vous supprimer dans ce r�seau ?");
			for(int i = 0; i < n.getDevices().size(); i++) {				
				System.out.println(i + " - " + n.getDevices().get(i));
			}
			nb = scan.nextInt();
			Device d = n.getDevices().get(nb);
			
			web.removeDeviceInNetwork(n, d);
		} else {
			System.out.println("Il n'y a aucun r�seau ou device !");
		}
	}
	
	/**
	 * M�thode pour afficher tous les devices et exclure 1 device pr�cis
	 * @param list
	 * @param exclude
	 */
	private void boucleOnDevice(List<Device> list, Device exclude) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getNumberOfNotConnectedInterface() > 0) {	
				if(exclude != null && ! list.get(i).equals(exclude)) {		
					System.out.println(i + " - " + list.get(i));
				} else if(exclude == null) {
					System.out.println(i + " - " + list.get(i));
				}
			}
		}
	}
	
	/**
	 * M�thode pour afficher tous les networks OU tous les networks qui ont au moins 1 device connect�
	 * @param type
	 */
	public void boucleOnNetwork(boolean type) {
		for(int i = 0; i < web.getNetworks().size(); i++) {
			if(type) {
				if(! web.getNetworks().get(i).getDevices().isEmpty()) {					
					System.out.println(i + " - " + web.getNetworks().get(i));
				}
			} else {
				System.out.println(i + " - " + web.getNetworks().get(i));
			}
		}
	}
	
	/**
	 * M�thode pour afficher toutes les interfaces non connect�es d'1 device
	 * @param d
	 */
	private void boucleOnInterface(Device d) {
		for(int i = 0; i < d.getInterfaces().size(); i++) {
			if(! d.getInterfaceByIndex(i).isUsed()) {					
				System.out.println(i + " - " + d.getInterfaces().get(i));
			}
		}
	}
	
	/**
	 * M�thode pour afficher tous les networks
	 */
	private void showNetworks() {
		List<Network> networks = web.getNetworks();
		if(networks.isEmpty()) {
			System.out.println("Aucun r�seau a �t� cr�e !");
		} else {
			System.out.println(networks);
		}
	}
	
	/**
	 * M�thode pour afficher tous les clients
	 */
	private void showClients() {
		List<Client> clients = web.getClients();
		if(clients.isEmpty()) {
			System.out.println("Aucun client a �t� cr�e !");
		} else {
			System.out.println(clients);
		}
	}
	
	/**
	 * M�thode pour afficher tous les serveurs
	 */
	private void showServers() {
		List<Server> servers = web.getServers();
		if(servers.isEmpty()) {
			System.out.println("Aucun serveur a �t� cr�e !");
		} else {
			System.out.println(servers);
		}
	}
	
	/**
	 * M�thode qui affiche la console d'un device
	 */
	private void promptDevice() {
		if(!web.getDevices().isEmpty()) {
			scan = new Scanner(System.in);
			System.out.println("\nA quel device voulez-vous acc�der ?");

			for(int i = 0; i < web.getDevices().size(); i++) { // On boucle sur tous les devices de type Client
				if(web.getDevices().get(i) instanceof Client) {					
					System.out.println(i + " - " + web.getDevices().get(i));
				}
			}

			int nb = scan.nextInt();
			Device d = web.getDevices().get(nb);
			String data = "";
			while(!data.equals("exit")) { // On rentre dans la boucle pour simuler la console
				data = scan.nextLine();
				if(data.equals("ip addr")) { // On v�rifie ce qui a �t� tap�
					System.out.print(defaultTextPrompt(d) + " " + d.getIp());
				} else if(data.matches("ping \\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b")) {
					String ip = data.substring(5, data.length()); // On r�cup�re l'IP
					if(ip.equals(d.getIp())) { // On a tap� notre propre IP
						System.out.print(defaultTextPrompt(d) + " L'IP rentr�e est votre propre IP !");
					} else {
						boolean result = checkConnectedDevices(d, ip, new ArrayList<>());
						// On v�rifie gr�ce � checkConnectedDevices si le device est connect� � l'IP rentr�e
						if(result) { // S'il est connect�
							System.out.println(defaultTextPrompt(d) + "La connexion est un succ�s !");
						} else { // Sinon
							System.out.println(defaultTextPrompt(d) + "La connexion est un �chec ! ");
						}
						System.out.print(defaultTextPrompt(d));
					}
				} else if(data.equals("help")) { // On v�rifie si l'utilisateur veut afficher de l'aide pour les commandes
					System.out.print(defaultTextPrompt(d) + " Voici les commandes disponibles dans la console :\n");
					System.out.println("ip addr -> Affiche l'IP du device sur lequel on est.");
					System.out.println("ping x.x.x.x -> V�rifie si le device correspondant � l'IP est connect� � ce device.");
					System.out.println("help -> Affiche les commandes disponibles.");
					System.out.println("network -> Affiche le r�seau du device actuel.");
					System.out.println("exit -> Quitte la console actuelle.");
					System.out.print(defaultTextPrompt(d));
				} else if(data.equals("network")) {
					checkDeviceNetwork(d);
				} else {
					System.out.print(defaultTextPrompt(d));					
				}
			}
		} else {
			System.out.println("\nIl n'y a aucun device !\n");
		}
	}
	
	/**
	 * M�thode qui renvoie true si le device est bien connect� � l'ip et false sinon
	 * @param d
	 * @param ip
	 * @param toExclude
	 * @return
	 */
	private boolean checkConnectedDevices(Device d, String ip, List<Device> toExclude) {
		List<Device> connectedDevice = new ArrayList<>();
		for(Interface inter : d.getInterfaces()) {
			Device linked = inter.getLinkedDevice();
			if(linked != null && !toExclude.contains(linked)) {
				connectedDevice.add(linked);
				toExclude.add(linked);
			}
		}
		for(Device device : connectedDevice) {
			if(device.getIp().equals(ip)) {
				return true;
			}
			if(device instanceof Server) {
				return checkConnectedDevices(device, ip, toExclude);
			}
		}
		return false;
	}
	
	private void checkDeviceNetwork(Device d) {
		if(! web.getNetworks().isEmpty()) {			
			for(int i = 0; i < web.getNetworks().size(); i++) {
				for(Device device : web.getNetworks().get(i).getDevices()) {
					if(device.getIp().equals(d.getIp())) {
						System.out.println(defaultTextPrompt(d) + " " + web.getNetworks().get(i).getIp()+"/"+web.getNetworks().get(i).getMasque());
						System.out.print(defaultTextPrompt(d));
						return;
					}
				}
			}
		}
		System.out.println(defaultTextPrompt(d) + " Le device n'appartient � aucun r�seau !");
		System.out.print(defaultTextPrompt(d));
	}
	
	/**
	 * M�thode pour afficher le texte de base de la console
	 * @param d
	 * @return
	 */
	private String defaultTextPrompt(Device d) {
		return d.getName() + ">>";
	}
	
}
