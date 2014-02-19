package fr.ustl.sil.da2i.taches;

public class Tache {

    public static int cptIds;

    private int idTache;
    private String createur;
    private String intitule;
    private String affectation;
    private boolean effectuee;

    public Tache()
    {
	this.idTache = ++cptIds;
	this.affectation = null;
    }

    public Tache(String createur, String intitule)
    {
	this();
	this.createur = createur;
	this.intitule = intitule;
	this.effectuee = false;
    }

    public String toString()
    {
	return idTache+","
	    + intitule+","
	    + (estAffectee() ? affectation : "Non affectée")+","
	    + createur;
    }

    public boolean affecter(String affectation)
    {
	if (! estAffectee())
	    {
		this.affectation = affectation;
		return true;
	    }
	return false;
    }

    public boolean estAffectee()
    {
	return affectation != null;
    }

    public void cloturer()
    {
	this.effectuee = true;;
    }

    public void setCreateur(String createur)
    {
	this.createur = createur;
    }

    public String getCreateur()
    {
	return createur;
    }

    public void setIntitule(String intitule)
    {
	this.intitule = intitule;
    }

    public String getIntitule()
    {
	return intitule;
    }

    public void setAffectation(String affectation)
    {
	this.affectation = affectation;
    }

    public String getAffectation()
    {
	return affectation;
    }
    

    public static void main (String [] args)
    {
	Tache t1 = new Tache("Corriger les bugs", "Martin");
	Tache t2 = new Tache("Ajouter la super fonctionnalité", "Joe");
	t2.affecter("Joe la Mouk");

	System.out.println(t1);
	System.out.println(t2);
    }
}