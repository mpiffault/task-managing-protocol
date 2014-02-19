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

	    System.out.println("Fin realiseService()"); // DEBUG
	}
    }
}
