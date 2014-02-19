package fr.ustl.sil.da2i.taches;

import java.util.ArrayList;
import java.util.List;
import fr.ustl.sil.da2i.taches.*;

public class ListeDeTaches {
    private static ArrayList<Tache> listeTaches = null;

    private static ListeDeTaches t = new ListeDeTaches();

    /** Construit une liste de taches vides.
     */
    public ListeDeTaches()
    {
	this.listeTaches = new ArrayList<Tache>();
    }

    /** Construit une nouvelle liste de taches
     * à partir de la liste passée en paramètre.
     */
    public ListeDeTaches(List<Tache> listeTaches)
    {
	this.listeTaches = (ArrayList<Tache>) listeTaches;
    }

    public String toString()
    {
	String res = "Id,Intitulé,Affectation,Auteur\n";
	for (Tache t : listeTaches)
	    {
		res += t + "\n";
	    }
	return res;
    }

    public static ListeDeTaches getListe()
    {
	return t;
    }

    public static void ajouterTache(String createur, String intitule)
    {
	listeTaches.add(new Tache(createur, intitule));
    }

    public static void main (String args[])
    {
	ArrayList<Tache> at = new ArrayList<Tache>();

	at.add(new Tache("Martin", "Corriger les bugs"));
	at.add(new Tache("Joe", "Ajouter la super fonctionnalité"));

	ListeDeTaches lt = new ListeDeTaches(at);

	System.out.println(lt);
    }
}
