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
	
	public void start() {
		System.out.println("########################################################\n"
						 + "######## Bienvenue sur un Packet Tracer light ! ########\n"
						 + "########################################################");
		System.out.println("\nQue voulez-vous faire ?\n");
		System.out.println("1. Créer un nouveau réseau");
		System.out.println("2. Créer un nouvel ordi portable");
		System.out.println("3. Créer un nouveau switch");
		System.out.println("4. Créer un nouveau routeur");
		System.out.println("5. Connecter 2 appareils par un câble");
		System.out.println("-----------------------------");
		System.out.println("10. Voir la liste des réseaux");
		System.out.println("11. Voir la liste des clients");
		System.out.println("12. Voir la liste des serveurs\n");
		System.out.print("Choisir un chiffre : ");
		scan = new Scanner(System.in);
		if(scan.hasNextInt()) {
			int v = scan.nextInt();
			switch(v) {
				case 1:
					addNetwork();
					break;
				case 2:
					addLaptop();
					break;
				case 3:
					addSwitch();
					break;
				case 4:
					addRouter();
					break;
				case 5:
					connectDevice();
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
			}
			options();
		} else {
			System.out.println("\nVous devez rentrer un chiffre !\n");
			start();
		}
	}
	
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
	
	private void addNetwork() {
		System.out.print("Quel est l'IP du réseau ? ");
		scan = new Scanner(System.in);
		ip = scan.nextLine();
		System.out.print("Quel est le masque du réseau ? (par défaut /24) ");
		String mask = scan.nextLine();
		web.addNetwork(ip, mask);
	}
	
	private void addLaptop() {
		System.out.print("Quel est le nom de votre laptop ? ");
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.print("Quel est l'IP de votre laptop ? ");
		ip = scan.nextLine();
		web.addLaptop(name, ip);
	}
	
	private void addSwitch() {
		System.out.print("Quel est le nom de votre switch ? ");
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.print("Nombre d'interfaces pour votre switch ? (par défaut 10) ");
		String interfaces = scan.nextLine();
		String s = interfaces.isEmpty() ? "0" : interfaces;
		web.addSwitch(name, Integer.parseInt(s));
	}
	
	private void addRouter() {
		System.out.print("Quel est le nom de votre routeur ? ");
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.print("Nombre d'interfaces pour votre routeur ? (par défaut 2) ");
		String interfaces = scan.nextLine();
		String s = interfaces.isEmpty() ? "0" : interfaces;
		web.addRouter(name, Integer.parseInt(s));
	}
	
	private void connectDevice() {
		scan = new Scanner(System.in);
		if(! web.getDevice().isEmpty() && web.getDevice().size() > 1) {
			System.out.println("\nQuel device voulez-vous connecter ?");
			for(int i = 0; i < web.getDevice().size(); i++) {
				if(web.getDevice().get(i).getNumberOfNotConnectedInterface() != 0) {					
					System.out.println(i + " - " + web.getDevice().get(i));
				}
			}
			int nb = scan.nextInt();
			Device d1 = web.getDevice().get(nb);
			System.out.println("\nQuelle interface voulez-vous connecter ?");
			for(int i = 0; i < d1.interfaces.size(); i++) {
				if(! d1.getInterfaceByIndex(i).used) {					
					System.out.println(i + " - " + d1.interfaces.get(i));
				}
			}
			nb = scan.nextInt();
			Interface i1 = d1.getInterfaceByIndex(nb);
			System.out.println("\nSur quel autre device voulez-vous vous connecter ? ");
			for(int i = 0; i < web.getDevice().size(); i++) {
				if(! web.getDevice().get(i).equals(d1)) {					
					System.out.println(i + " - " + web.getDevice().get(i));
				}
			}
			nb = scan.nextInt();
			Device d2 = web.getDevice().get(nb);
			System.out.println("\nSur quelle interface du deuxième device voulez-vous vous connecter ? ");
			for(int i = 0; i < d2.interfaces.size(); i++) {
				if(! d2.getInterfaceByIndex(i).used) {					
					System.out.println(i + " - " + d2.interfaces.get(i));
				}
			}
			nb = scan.nextInt();
			web.connectDevice(d1, d2, nb, i1);
		} else {
			System.out.println("Il n'y a aucun device crée ou il n'y a qu'un seul device !");
		}
	}
	
	private void showNetworks() {
		List<Network> networks = web.getNetworks();
		if(networks.isEmpty()) {
			System.out.println("Aucun réseau a été crée !");
		} else {
			System.out.println(networks);
		}
	}
	
	private void showClients() {
		List<Client> clients = web.getClients();
		if(clients.isEmpty()) {
			System.out.println("Aucun client a été crée !");
		} else {
			System.out.println(clients);
		}
	}
	
	private void showServers() {
		List<Server> servers = web.getServers();
		if(servers.isEmpty()) {
			System.out.println("Aucun serveur a été crée !");
		} else {
			System.out.println(servers);
		}
	}
	
}
