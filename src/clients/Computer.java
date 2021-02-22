package clients;

public class Computer extends Client {
	
	public Computer(String ip, String name) {
		super(ip, name);
	}
	
	public String toString() {
		return getIp();
	}

}
