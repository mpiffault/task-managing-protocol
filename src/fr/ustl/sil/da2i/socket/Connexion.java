package fr.ustl.sil.da2i.socket;

import fr.ustl.sil.da2i.taches.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connexion extends Thread {
    
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
			envoi.println(traiterRequete(requete));
		    else // Connection closed
			return;
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
    }

    private String traiterRequete(String requete){

	if(requete.equals("CREATE"))
	    return "Création d'une tâche.";

	else if (requete.equals("STAT"))
	    return "Liste des tâches.";

	else if(requete.equals("AFF"))
	    return "Affectaion d'une tâche.";

	else if(requete.equals("CHSTAT"))
	    return "Changement du status d'une tâche.";

	else if(requete.equals("DEL"))
	    return "Suppression d'une tâche.";

	else
	    return "Bad Request";

    }
}