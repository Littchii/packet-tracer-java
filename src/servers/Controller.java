package servers;

import java.util.Scanner;

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
		System.out.println("3. Créer un nouvel ordi fix");
		System.out.println("-----------------------------");
		System.out.println("10. Voir la liste des réseaux");
		System.out.println("11. Voir la liste des clients\n");
		System.out.print("Choisir un chiffre : ");
		scan = new Scanner(System.in);
		if(scan.hasNextInt()) {
			int v = scan.nextInt();
			switch(v) {
				case 1:
					addNetwork();
					options();
					break;
				case 2:
					addLaptop();
					options();
					break;
				case 10:
					showNetwork();
					options();
					break;
				case 11:
					showClients();
					options();
					break;
			}
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
		System.out.print("Quel est l'IP de votre laptop ? ");
		scan = new Scanner(System.in);
		ip = scan.nextLine();
		System.out.print("Quel est nom de votre laptop ? ");
		String name = scan.nextLine();
		web.addLaptop(name, ip);
	}
	
	private void showNetwork() {
		System.out.println(web.showNetworks());
	}
	
	private void showClients() {
		System.out.println(web.showClients());
	}
	
}
