package fr.ustl.sil.da2i.taches;

import java.util.ArrayList;
import java.util.List;
import fr.ustl.sil.da2i.taches.*;

public class ListeDeTaches {
    private ArrayList<Tache> taches = null;

    /** Construit une liste de taches vides.
     */
    public ListeDeTaches()
    {
	this.taches = new ArrayList<Tache>();
    }

    /** Construit une nouvelle liste de taches
     * à partir de la liste passée en paramètre.
     */
    public ListeDeTaches(List<Tache> taches)
    {
	this.taches = (ArrayList<Tache>) taches;
    }

    public String toString()
    {
	String res = "Id,Intitulé,Affectation,Auteur\n";
	for (Tache t : taches)
	    {
		res += t + "\n";
	    }
	return res;
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
