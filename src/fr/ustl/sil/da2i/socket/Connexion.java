package fr.ustl.sil.da2i.socket;

import fr.ustl.sil.da2i.taches.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.*;

public class Connexion extends Thread {
    
    private static String erreur = "ERR Bad Request.";
    Socket client;

    Connexion(Socket client)
    {
	this.client = client;
    }

    public void run()
    {
	PrintWriter envoi = null;
	BufferedReader reception = null;
	try {
	    envoi = new PrintWriter(client.getOutputStream(), true);
	    reception = new BufferedReader(new InputStreamReader(client.getInputStream()));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	while (! client.isClosed())
	    {
		try {
		    String requete = reception.readLine();

		    if (requete != null)
			{
			    requete = requete.replaceAll("\\s+", " ");
			    requete = requete.trim();
			    envoi.println( traiterRequete(requete) );
			}
		    else // Connection closed
			return;
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
    }

    private String traiterRequete(String requete){
	
	Scanner sc = new Scanner(requete);

	if (sc.findInLine("^(CREATE|LIST|AFF|CHSTAT|DEL|QUIT)") != null)
	    // if (Pattern.matches("^(CREATE|LIST|AFF|CHSTAT|DEL|QUIT)$", requete))
	    {
		MatchResult m = sc.match();
		String commande = m.group(1);

		// Echappement des ':' => \:
		// CREATE Intitulé de la tache:Créateur
		//Doit créer une nouvelle tâche et retourner le numero de la nouvelle tâche
		if(commande.equals("CREATE"))
		    {
			sc = new Scanner(requete);
			//if (sc.findInLine("CREATE ([^:]*):([^:]*)") != null)
			if (sc.findInLine("CREATE \"([^\"]*)\"\\s*(.*)") != null)
			    {
				m = sc.match();
				String intitule = m.group(1);
				String createur = m.group(2);
				return "OK Création de la tâche \"" + intitule + "\" par " + createur;
			    }
			else
			    return ("Bad CREATE request !");
		    }
		// LIST
		// Doit retourner la listes des tâches existantes
		else if (commande.equals("LIST"))
		    {
			return "OK Liste des tâches.";
		    }

		// AFF num_tache
		// Retourne les détails de la tâche demandée
		else if(commande.equals("AFF"))
		    {
			return "OK Affectation d'une tâche.";
		    }

		// CHSTAT num_tache:(LIB|FINI)
		// Change le statut d'une tâche déjà affectée
		// (Pour un EFF, affecter à dummy si pas d'affectation préalable ?)
		else if(commande.equals("CHSTAT"))
		    {
			return "OK Changement du status d'une tâche.";
		    }

		// DEL num_tache
		// Supprime la tâche
		else if(commande.equals("DEL"))
		    {
			return "OK Suppression d'une tâche.";
		    }

		// QUIT
		// Déconnecte l'utilisateur
		else if(commande.equals("QUIT"))
		    {
			return "OK Déconnecté du serveur.";
		    }
		else
		    return "Requete : " + requete + "\n" + erreur;
	    }
	// Pour toute commande invalide
	else
	    return "Requete : " + requete + "\n" + erreur;

    }
}