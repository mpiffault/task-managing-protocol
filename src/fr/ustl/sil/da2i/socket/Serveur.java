package fr.ustl.sil.da2i.socket;

import fr.ustl.sil.da2i.taches.*;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    private ServerSocket serveurSocket = null;
    private ArrayList<Tache> taches = null;

    /** Constructeur du serveur. Nouvelle instance de Socket.
     */
    public Serveur() {
	taches = new ArrayList<Tache>();
	try {
	    serveurSocket = new ServerSocket(8599);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    /** Mise en attente de la Socket dans une boucle.
     * Bloquant.
     */
    // TODO: Thread
    public void miseEnService() {
	Socket unClient = null;
		
	while (true ) {
	    try {
		unClient = serveurSocket.accept();
	    } catch (IOException e) {
		e.printStackTrace();
		System.exit(1);
	    }

	    Connexion c = new Connexion(unClient);
	    c.start();

	    //	    realiseService(unClient);
	    System.out.println("Fin realiseService()"); // DEBUG
	}
    }

    
    // private void realiseService(Socket unClient) {
    // 	PrintWriter envoi = null;
    // 	BufferedReader reception = null;

    // 	envoi = new PrintWriter(unClient.getOutputStream(), true);
    // 	reception = new BufferedReader(new InputStreamReader(unClient.getInputStream()));
    // 	while (! unClient.isClosed())
    // 	    {
    // 		try {
    // 		    String requete = reception.readLine();

    // 		    if (requete != null)
    // 			envoi.println(traiterRequete(requete));
    // 		    else // Connection closed
    // 			return;
    // 		} catch (IOException e) {
    // 		    e.printStackTrace();
    // 		    envoi.close();
    // 		    reception.close();
    // 		}
    // 	    }
    // }

    // private String traiterRequete(String requete){

    // 	if(requete.equals("CREATE"))
    // 	    return "Création d'une tâche.";

    // 	else if (requete.equals("STAT"))
    // 	    return "Liste des tâches.";

    // 	else if(requete.equals("AFF"))
    // 	    return "Affectaion d'une tâche.";

    // 	else if(requete.equals("CHSTAT"))
    // 	    return "Changement du status d'une tâche.";

    // 	else if(requete.equals("DEL"))
    // 	    return "Suppression d'une tâche.";

    // 	else
    // 	    return "Bad Request";

    // }
}
