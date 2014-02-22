package taches;

public class Tache {

	public static int cptIds;

	private int idTache;
	private String createur;
	private String intitule;
	private String affectation;
	private boolean effectuee;

	public Tache() {
		this.idTache = ++cptIds;
		this.affectation = null;
	}

	public Tache(String createur, String intitule) {
		this();
		this.createur = createur;
		this.intitule = intitule;
		this.effectuee = false;
	}

	public String toString() {
		return idTache
				+ ","
				+ intitule
				+ ","
				+ (estAffectee() ? affectation : "Non affectée")
				+ ","
				+ (effectuee ? "réalisée" : (estAffectee() ? "affectée"
						: "libre")) + "," + createur;
	}

	public void effectuee() {
		effectuee = true;
	}

	/**
	 * @param affectation
	 * @return true si la tâche a bien été affectée/libérée. false sinon (dans
	 *         le cas où l'on tente d'affecter une tâche déjà affectée)
	 */
	public boolean affecter(String affectation) {
		if (affectation != null && !estAffectee()) {
			this.affectation = affectation;
			return true;
		} else if (affectation == null) {
			this.affectation = null;
			return true;
		}
		return false;
	}

	/**
	 * @return true si la tache est déjà affectée, false sinon.
	 */
	public boolean estAffectee() {
		return affectation != null;
	}

	public void cloturer() {
		this.effectuee = true;
	}

	public int getId() {
		return idTache;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public String getCreateur() {
		return createur;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setAffectation(String affectation) {
		this.affectation = affectation;
	}

	public String getAffectation() {
		return affectation;
	}
}