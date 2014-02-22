task-managing-protocol
======================

TMP - Task Managing Protocol

Prototype de d'un protocole de gestion de tâches inspiré du Post Office Protocol (POP).

Commandes client-serveur:

# LIST

    Retourne la liste des tâches

# CREATE <N° Tache> <Créateur>

    Créé une nouvelle tâche
	
# DEL <N° Tache>

    Supprime une tache

# AFF <N° Tache> <Agent>

    Affecte une tâche à un agent

# CHSTAT <N° Tache> <(LIB|CLOSE)>

    Change l'état d'une tâche (Libre, Terminée)

# QUIT

    Déconnecte le client
