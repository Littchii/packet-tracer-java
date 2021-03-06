package controller;

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
	 * Méthode pour démarrer le programme
	 */
	public void start() {
		System.out.println("########################################################\n"
						 + "######## Bienvenue sur un Packet Tracer light ! ########\n"
						 + "########################################################");
		System.out.println("\nQue voulez-vous faire ?\n");
		System.out.println("1. Créer un nouveau réseau");
		System.out.println("2. Créer un nouvel ordinateur");
		System.out.println("3. Créer un nouvel ordinateur portable");
		System.out.println("4. Créer un nouveau switch");
		System.out.println("5. Créer un nouveau routeur");
		System.out.println("6. Connecter 2 appareils par un câble");
		System.out.println("7. Ajouter un device dans un réseau");
		System.out.println("8. Supprimer un device de son réseau");
		System.out.println("9. Voir tous les devices d'un réseau");
		System.out.println("-----------------------------");
		System.out.println("10. Voir la liste des réseaux");
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
	 * Méthode appelée après que l'utilisateur a effectué une action de start()
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
	 * Méthode pour ajouter un nouveau network
	 */
	private void addNetwork() {
		System.out.print("Quel est l'IP du réseau ? ");
		scan = new Scanner(System.in);
		ip = scan.nextLine();
		System.out.print("Quel est le masque du réseau ? (par défaut /24) ");
		String mask = scan.nextLine();
		web.addNetwork(ip, mask);
	}
	
	/**
	 * Méthode pour ajouter un nouveau computer ou laptop
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
	 * Méthode pour ajouter un switch
	 */
	private void addSwitch() {
		System.out.print("Quel est le nom de votre switch ? ");
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.print("Nombre d'interfaces pour votre switch ? (par défaut 10) ");
		String interfaces = scan.nextLine();
		String s = interfaces.isEmpty() ? "0" : interfaces;
		web.addSwitch(name, Integer.parseInt(s));
	}
	
	/**
	 * Méthode pour ajouter un routeur
	 */
	private void addRouter() {
		System.out.print("Quel est le nom de votre routeur ? ");
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.print("Nombre d'interfaces pour votre routeur ? (par défaut 2) ");
		String interfaces = scan.nextLine();
		String s = interfaces.isEmpty() ? "0" : interfaces;
		web.addRouter(name, Integer.parseInt(s));
	}
	
	/**
	 * Méthode pour connecter 2 devices ensemble avec leurs interfaces
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
			System.out.println("\nSur quelle interface du deuxième device voulez-vous vous connecter ? ");
			
			boucleOnInterface(d2);
			
			nb = scan.nextInt();
			web.connectDevice(d1, d2, nb, i1);
		} else {
			System.out.println("Il n'y a aucun device crée ou il n'y a qu'un seul device !");
		}
	}
	
	/**
	 * Méthode pour ajouter un device dans un network
	 */
	private void addDeviceInNetwork() {
		if(web.getNetworks().size() > 0 && web.getDevices().size() > 0) {			
			scan = new Scanner(System.in);
			System.out.println("Dans quel réseau voulez-vous ajouter votre device ?");
			boucleOnNetwork(false);
			int nb = scan.nextInt();
			Network n = web.getNetworks().get(nb);
			
			System.out.println("Quel device voulez-vous ajouter dans ce réseau ?");
			for(int i = 0; i < web.getDevices().size(); i++) {	
				if(! web.getDevices().get(i).getInNetwork()) {						
					System.out.println(i + " - " + web.getDevices().get(i));
				}
			}
			nb = scan.nextInt();
			Device d = web.getDevices().get(nb);
			
			web.addDeviceInNetwork(n, d);
		} else {
			System.out.println("Il n'y a aucun réseau ou device !");
		}
	}
	
	/**
	 * Méthode pour voir tous les devices connectés à un network
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
	 * Méthode pour supprimer un device de son réseau
	 */
	public void removeDeviceInNetwork() {
		if(web.getNetworks().size() > 0 && web.getDevices().size() > 0) {
			scan = new Scanner(System.in);
			System.out.println("Dans quel réseau voulez-vous supprimer votre device ?");
			boucleOnNetwork(true);
			int nb = scan.nextInt();
			Network n = web.getNetworks().get(nb);
			
			System.out.println("Quel device voulez-vous supprimer dans ce réseau ?");
			for(int i = 0; i < n.getDevices().size(); i++) {				
				System.out.println(i + " - " + n.getDevices().get(i));
			}
			nb = scan.nextInt();
			Device d = n.getDevices().get(nb);
			
			web.removeDeviceInNetwork(n, d);
		} else {
			System.out.println("Il n'y a aucun réseau ou device !");
		}
	}
	
	/**
	 * Méthode pour afficher tous les devices et exclure 1 device précis
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
	 * Méthode pour afficher tous les networks OU tous les networks qui ont au moins 1 device connecté
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
	 * Méthode pour afficher toutes les interfaces non connectées d'1 device
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
	 * Méthode pour afficher tous les networks
	 */
	private void showNetworks() {
		List<Network> networks = web.getNetworks();
		if(networks.isEmpty()) {
			System.out.println("Aucun réseau a été crée !");
		} else {
			System.out.println(networks);
		}
	}
	
	/**
	 * Méthode pour afficher tous les clients
	 */
	private void showClients() {
		List<Client> clients = web.getClients();
		if(clients.isEmpty()) {
			System.out.println("Aucun client a été crée !");
		} else {
			System.out.println(clients);
		}
	}
	
	/**
	 * Méthode pour afficher tous les serveurs
	 */
	private void showServers() {
		List<Server> servers = web.getServers();
		if(servers.isEmpty()) {
			System.out.println("Aucun serveur a été crée !");
		} else {
			System.out.println(servers);
		}
	}
	
	private void promptDevice() {
		if(web.getDevices().size() > 0) {
			scan = new Scanner(System.in);
			
			boucleOnDevice(web.getDevices(), null);
			
//			int nb = scan.nextInt();
//			Device d = web.getDevices().get(nb);
		} else {
			System.out.println("Il n'y a aucun device !");
		}
	}
	
}
