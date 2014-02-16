package fr.ustl.sil.da2i.taches;

public class Tache {

    public static int cptIds;

    private int idTache;
    private String auteur;
    private String intitule;
    private String personne;
    private boolean effectuee;

    public Tache()
    {
	this.idTache = ++cptIds;
	this.personne = null;
    }

    public Tache(String auteur, String intitule)
    {
	this();
	this.auteur = auteur;
	this.intitule = intitule;
	this.effectuee = false;
    }

    public boolean affecter(String personne)
    {
	if (! estAffectee())
	    {
		this.personne = personne;
		return true;
	    }
	return false;
    }

    public boolean estAffectee()
    {
	return personne != null;
    }

    public void cloturer()
    {
	this.effectuee = true;;
    }

    public void setAuteur(String auteur)
    {
	this.auteur = auteur;
    }

    public String getAuteur()
    {
	return auteur;
    }

    public void setIntitule(String intitule)
    {
	this.intitule = intitule;
    }

    public String getIntitule()
    {
	return intitule;
    }

    public void setPersonne(String personne)
    {
	this.personne = personne;
    }

    public String getPersonne()
    {
	return personne;
    }

}