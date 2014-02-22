package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket clientSocket = null;
	PrintWriter envoi = null;
	BufferedReader reception = null;

	public Client() {
		try {
			clientSocket = new Socket("localhost", 8599);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			envoi = new PrintWriter(clientSocket.getOutputStream(), true);
			reception = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Envoi la commande passée en paramètre au serveur.
	 * 
	 * @param message
	 * @return
	 */
	public String envoyer(String message) {
		envoi.println(message);
		String ret = "";
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			while (reception.ready()) {
				ret += reception.readLine() + "\n";
			}
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
