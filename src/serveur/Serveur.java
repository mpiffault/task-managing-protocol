package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import taches.Tache;

/**
 * Classe mêre qui initialise les connexions. Elle redirige les clients vers des
 * processus légers dédiés à une connexion (multi-threading).
 */
public class Serveur {
	private ServerSocket serveurSocket = null;

	/**
	 * Constructeur du serveur. Nouvelle instance de Socket.
	 */
	public Serveur() {
		new ArrayList<Tache>();
		try {
			serveurSocket = new ServerSocket(8599);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Mise en attente de la Socket dans une boucle.
	 */
	public void miseEnService() {
		Socket unClient = null;

		while (true) {
			try {
				unClient = serveurSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}

			// Création d'un Thread serveur
			Connexion c = new Connexion(unClient);
			c.start();
		}
	}
}
