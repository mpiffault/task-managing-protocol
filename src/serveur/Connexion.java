package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.MatchResult;

import taches.ListeDeTaches;

/**
 * Cette classe représente une instance de connexion avec un client sous la
 * forme d'un Thread. A la fin du dialogue,
 * 
 */
public class Connexion extends Thread {

	private static String erreur = "ERR Bad Request.";

	Socket client;

	Connexion(Socket client) {
		this.client = client;
	}

	public void run() {
		PrintWriter envoi = null;
		BufferedReader reception = null;
		try {
			envoi = new PrintWriter(client.getOutputStream(), true);
			reception = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!client.isClosed()) {
			try {
				String requete = reception.readLine();

				if (requete != null) {
					requete = requete.replaceAll("\\s+", " ");
					requete = requete.trim();
					String ret = traiterRequete(requete);
					if (ret != null) {
						envoi.println(ret);
					} else {
						envoi.println("Déconnecté.");
						client.close();
						System.exit(0);
					}
				} else
					// Connection closed
					return;
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	/**
	 * Cette méthode est un parser et dispatcher de la requête.
	 * 
	 * @param requete
	 * @return
	 */
	private String traiterRequete(String requete) {

		Scanner sc = new Scanner(requete);

		if (sc.findInLine("^(CREATE|LIST|AFF|CHSTAT|DEL|QUIT)") != null) {
			MatchResult m = sc.match();
			String commande = m.group(1);

			// Echappement des ':' => \:
			// CREATE Intitulé de la tache:Créateur
			// Doit créer une nouvelle tâche et retourner le numero de la
			// nouvelle tâche
			if (commande.equals("CREATE")) {
				sc.close();
				sc = new Scanner(requete);

				if (sc.findInLine("CREATE \"([^\"]*)\"\\s*(.*)") != null) {
					m = sc.match();
					String intitule = m.group(1);
					String createur = m.group(2);
					ListeDeTaches.ajouterTache(createur, intitule);
					sc.close();
					return "OK Task \"" + intitule + "\" created by "
							+ createur;
				} else {
					sc.close();
					return ("ERR Bad CREATE request");
				}
			}

			// LIST
			// Doit retourner la listes des tâches existantes
			else if (commande.equals("LIST")) {
				sc.close();
				return ListeDeTaches.getListe().toString();
			}

			// AFF num_tache
			// Retourne les détails de la tâche demandée
			else if (commande.equals("AFF")) {
				sc.close();
				sc = new Scanner(requete);
				int idTache = 0;
				String affectation = "";

				try {
					sc.next();
				} catch (Exception e) {
				}

				try {
					idTache = sc.nextInt();
				} catch (Exception e) {
					sc.close();
					return "ERR Bad AFF request (idTache required position 2)";
				}

				try {
					affectation = sc.next();
				} catch (Exception e) {
					sc.close();
					return "ERR Bad AFF request";
				}

				sc.close();
				return (ListeDeTaches.affecter(idTache, affectation)) ? "OK Tache "
						+ idTache + " affectée"
						: "ERR Task affected.";
			}

			// CHSTAT num_tache:(LIB|FINI)
			// Change le statut d'une tâche déjà affectée
			// (Pour un EFF, affecter à dummy si pas d'affectation préalable ?)
			else if (commande.equals("CHSTAT")) {
				sc.close();
				sc = new Scanner(requete);
				int idTache = 0;
				String status = "";

				try {
					sc.next();
				} catch (Exception e) {
				}

				try {
					idTache = sc.nextInt();
				} catch (Exception e) {
					sc.close();
					return "ERR Bad CHSTAT request (idTache required position 2).";
				}

				try {
					status = sc.next();
				} catch (Exception e) {
					sc.close();
					return "ERR Bad CHSTAT request.";
				}

				boolean b = false;
				if (status.equals("LIB")) {
					b = ListeDeTaches.affecter(idTache, null);
				} else if (status.equals("CLOSE")) {
					b = ListeDeTaches.cloturer(idTache);
				}

				sc.close();
				return b ? "OK Task status changed."
						: "ERR Task status inchanged.";
			}

			else if (commande.equals("DEL")) {
				sc.close();
				sc = new Scanner(requete);
				int idTache = 0;
				try {
					sc.next();
				} catch (Exception e) {
				}

				try {
					idTache = sc.nextInt();
				} catch (Exception e) {
					sc.close();
					return "ERR Bad DEL request (idTache required position 2).";
				}

				sc.close();
				if (ListeDeTaches.supprimerTache(idTache))
					return "OK Task deleted.";
				else
					return "ERR No task deleted.";
			}

			else if (commande.equals("QUIT")) {
				sc.close();
				return null;
			} else {
				sc.close();
				return "Request : " + requete + "\n" + erreur;
			}
		}
		// Pour toute commande invalide
		else {
			sc.close();
			return "Request : " + requete + "\n" + erreur;
		}
	}
}