# **Projet : Jeu Démineur avec Sockets en Python**  

---

## **Description**  

Ce projet implémente le célèbre **jeu du Démineur** en utilisant des **sockets** pour permettre la communication entre un **client** et un **serveur** sur un réseau local.  
Le serveur héberge la logique du jeu tandis que le client permet à l'utilisateur d'interagir avec le jeu via une interface.

NB: le code servuer et cleint à dej été vu en cour ici on donne une implementation de la logique de jeu et du socket

---

## **Membres**  
- [**Samnick Tong Roberto Martial**](https://github.com/roberto-martial) *(Chef de projet)*  

---

## **Objectifs**  

1. **Serveur** :  
   - Héberger la logique du jeu Démineur.  
   - Gérer les connexions des clients et envoyer les réponses appropriées.  

2. **Client** :  
   - Permettre aux utilisateurs d'envoyer des commandes au serveur (ex: sélectionner une case).  
   - Recevoir et afficher les réponses du serveur pour continuer le jeu.  

3. **Communication Socket** :  
   - Utiliser les **sockets TCP/IP** pour assurer la communication entre le client et le serveur.  

---

## **Fonctionnalités**  

- **Connexion au serveur local** : Le client se connecte au serveur via une adresse IP et un port.  
- **Sélection des cases** : Le joueur peut sélectionner des cases pour révéler leur contenu.  
- **Gestion des bombes** :  
   - Si une case contient une bombe, la partie est perdue.  
   - Sinon, la case affiche le nombre de bombes adjacentes.  
- **Fin de partie** : Le serveur informe le client si la partie est gagnée ou perdue.  

---

## **Tâches**  

| **Rôle**      | **Tâches**                                     | **Détails**                                   |
|---------------|-----------------------------------------------|----------------------------------------------|
| **Chef**      | `Configuration du serveur`                    | Mise en place des sockets et gestion des connexions. |
|               | `Logique du jeu Démineur`                     | Implémentation des règles et génération de la grille. |
|               | `Développement du client`                     | Création de l'interface utilisateur pour jouer. |
| **Scribe**    | `Documentation`                               | Décrire le fonctionnement des sockets et du jeu. |

---

## **Structure du Projet**  

```plaintext
├── src/                      # Code source principal  
│   ├── server.py             # Serveur hébergeant la logique du jeu  
│   ├── client.py             # Client pour interagir avec le serveur  
│   ├── game_logic.py         # Logique du jeu Démineur (génération de grille, règles)  
│   └── utils.py              # Fonctions utilitaires pour le jeu  
│  
├── data/                     # Fichiers de configuration (IP, port, etc.)  
│  
├── results/                  # Captures d'écran des sessions de jeu  
│  
└── README.md                 # Documentation du projet  

