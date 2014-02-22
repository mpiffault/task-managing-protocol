package client;

import java.util.Scanner;

public class ClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String nom;
		Scanner sc = new Scanner(System.in);

		Client client = new Client();

		// Demande du nom de l'utilisateur
		nom = connexion(sc);

		while (true) {
			String commande = null;
			int action = menuPrincipal(sc);

			// Création des commandes à envoyer au serveur
			switch (action) {
			case 1:
				commande = "LIST";
				break;

			case 2:
				commande = "CREATE \""
						+ getChaine("Description de la tâche ?", sc) + "\" "
						+ nom;
				break;

			case 3:
				System.out.println(client.envoyer("LIST"));
				commande = "DEL "
						+ getNumTache("Numéro de la tâche à supprimer ?", sc);
				break;

			case 4:
				System.out.println(client.envoyer("LIST"));
				commande = "AFF "
						+ getNumTache("Numéro de la tâche à affecter ?", sc)
						+ " " + getChaine("Nom de la personne affectée ?", sc);
				break;

			case 5:
				System.out.println(client.envoyer("LIST"));
				commande = changerStatut(
						getNumTache("Numéro de la tâche à modifier ?", sc), sc);
				break;

			case 6:
				commande = "QUIT";
				System.exit(0);
				break;

			default:
				break;
			}

			// Envoi des commandes et récupération du résultat
			String resultat = client.envoyer(commande);
			System.out.println(resultat);
		}
	}

	/**
	 * @param numTache
	 * @param sc
	 * @return
	 */
	private static String changerStatut(int numTache, Scanner sc) {
		int ret = 0;

		do {
			System.out.println("Quel status voulez-vous affecter ?");
			System.out.println("-----------------------");
			System.out.println("1 - Libérer");
			System.out.println("2 - Clôturer");

			try {
				ret = sc.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
				ret = 0;
			}
		} while (ret < 1 || ret > 2);

		return "CHSTAT " + numTache + ((ret == 1) ? " LIB" : "CLOSE");
	}

	/**
	 * @param question
	 * @param sc
	 * @return
	 */
	private static String getChaine(String question, Scanner sc) {

		String chaine = null;

		do {
			System.out.println(question);

			try {
				chaine = sc.nextLine();
			} catch (Exception e) {
			}

		} while (chaine == null || chaine.isEmpty());

		return chaine;
	}

	/**
	 * @param question
	 * @param sc
	 * @return
	 */
	private static int getNumTache(String question, Scanner sc) {

		int numTache = 0;

		do {
			System.out.println(question);

			try {
				numTache = sc.nextInt();
			} catch (Exception e) {
			}

		} while (numTache == 0);

		return numTache;
	}

	/**
	 * Méthode permettant d'obtenir le nom de l'utilisateur Le nom est ensuite
	 * utilisé lors de la création des taches.
	 * 
	 * @param sc
	 * @return le nom renseigné
	 */
	private static String connexion(Scanner sc) {
		String nom = null;

		do {
			System.out.println("Votre nom:");
			try {
				nom = sc.next();
			} catch (Exception e) {
				nom = null;
			}
		} while (nom == null || nom.isEmpty());

		return nom;
	}

	/**
	 * @param sc
	 * @return
	 */
	private static int menuPrincipal(Scanner sc) {
		int ret = 0;

		do {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("-----------------------");
			System.out.println("1 - Lister les tâches");
			System.out.println("2 - Créer une tâche");
			System.out.println("3 - Supprimer une tâche");
			System.out.println("4 - Affecter une tâche");
			System.out.println("5 - Changer le statut d'une tâche");
			System.out.println("6 - Se déconnecter");

			try {
				ret = sc.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
				ret = 0;
			}
		} while (ret < 1 || ret > 6);

		return ret;
	}

}
