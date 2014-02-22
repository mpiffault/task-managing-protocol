package taches;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Classe singelton qui représente une liste de tache.
 * 
 */
public class ListeDeTaches {
	private static ConcurrentHashMap<Integer, Tache> listeTaches = null;

	private static ListeDeTaches lt = new ListeDeTaches();

	/**
	 * Construit une liste de taches vides.
	 */
	public ListeDeTaches() {
		ListeDeTaches.listeTaches = new ConcurrentHashMap<Integer, Tache>();
	}

	public String toString() {
		String res = "Id,Intitulé,Affectation,Statut,Auteur\n";
		for (Integer i : new TreeSet<Integer>(listeTaches.keySet())) {
			res += listeTaches.get(i) + "\n";
		}

		return res;
	}

	/**
	 * @return la liste de tâches.
	 */
	public static ListeDeTaches getListe() {
		return lt;
	}

	/**
	 * Ajoute une nouvelle tâche à la liste.
	 * 
	 * @param createur
	 * @param intitule
	 */
	public static void ajouterTache(String createur, String intitule) {
		Tache t = new Tache(createur, intitule);

		listeTaches.put(t.getId(), t);
	}

	/**
	 * @param idTache
	 * @return la tâche d'identifiant idTache.
	 */
	public static Tache getTache(int idTache) {
		return listeTaches.get(idTache);
	}

	/**
	 * @param idTache
	 * @param affectation
	 * @return true si la tâche a bien été affectée/libérée. false sinon (dans
	 *         le cas où l'on tente d'affecter une tâche déjà affectée)
	 */
	public static boolean affecter(int idTache, String affectation) {
		Tache t = listeTaches.get(idTache);
		if (t != null) {
			return t.affecter(affectation);
		} else
			return false;
	}

	/**
	 * @param idTache
	 * @return true si la tache a bien été supprimée. false sinon (tache
	 *         inexistante)
	 */
	public static boolean supprimerTache(int idTache) {
		if (listeTaches.get(idTache) != null) {
			listeTaches.remove(idTache);
			return true;
		} else
			return false;

	}

	public static boolean cloturer(int idTache) {
		Tache t = listeTaches.get(idTache);
		if (t != null) {
			t.cloturer();
			return true;
		}
		return false;
	}
}
